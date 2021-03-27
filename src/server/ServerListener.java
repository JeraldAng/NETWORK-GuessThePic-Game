package server;

import model.Protocols;

import java.io.IOException;
import java.net.InetAddress;

public class ServerListener implements Runnable {
    Server server;

    public ServerListener(Server server){
        this.server = server;
    }

    @Override
    public void run() {
        while(server.running){
            try{
                listen();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void listen() throws IOException {
        server.receive();
        String s = new String(server.packet.getData(), 0, server.packet.getLength());
        Protocols p = Protocols.getProtocols(s);
        System.out.println(p.getDescription());
        InetAddress address = server.packet.getAddress();
        int port = server.packet.getPort();

        switch(p){
            case CONNECT:
                server.addClient(address, port);
                break;
            case READY:
                server.clientReady();
                break;
            case PICTURE:
                server.sendPicture(address, port);
                break;
            case NAME:
                server.sendName(address, port);
                break;
            case READYCHECK:
                server.sendReadyCheck(address, port);
                break;
            case UPDATE:
                server.sendUpdates(address, port);
                break;
            case MESSAGE:
                server.sendMessage(address, port);
                break;
        }
    }
}
