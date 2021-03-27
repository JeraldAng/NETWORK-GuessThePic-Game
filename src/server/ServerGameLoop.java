package server;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.util.Timer;
import java.util.TimerTask;

public class ServerGameLoop implements Runnable {
    Server server;

    public ServerGameLoop(Server server){
        this.server = server;
    }

    @Override
    public void run() {
        while (server.running) {
            server.model.next();

            while (!(server.hasClients())) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (!(server.allReady())) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while(!server.hasRoundWinner()){
                if(!server.hasWinner()){
                    server.model.next();
                    server.countDown = 30;
                    server.timer = new Timer();
                    server.timer.schedule(new CountdownTimer(), 0, 2000);
                    while(server.countDown > 0 && !server.hasRoundWinner() && !server.hasWinner()){
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if(server.hasWinner()){
                        server.timer.cancel();
                        server.round--;
                        //server.stopThread();
                        //server.removeClients();
                        //server.exit();
                    }
                    server.round++;
                    server.roundWinner = null;
                }
            }
        }
    }

    public class CountdownTimer extends TimerTask {
        @Override
        public void run() {
            server.countDown--;
            if(server.countDown <= 0){
                cancel();
            }
        }
    }
}
