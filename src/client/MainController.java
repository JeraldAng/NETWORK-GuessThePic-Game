package client;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import server.Server;

import java.net.InetAddress;
import java.net.SocketException;

public class MainController {
    private Stage mainStage;
    private Scene scene;
    private InetAddress serverAddress;
    private LogInController logIn;
    private ClientModel client;

    public static final int LOGIN_VIEW = 1;
    public static final int LOBBY_VIEW = 2;
    public static final int MAINGAME_VIEW = 3;

    private LogInController logInController;
    private LobbyController lobbyController;
    private MainGameController mainGameController;
    private int previousView;


    public MainController(Stage mainStage, InetAddress address) throws SocketException{
        this.mainStage = mainStage;
        mainStage.setTitle("Photo-Off");
        mainStage.setResizable(false);
        this.serverAddress = address;
        scene = new Scene(new Group(), 550, 700);
        initControllers();
        client = new ClientModel(serverAddress, mainGameController.getMainGameView(), lobbyController.getLobbyView());
        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                mainGameController.stopThread();
                lobbyController.stopThread();
                //if exits
                Platform.exit();
                System.exit(0);
            }
        });
        changeScene(1);

        mainStage.setScene(scene);
        mainStage.show();
    }

    private void initControllers(){
        logInController = new LogInController(this);
        lobbyController = new LobbyController(this, logInController.getLogInView().getIcons());
        mainGameController = new MainGameController(this, logInController.getLogInView().getIcons());
        System.out.println("Hello I Printed");
    }

    public void changeScene(int view) {
        this.previousView = view;
        switch(previousView) {
            case 1: scene.setRoot(logInController.getLogInView());
                break;
            case 2: this.mainStage.setHeight(700.0D);
                this.mainStage.setWidth(1000.0D);
                this.mainStage.setTitle("Lobby");
                scene.setRoot(lobbyController.getLobbyView());
                lobbyController.setProfile(logInController.getIcon());
                lobbyController.startThread();
                break;
            case 3: this.mainStage.setHeight(700.0D);
                this.mainStage.setWidth(1200.0D);
                this.mainStage.setTitle("Photo-off");
                mainGameController.setProfile(logInController.getIcon());
                scene.setRoot(mainGameController.getMainGameView());
                mainGameController.gameStart();
                break;
        }
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public ClientModel getClient() {
        return client;
    }
}
