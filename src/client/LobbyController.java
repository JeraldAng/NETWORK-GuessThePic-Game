package client;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import view.LobbyView;
import view.LogInView;

import java.io.IOException;
import java.util.Date;

public class LobbyController {

    private MainController mainController;
    private LobbyView lobbyView;
    private LogInView logInView;
    private Image profile;

    private String username;

    Thread thread;

    public LobbyController(MainController mainController, Image[] icons) {
        this.mainController = mainController;

        this.lobbyView = new LobbyView(icons);

        this.setViewControls();
        makeupdate();
    }

    public void setProfile(Image var1) {
        this.profile = var1;
        lobbyView.setProfile1Icon(profile);
        lobbyView.setProfile2Icon(profile);
        lobbyView.setProfile3Icon(profile);
    }

    public Image getProfile() {
        return this.profile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String var1) {
        this.username = var1;

        lobbyView.setName(username);
        lobbyView.setLprofile1(username);
    }

    public void setViewControls() {
        this.lobbyView.setOnReadyClick(new LobbyView.OnReadyClick() {
            public void doAction() {
                try {
                    mainController.getClient().ready();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
         });

        /*this.lobbyView.setOnSendClick(new LobbyView.OnSendClick() {
            public void doAction(String message, ObservableList<String> names) {
                AnimationTimer time = new AnimationTimer() {
                    public void handle(long now) {
                        int startTime = new Date().getSeconds();
                        LobbyController.this.mainController.getMainGameController().getMainGameView().setLtime(String.valueOf(new Date().getSeconds() - startTime));
                    }
                };
               lobbyView.getMessage();
            }
        });*/
    }

    public void makeupdate(){
        thread = new Thread(()->{
            while(!mainController.getClient().readyCheck()){
                Platform.runLater(()->{
                    mainController.getClient().requestNames();
                });

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Platform.runLater(()->{
                mainController.changeScene(3);
            });
        });
    }

    public void startThread(){
        thread.start();
    }

    public void stopThread(){
        if(thread.isAlive())
            thread.interrupt();
    }

    public LobbyView getLobbyView() {
        return lobbyView;
    }
}
