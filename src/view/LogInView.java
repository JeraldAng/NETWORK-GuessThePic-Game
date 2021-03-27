package view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;

public class LogInView extends AnchorPane {

    private OnAlvinClick onAlvinClick;
    private OnTheodoreClick onTheodoreClick;
    private OnSimonClick onSimonClick;
    private OnPlayClick onPlayClick;
    private ChangeScene changeScene;
    private DropShadow ds3;
    private DropShadow ds2;
    private ImageView choose;
    private ImageView username;
    private boolean select = false;
    Image[] icons;

    private TextField name;
    int iconNum;

    public LogInView() {
        icons = new Image[3];
        iconNum = 0;
        this.init();
    }

    public void init() {
        //Background Image
        BackgroundImage film = new BackgroundImage(new Image("file:film.png", 550.0D, 700.0D, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.setBackground(new Background(new BackgroundImage[]{film}));

        //Logo
        Image logoIcon = new Image("file:final_logo.png", 200D, 150D, true, true);
        ImageView logo = new ImageView();
        logo.setImage(logoIcon);
        AnchorPane.setTopAnchor(logo, 55.0D);
        AnchorPane.setLeftAnchor(logo, 190.0D);

        //Username
        Image usernameIcon = new Image("file:username.png", 160D, 160D, true, true);
        username = new ImageView();
        username.setImage(usernameIcon);
        AnchorPane.setTopAnchor(username, 235.0D);
        AnchorPane.setLeftAnchor(username, 80.0D);

        //Textfield Name
        this.name = new TextField();
        name.setPromptText("Username");
        name.setAlignment(Pos.CENTER);
        name.setFont(Font.font("Courier New", FontWeight.BOLD, 20.0D));
        name.setPrefSize(200, 40);
        AnchorPane.setTopAnchor(name, 240.0D);
        AnchorPane.setRightAnchor(name, 100.0D);

        //Choose Icon
        Image chooseIcon = new Image("file:choose.png", 160D, 160D, true, true);
        choose = new ImageView();
        choose.setImage(chooseIcon);
        AnchorPane.setBottomAnchor(choose, 240.0D);
        AnchorPane.setLeftAnchor(choose, 85.0D);

        //DropShadow
        DropShadow ds1 = new DropShadow();
        ds1.setOffsetY(5.0);
        ds1.setOffsetX(5.0);
        ds1.setColor(Color.BLACK);

        ds2 = new DropShadow();
        ds2.setOffsetY(0.0);
        ds2.setOffsetX(0.0);
        ds2.setColor(Color.WHITE);

        ds3 = new DropShadow();
        ds3.setOffsetY(0.0);
        ds3.setOffsetX(0.0);
        ds3.setColor(Color.RED);

        //Alvin Icon
        Image alvinIcon = new Image("file:alvin.png", 120D, 130D, false, true);
        icons[0] = alvinIcon;
        ImageView alvin = new ImageView();
        alvin.setImage(alvinIcon);

        AnchorPane.setBottomAnchor(alvin, 100.0D);
        AnchorPane.setLeftAnchor(alvin, 85.0D);

        //Theodore Icon
        Image theodoreIcon = new Image("file:theodore.png", 120D, 130D, false, true);
        icons[1] = theodoreIcon;
        ImageView theodore = new ImageView();
        theodore.setImage(theodoreIcon);

        AnchorPane.setBottomAnchor(theodore, 100.0D);
        AnchorPane.setLeftAnchor(theodore, 195.0D);

        //Simon Icon
        Image simonIcon = new Image("file:simon.png", 120D, 130D, false, true);
        icons[2] = simonIcon;
        ImageView simon = new ImageView();
        simon.setImage(simonIcon);

        AnchorPane.setBottomAnchor(simon, 100.0D);
        AnchorPane.setRightAnchor(simon, 105.0D);

        //Choose Icon
        alvin.setOnMouseClicked(e -> {
            onAlvinClick.doAction(alvinIcon);
            select = true;
            alvin.setEffect(ds1);
            theodore.setEffect(ds2);
            simon.setEffect(ds2);
            iconNum = 0;
        });

        theodore.setOnMouseClicked(e -> {
            onTheodoreClick.doAction(theodoreIcon);
            select = true;
            theodore.setEffect(ds1);
            alvin.setEffect(ds2);
            simon.setEffect(ds2);
            iconNum = 1;
        });

        simon.setOnMouseClicked(f -> {
            onSimonClick.doAction(simonIcon);
            select = true;
            alvin.setEffect(ds2);
            theodore.setEffect(ds2);
            simon.setEffect(ds1);
            iconNum = 2;
        });

        //Play
        Image playIcon = new Image("file:play.png", 160D, 160D, true, true);
        ImageView play = new ImageView();
        play.setImage(playIcon);
        /***********************
          There's still error...
         **********************/
       play.setOnMouseClicked(e -> {
            /*if (name.getText().isEmpty()) {
                name.setEffect(ds3);
                try {
                    wait();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                    notifyAll();
                }
            }*/

            onPlayClick.doAction(name.getText(), iconNum);
        });
        AnchorPane.setBottomAnchor(play, 35.0D);
        AnchorPane.setLeftAnchor(play, 180.0D);

        this.getChildren().addAll( new Node[]{
                logo, username, name, choose, alvin, theodore, simon, play
        });
    }

    public void setOnAlvinClick(OnAlvinClick var1) {
        this.onAlvinClick = var1;
    }

    public void setOnTheodoreClick(OnTheodoreClick var1) {
        this.onTheodoreClick = var1;
    }

    public void setOnSimonClick(OnSimonClick var1) {
        this.onSimonClick = var1;
    }

    public void setOnPlayClick(OnPlayClick var1) {
        this.onPlayClick = var1;
    }

    public TextField getName() {
        return name;
    }

    public void setChangeScene (ChangeScene changeScene) {
        this.changeScene = changeScene;
    }

    public interface OnAlvinClick {
        void doAction(Image var1);
    }

    public interface OnTheodoreClick {
        void doAction(Image var1);
    }

    public interface OnSimonClick {
        void doAction(Image var1);
    }

    public interface OnPlayClick {
        void doAction(String name, int icon);
    }

    public interface ChangeScene {
        public void doAction();
    }

    public DropShadow getDs3() {
        return ds3;
    }

    public DropShadow getDs2() {
        return ds2;
    }

    public ImageView getChoose() {
        return choose;
    }

    public ImageView getUsername() {
        return username;
    }

    public boolean isSelect() {
        return select;
    }

    public Image[] getIcons() {
        return icons;
    }
}
