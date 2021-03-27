package view;

import client.MainGameController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainGameView extends AnchorPane {

    /**************
        Players
     *************/
    private ImageView profile1;
    private ImageView profile2;
    private ImageView profile3;
    private Label lprofile1;
    private Label lprofile2;
    private Label lprofile3;
    private Label lscores1;
    private Label lscores2;
    private Label lscores3;

    /**************
          Game
     *************/
    private Label lround;
    private Label ltime;
    private ImageView picture1;
    private ImageView picture2;
    private ImageView picture3;
    private ImageView picture4;

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

    private String msg;
    private Image[] icons;

    ImageView flower;
    ImageView animal;
    ImageView fruit;
    DropShadow ds;
    DropShadow ds1;

    MainGameController view;

    public MainGameView(Image[] icons, MainGameController view) {
        this.icons = icons;
        this.view = view;
        this.init();
    }

    public void init() {
        //Background Image
        BackgroundImage bg = new BackgroundImage(new Image("file:bgmain.jpg", 1200.0D, 700.0D, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(new BackgroundImage[]{bg}));

        ds = new DropShadow();
        ds.setOffsetY(0.0);
        ds.setOffsetX(0.0);
        ds.setColor(Color.RED);

        ds1 = new DropShadow();
        ds1.setOffsetY(0.0);
        ds1.setOffsetX(0.0);
        ds1.setColor(Color.WHITE);

        /*************************
         Left Side Upper (Profile)
         ************************/
        //Label for Player 1
        this.lprofile1 = new Label("");
        lprofile1.setFont(Font.font("Courier New", FontWeight.BOLD, 30.0D));
        lprofile1.setPrefSize(270, 50);
        lprofile1.setAlignment(Pos.CENTER);
        this.setTopAnchor(lprofile1, 05.0D);
        this.setLeftAnchor(lprofile1, 20.0D);

        //Profile 1
        this.profile1 = new ImageView();
        this.setTopAnchor(profile1, 50.0D);
        this.setLeftAnchor(profile1, 05.0D);

        //Score:
        Label lscore1 = new Label("Score:");
        lscore1.setFont(Font.font("Courier New", FontWeight.BOLD, 25.0D));
        lscore1.setPrefSize(100, 05.0D);
        this.setTopAnchor(lscore1, 65.0D);
        this.setLeftAnchor(lscore1, 130.0D);

        //Label for Score1
        this.lscores1 = new Label("12");
        lscores1.setFont(Font.font("Courier New", FontWeight.BOLD, 50.0D));
        lscores1.setPrefSize(270, 50);
        lscores1.setAlignment(Pos.CENTER);
        this.setTopAnchor(lscores1, 100.0D);
        this.setLeftAnchor(lscores1, 40.0D);

        //Label for Player 2
        this.lprofile2 = new Label("");
        lprofile2.setFont(Font.font("Courier New", FontWeight.BOLD, 30.0D));
        lprofile2.setPrefSize(270, 50);
        lprofile2.setAlignment(Pos.CENTER);
        this.setTopAnchor(lprofile2, 05.0D);
        this.setLeftAnchor(lprofile2, 240.0D);

        //Profile 2
        this.profile2 = new ImageView();
        this.setTopAnchor(profile2, 50.0D);
        this.setLeftAnchor(profile2, 220.0D);

        //Score:
        Label lscore2 = new Label("Score:");
        lscore2.setFont(Font.font("Courier New", FontWeight.BOLD, 25.0D));
        lscore2.setPrefSize(100, 05.0D);
        this.setTopAnchor(lscore2, 65.0D);
        this.setLeftAnchor(lscore2, 345.0D);

        //Label for Score2
        this.lscores2 = new Label("12");
        lscores2.setFont(Font.font("Courier New", FontWeight.BOLD, 50.0D));
        lscores2.setPrefSize(270, 50);
        lscores2.setAlignment(Pos.CENTER);
        this.setTopAnchor(lscores2, 100.0D);
        this.setLeftAnchor(lscores2, 260.0D);

        //Label for Player 3
        this.lprofile3 = new Label("");
        lprofile3.setFont(Font.font("Courier New", FontWeight.BOLD, 30.0D));
        lprofile3.setPrefSize(270, 50);
        lprofile3.setAlignment(Pos.CENTER);
        this.setTopAnchor(lprofile3, 05.0D);
        this.setLeftAnchor(lprofile3, 460.0D);

        //Profile 3
        this.profile3 = new ImageView();
        this.setTopAnchor(profile3, 50.0D);
        this.setLeftAnchor(profile3, 440.0D);

        //Score:
        Label lscore3 = new Label("Score:");
        lscore3.setFont(Font.font("Courier New", FontWeight.BOLD, 25.0D));
        lscore3.setPrefSize(100, 05.0D);
        this.setTopAnchor(lscore3, 65.0D);
        this.setLeftAnchor(lscore3, 570.0D);

        //Label for Score3
        this.lscores3 = new Label("12");
        lscores3.setFont(Font.font("Courier New", FontWeight.BOLD, 50.0D));
        lscores3.setPrefSize(270, 50);
        lscores3.setAlignment(Pos.CENTER);
        this.setTopAnchor(lscores3, 100.0D);
        this.setLeftAnchor(lscores3, 480.0D);

        /*************************
          Left Side Lower (Game)
         ************************/
        AnchorPane agame = new AnchorPane();
        agame.setPrefSize(700, 455);
        HBox hgame = new HBox();
        hgame.setPrefSize(700, 455);

        this.setTopAnchor(hgame, 195.0D);
        this.setLeftAnchor(hgame, 15.0D);

        //Background Image
        BackgroundImage bggame = new BackgroundImage(new Image("file:border.png", 700.0D, 455.0D, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        hgame.setBackground(new Background(new BackgroundImage[]{bggame}));

        //Round
        Image roundIcon = new Image("file:round.png", 160D, 160D, true, true);
        ImageView round = new ImageView();
        round.setImage(roundIcon);
        agame.setTopAnchor(round, 30.0D);
        agame.setLeftAnchor(round, 50.0D);

        //Round Number
        this.lround = new Label("1");
        lround.setFont(Font.font("Courier New", FontWeight.BOLD, 40.0D));
        lround.setPrefSize(100, 25.0D);
        agame.setTopAnchor(lround, 30.0D);
        agame.setLeftAnchor(lround, 230.0D);

        //Time
        Image timeIcon = new Image("file:time.png", 160D, 160D, true, true);
        ImageView time = new ImageView();
        time.setImage(timeIcon);
        agame.setTopAnchor(time, 30.0D);
        agame.setRightAnchor(time, 170.0D);

        //Semi-Colon
        Label scolon = new Label(":");
        scolon.setFont(Font.font("Courier New", FontWeight.BOLD, 40.0D));
        scolon.setPrefSize(100, 25.0D);
        agame.setTopAnchor(scolon, 30.0D);
        agame.setRightAnchor(scolon, 50.0D);

        //Time Number
        this.ltime = new Label("12");
        ltime.setFont(Font.font("Courier New", FontWeight.BOLD, 40.0D));
        ltime.setPrefSize(100, 25.0D);
        agame.setTopAnchor(ltime, 30.0D);
        agame.setRightAnchor(ltime, 30.0D);

        //Category : Flowers
        Image flowerIcon = new Image("file:flowers.png", 200D, 200D, true, true);
        flower= new ImageView();
        flower.setImage(flowerIcon);
        agame.setTopAnchor(flower, 120.0D);
        agame.setLeftAnchor(flower, 70.0D);

        //Category : Animals
        Image animalIcon = new Image("file:animals.png", 200D, 200D, true, true);
        animal = new ImageView();
        animal.setImage(animalIcon);
        agame.setTopAnchor(animal, 215.0D);
        agame.setLeftAnchor(animal, 70.0D);

        //Category : Fruits
        Image fruitIcon = new Image("file:fruits.png", 200D, 200D, true, true);
        fruit = new ImageView();
        fruit.setImage(fruitIcon);
        agame.setTopAnchor(fruit, 310.0D);
        agame.setLeftAnchor(fruit, 70.0D);

        //Picture 1
        Image picture1Icon = new Image("file:dog1.png", 160D, 160D, true, true);
        this.picture1 = new ImageView();
        picture1.setImage(picture1Icon);

        HBox hp1 = new HBox();
        hp1.setPrefSize(160, 160);
        hp1.getChildren().add(picture1);
        hp1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(4))));

        agame.setTopAnchor(hp1, 90.0D);
        agame.setRightAnchor(hp1, 225.0D);

        //Picture 2
        Image picture2Icon = new Image("file:dog2.png", 160D, 160D, true, true);
        this.picture2 = new ImageView();
        picture2.setImage(picture2Icon);

        HBox hp2 = new HBox();
        hp2.setPrefSize(160, 160);
        hp2.getChildren().add(picture2);
        hp2.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(4))));

        agame.setTopAnchor(hp2, 90.0D);
        agame.setRightAnchor(hp2, 50.0D);

        //Picture 3
        Image picture3Icon = new Image("file:dog3.png", 160D, 160D, true, true);
        this.picture3 = new ImageView();
        picture3.setImage(picture3Icon);

        HBox hp3 = new HBox();
        hp3.setPrefSize(160, 160);
        hp3.getChildren().add(picture3);
        hp3.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(4))));

        agame.setTopAnchor(hp3, 260.0D);
        agame.setRightAnchor(hp3, 225.0D);

        //Picture 4
        Image picture4Icon = new Image("file:dog4.png", 160D, 160D, true, true);
        this.picture4 = new ImageView();
        picture4.setImage(picture4Icon);

        HBox hp4 = new HBox();
        hp4.setPrefSize(160, 160);
        hp4.getChildren().add(picture4);
        hp4.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(4))));

        agame.setTopAnchor(hp4, 260.0D);
        agame.setRightAnchor(hp4, 50.0D);

        agame.getChildren().addAll(new Node[] {
                round, lround, time, scolon, ltime,
                flower, animal, fruit,
                hp1, hp2, hp3, hp4
        });

        hgame.getChildren().add(agame);

        /**********************
         Right Side (Chatbox)
        *********************/
        AnchorPane achat = new AnchorPane();
        achat.setPrefSize(500, 650);
        HBox hchat = new HBox();
        hchat.setPrefSize(500, 650);

        this.setTopAnchor(hchat, 15.D);
        this.setRightAnchor(hchat, 0.D);

        //Background Image
        BackgroundImage phone = new BackgroundImage(new Image("file:phone.png", 500.0D, 650.0D, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        achat.setBackground(new Background(new BackgroundImage[]{phone}));

        // Client 1 Name
        this.name = new Label("Player 1");
        name.setTextFill(Color.WHITE);
        name.setFont(Font.font("Courier New", FontWeight.BOLD, 20.0D));
        achat.setTopAnchor(name, 8D);
        achat.setLeftAnchor(name, 210D);
        name.setVisible(false);

        //Display of Messages
        messages = FXCollections.observableArrayList();
        this.listView = new ListView<String>(messages);

        this.list = new VBox();
        list.getChildren().add(listView);
        list.setVgrow(listView, Priority.ALWAYS);
        list.setStyle("-fx-box-border: transparent;");
        list.setPrefSize(330, 355);

        HBox hlist = new HBox();
        hlist.setPrefSize(330, 355);
        hlist.getChildren().add(list);
        hlist.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(2))));

        achat.setTopAnchor(hlist, 60D);
        achat.setLeftAnchor(hlist, 88D);

        //Input Message
        this.message = new TextField();
        message.setPromptText("Text Message");
        message.setAlignment(Pos.CENTER);
        message.setFont(Font.font("Courier New", FontWeight.BOLD, 20.0D));
        message.setPrefSize(330, 50);
        message.setBackground(null);

        HBox hmessage = new HBox();
        hmessage.setPrefSize(330, 50);
        hmessage.getChildren().add(message);
        hmessage.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(5))));

        achat.setBottomAnchor(hmessage, 180.0D);
        achat.setLeftAnchor(hmessage, 88.0D);

        //SEND Icon
        this.sendIcon = new Image("file:send.png", 450.0D, 70.0D, true, true);
        this. send = new ImageView(sendIcon);
        send.setImage(sendIcon);
        send.setOnMouseClicked(e -> {
            msg = message.getText();
            onSendClick.doAction(msg, messages);
            message.clear();
        });

        achat.setBottomAnchor(send, 105.0D);
        achat.setLeftAnchor(send, 220.0D);

        achat.getChildren().addAll (
                name, hlist, hmessage, send
        );

        hchat.getChildren().add(achat);

        this.getChildren().addAll(new Node[] {
                lprofile1, lprofile2, lprofile3, profile1, profile2, profile3,
                lscore1, lscore2, lscore3, lscores1, lscores2, lscores3,
                hgame, hchat

        });

    }

    public void setImages(String category ,String[] images){
        Image[] imgs = new Image[4];
        for(int i = 0; i < 4 ; i++){
            imgs[i] = new Image("/" + images[i], 160D, 160D, true, true);
        }
        picture1.setImage(imgs[0]);
        picture2.setImage(imgs[1]);
        picture3.setImage(imgs[2]);
        picture4.setImage(imgs[3]);

        picture2.setVisible(false);
        picture3.setVisible(false);
        picture4.setVisible(false);

        lscores2.setVisible(false);
        lscores3.setVisible(false);

        if(!lprofile2.getText().isEmpty()){
            profile2.setVisible(true);
            lscores2.setVisible(true);
        }
        if(!lprofile3.getText().isEmpty()){
            profile3.setVisible(true);
            lscores3.setVisible(true);
        }

        switch(category){
            case "animals":
                animal.setEffect(ds);
                fruit.setEffect(ds1);
                flower.setEffect(ds1);
                break;
            case "fruits":
                animal.setEffect(ds1);
                fruit.setEffect(ds);
                flower.setEffect(ds1);
                break;
            case "flowers":
                animal.setEffect(ds1);
                fruit.setEffect(ds1);
                flower.setEffect(ds);
                break;
        }
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

    public void setOnSendClick (OnSendClick phone) {
        this.onSendClick = phone;
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

    public void setName(String names) {
        this.name.setText(names);
    }

    public void setLtime(String ltimes) {
        this.ltime.setText(ltimes);
    }

    public void setLround(String round){ this.lround.setText(round);}

    public void setLscores1(String score) {this.lscores1.setText(score);}

    public void setLscores2(String score) {this.lscores2.setText(score);}

    public void setLscores3(String score) {this.lscores3.setText(score);}

    /*public String getMsg() {
        return msg;
    }*/

    public void updateInformation(String[] players, String round, String countdown) {
        String[] hold = players[0].split(",");
        setLprofile1(hold[0]);
        setProfile1Icon(icons[Integer.parseInt(hold[1])]);
        setLscores1(hold[2]);

        if(players.length > 1){
            hold = players[1].split(",");
            setLprofile2(hold[0]);
            setProfile2Icon(icons[Integer.parseInt(hold[1])]);
            setLscores2(hold[2]);
        }

        if(players.length > 2){
            hold = players[2].split(",");
            setLprofile3(hold[0]);
            setProfile3Icon(icons[Integer.parseInt(hold[1])]);
            setLscores3(hold[2]);
        }

        setLround(round);
        setLtime(countdown);
    }

    public void open2() {
        picture2.setVisible(true);
        picture3.setVisible(false);
        picture4.setVisible(false);
    }

    public void open3() {
        picture2.setVisible(true);
        picture3.setVisible(true);
        picture4.setVisible(false);
    }

    public void open4() {
        picture2.setVisible(true);
        picture3.setVisible(true);
        picture4.setVisible(true);
    }

    public void displayWinner(String name){
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Photo-off");
            alert.setHeaderText(name + "has won the game!");
            alert.show();
            alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
                @Override
                public void handle(DialogEvent event) {
                    Platform.exit();
                    view.stopThread();
                    System.exit(0);
                }
            });
        });

    }

    public interface OnSendClick {
        public void doAction(String message, ObservableList<String> messages);
    }

    public ObservableList<String> getMessages() {
        return messages;
    }

    public void setMessages(ObservableList<String> messages) {
        this.messages = messages;
    }

    public ListView<String> getListView() {
        return listView;
    }
}
