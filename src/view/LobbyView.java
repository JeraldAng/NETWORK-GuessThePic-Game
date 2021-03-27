package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.PrintWriter;
import java.net.Socket;

public class LobbyView extends AnchorPane implements View {

    /**************
        Players
     *************/
    private OnReadyClick onReadyClick;

    private ImageView profile1;
    private ImageView profile2;
    private ImageView profile3;
    public Label lprofile1;
    public Label lprofile2;
    public Label lprofile3;
    private ChangeScene changeScene;

    /**************
        ChatBox
     *************/
    private OnSendClick onSendClick;
    private ObservableList<String> messages;

    private Label name;
    private ListView<String> listView;
    private VBox list;
    private TextField message;
    private Image sendIcon;
    private ImageView send;

    private Socket socket;
    private PrintWriter print;
    Image[] icons;

    public LobbyView(Image[] icons) {
        this.icons = icons;
        this.init();
    }

    public void init() {
        //Background Image
        BackgroundImage bg = new BackgroundImage(new Image("file:bg.jpg", 1000.0D, 700.0D, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(new BackgroundImage[]{bg}));

        //DropShadow
        DropShadow shadow = new DropShadow();
        shadow.setOffsetY(5.0);
        shadow.setOffsetX(5.0);
        shadow.setColor(Color.GREEN);

        /**********************
           Left Side (Profile)
         *********************/
        //Profile 1
        this.profile1 = new ImageView();
        this.setTopAnchor(profile1, 110.0D);
        this.setLeftAnchor(profile1, 75.0D);

        //Label for Player 1
        this.lprofile1 = new Label("");
        lprofile1.setFont(Font.font("Courier New", FontWeight.BOLD, 30.0D));
        lprofile1.setPrefSize(270, 50);
        lprofile1.setAlignment(Pos.CENTER);

        this.setTopAnchor(lprofile1, 150.0D);
        this.setLeftAnchor(lprofile1, 150.0D);

        //Ready 1
        Image ready1Icon = new Image("file:ready.png", 200D, 150D, true, true);
        ImageView ready1 = new ImageView();
        ready1.setImage(ready1Icon);
        ready1.setVisible(true);
        this.setTopAnchor(ready1, 140.0D);
        this.setLeftAnchor(ready1, 380.0D);

        ready1.setOnMouseClicked(e -> {
            onReadyClick.doAction();
            ready1.setEffect(shadow);
        });

        //Profile 2
        this.profile2 = new ImageView();
        this.setTopAnchor(profile2, 260.0D);
        this.setLeftAnchor(profile2, 75.0D);

        //Label for Player 2
        this.lprofile2 = new Label("");
        lprofile2.setFont(Font.font("Courier New", FontWeight.BOLD, 30.0D));
        lprofile2.setPrefSize(270, 50);
        lprofile2.setAlignment(Pos.CENTER);

        this.setTopAnchor(lprofile2, 300.0D);
        this.setLeftAnchor(lprofile2, 150.0D);

        //Ready 2
        Image ready2Icon = new Image("file:ready.png", 200D, 150D, true, true);
        ImageView ready2 = new ImageView();
        ready2.setImage(ready2Icon);
        ready2.setVisible(true);
        this.setTopAnchor(ready2, 290.0D);
        this.setLeftAnchor(ready2, 380.0D);

        ready2.setOnMouseClicked(e -> {
            ready2.setEffect(shadow);
            onReadyClick.doAction();
        });
        //Profile 3
        this.profile3 = new ImageView();
        this.setTopAnchor(profile3, 410.0D);
        this.setLeftAnchor(profile3, 75.0D);

        //Label for Player 3
        this.lprofile3 = new Label("");
        lprofile3.setFont(Font.font("Courier New", FontWeight.BOLD, 30.0D));
        lprofile3.setPrefSize(270, 50);
        lprofile3.setAlignment(Pos.CENTER);

        this.setTopAnchor(lprofile3, 450.0D);
        this.setLeftAnchor(lprofile3, 150.0D);

        //Ready 3
        Image ready3Icon = new Image("file:ready.png", 200D, 150D, true, true);
        ImageView ready3 = new ImageView();
        ready3.setImage(ready3Icon);
        ready3.setVisible(true);
        this.setTopAnchor(ready3, 440.0D);
        this.setLeftAnchor(ready3, 380.0D);

        ready3.setOnMouseClicked(e -> {
            ready3.setEffect(shadow);
            onReadyClick.doAction();
        });
        /**********************
          Right Side (Chatbox)
         *********************/
        AnchorPane achat = new AnchorPane();
        achat.setPrefSize(400, 480);
        HBox hchat = new HBox();
        hchat.setPrefSize(400, 480);

        this.setTopAnchor(hchat, 100.D);
        this.setRightAnchor(hchat, 25.D);

        //Background Image
        BackgroundImage phone = new BackgroundImage(new Image("file:phone.png", 370.0D, 480.0D, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        achat.setBackground(new Background(new BackgroundImage[]{phone}));

        // Client 1 Name
        this.name = new Label("");
        name.setTextFill(Color.WHITE);
        name.setAlignment(Pos.CENTER);
        name.setFont(Font.font("Courier New", FontWeight.BOLD, 20.0D));
        achat.setTopAnchor(name, 6D);
        achat.setLeftAnchor(name, 160D);

        //Display of Messages
        messages = FXCollections.observableArrayList();
        this.listView = new ListView<String>(messages);

        this.list = new VBox();
        list.getChildren().add(listView);
        list.setVgrow(listView, Priority.ALWAYS);
        list.setStyle("-fx-box-border: transparent;");
        list.setPrefSize(250, 230);

        HBox hlist = new HBox();
        hlist.setPrefSize(250, 230);
        hlist.getChildren().add(list);
        hlist.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));

        achat.setTopAnchor(hlist, 45D);
        achat.setLeftAnchor(hlist, 78D);

        //Input Message
        this.message = new TextField();
        message.setPromptText("Text Message");
        message.setAlignment(Pos.CENTER);
        message.setFont(Font.font("Courier New", FontWeight.BOLD, 20.0D));
        message.setPrefSize(250, 50);
        message.setEditable(false);

        HBox hmessage = new HBox();
        hmessage.setPrefSize(250, 50);
        hmessage.getChildren().add(message);
        hmessage.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(5))));

        achat.setBottomAnchor(hmessage, 150.0D);
        achat.setLeftAnchor(hmessage, 78.0D);

        //SEND Icon
        this.sendIcon = new Image("file:send.png", 450.0D, 70.0D, true, true);
        this. send = new ImageView(sendIcon);
        send.setImage(sendIcon);
        send.setOnMouseClicked(e -> {
            onSendClick.doAction(message.getText(), messages);
            message.clear();
        });
        send.setDisable(true);

        achat.setBottomAnchor(send, 70.0D);
        achat.setLeftAnchor(send, 170.0D);

        achat.getChildren().addAll (
                name, hlist, hmessage, send
        );

        hchat.getChildren().add(achat);


        /*********************
           For the whole Scene
         *********************/
        this.getChildren().addAll(
                profile1, profile2, profile3, lprofile1, lprofile2, lprofile3, ready1, ready2, ready3,
                hchat
        );
    }

    public void setChangeScene (ChangeScene changeScene) {
        this.changeScene = changeScene;
    }

    public void setProfile1Icon (Image image) {
        this.profile1.setImage(image);
    }

    public void setProfile2Icon (Image image) {
        this.profile2.setImage(image);
    }

    public void setProfile3Icon (Image image) {
        this.profile3.setImage(image);
    }

    public void setOnReadyClick(OnReadyClick var1) {
        this.onReadyClick = var1;
    }

    public void setName(String names) {
        this.name.setText(names);
    }

    public void setLprofile1(String lprofile1s) {
        this.lprofile1.setText(lprofile1s);
    }

    public void setLprofile2(String lprofile1s) {
        this.lprofile2.setText(lprofile1s);
    }

    public void setLprofile3(String lprofile1s) {
        this.lprofile3.setText(lprofile1s);
    }

    public void setOnSendClick (OnSendClick phone) {
        this.onSendClick = phone;
    }

    public Label getLprofile1() {
        return lprofile1;
    }

    public Label getLprofile2() {
        return lprofile2;
    }

    public Label getLprofile3() {
        return lprofile3;
    }

    public ObservableList<String> getMessages() {
        return messages;
    }

    public ListView<String> getListView() {
        return listView;
    }

    public TextField getMessage() {
        return message;
    }

    @Override
    public void update() {

    }

    public void updatePlayers(String players[]) {
        String[] hold = players[0].split(",");
        if (players.length > 0) {
            setLprofile1(hold[0]);
            setProfile1Icon(icons[Integer.parseInt(hold[1])]);
        }

        if(players.length > 1){
            hold = players[1].split(",");
            setLprofile2(hold[0]);
            setProfile2Icon(icons[Integer.parseInt(hold[1])]);

        }

        if(players.length > 2){
            hold = players[2].split(",");
            setLprofile3(hold[0]);
            setProfile3Icon(icons[Integer.parseInt(hold[1])]);
        }
    }

    public interface OnSendClick {
        public void doAction(String message, ObservableList<String> names);
    }

    public interface OnReadyClick {
        void doAction();
    }

    public interface ChangeScene {
        public void doAction();
    }
}
