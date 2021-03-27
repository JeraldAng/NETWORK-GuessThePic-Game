package client;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import server.Players;
import view.MainGameView;

public class MainGameController {
    private MainController mainController;
    private MainGameView mainGameView;
    private Image profile;
    private String username;
    Thread thread;

    private boolean running;

    public MainGameController(MainController mainController, Image[] icons) {
        this.mainController = mainController;

        this.mainGameView = new MainGameView(icons, this);

        this.setViewControls();

        running = true;

        game();
    }

    public void setProfile(Image var1) {
        this.profile = var1;
        mainGameView.setProfile1Icon(profile);
        mainGameView.setProfile2Icon(profile);
        mainGameView.setProfile3Icon(profile);
    }

    public void setUsername(String var1) {
        this.username = var1;

        mainGameView.setName(username);
        mainGameView.setLprofile1(username);
    }

    public void setViewControls() {
        this.mainGameView.setOnSendClick(new MainGameView.OnSendClick() {
            @Override
            public void doAction(String message, ObservableList<String> messages) {
                try{
                    String name = mainController.getClient().getName();
                    mainController.getClient().requestMessage(name, message);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }


    public void game(){
        thread = new Thread(()->{
            int x = 0;
            while(running) {
                Platform.runLater(() -> {
                    //mainGameView.getListView().refresh();
                    //if(mainController.getClient().getMessages() != null && !isThere(mainController.getClient().getMessage()))
                      //  mainGameView.getMessages().add(mainController.getClient().getMessages());
                    mainGameView.setMessages(mainController.getClient().getMessages());
                    mainGameView.getListView().setItems(mainGameView.getMessages());
                    mainController.getClient().requestUpdate();
                });

                if (mainController.getClient().countdown > 24) {
                    Platform.runLater(() -> {
                        mainController.getClient().requestPicture();
                    });
                }
                else if (mainController.getClient().countdown > 19){
                    mainGameView.open2();
                }
                else if (mainController.getClient().countdown > 14){
                    mainGameView.open3();
                }
                else if (mainController.getClient().countdown > 9){
                    mainGameView.open4();
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.exit(0);
                    e.printStackTrace();
                }
            }
        });
    }

    public void gameStart () {
        thread.start();
    }

    public void stopThread(){
        if(thread.isAlive())
            thread.interrupt();
    }

    public boolean isThere(String message){
        for(String s : mainGameView.getMessages()){
            if(s.equalsIgnoreCase(message))
                return true;
        }
        return false;
    }

    public MainGameView getMainGameView() {
        return mainGameView;
    }
}
