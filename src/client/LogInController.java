package client;

import javafx.scene.image.Image;
import view.LogInView;

import java.io.IOException;

public class LogInController {
    private MainController mainController;
    private LogInView loginview;
    private Image icon;

    private String username;

    public LogInController(MainController mainController) {
        this.mainController = mainController;

        this.loginview = new LogInView();

        this.setViewControls();
    }

    public void setIcon(Image var1) {
        this.icon = var1;
    }

    public Image getIcon() {
        return this.icon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setViewControls() {

        this.loginview.setOnAlvinClick(new LogInView.OnAlvinClick() {
            public void doAction(Image var1) {
                LogInController.this.setIcon(var1);
                //System.out.println("You chose Alvin");
            }
        });

        this.loginview.setOnTheodoreClick(new LogInView.OnTheodoreClick(){
            public void doAction(Image var1) {
                LogInController.this.setIcon(var1);
                //System.out.println("You chose Theodore");
            }
        });

        this.loginview.setOnSimonClick(new LogInView.OnSimonClick(){
            public void doAction(Image var1) {
                LogInController.this.setIcon(var1);
                //System.out.println("You chose Simon");
            }
        });

        this.loginview.setOnPlayClick(new LogInView.OnPlayClick() {
            @Override
            public void doAction(String name, int icon) {
            String temp = loginview.getName().getText();

            if(loginview.isSelect() && (!(temp.isEmpty()) && temp != null && !(temp.trim().isEmpty()))){
                try {
                    mainController.getClient().connect(name, icon);
                    mainController.changeScene(2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if(loginview.isSelect() == false && (!(temp.isEmpty()) && temp != null && !(temp.trim().isEmpty()))){
                loginview.getChoose().setEffect(loginview.getDs3());
                loginview.getUsername().setEffect(loginview.getDs2());
            }else if(loginview.isSelect() && (!(temp.isEmpty()) && temp != null && !(temp.trim().isEmpty())) == false){
                loginview.getUsername().setEffect(loginview.getDs3());
                loginview.getChoose().setEffect(loginview.getDs2());
            }else if(loginview.isSelect() == false && (!(temp.isEmpty()) && temp != null && !(temp.trim().isEmpty())) == false){
                loginview.getUsername().setEffect(loginview.getDs3());
                loginview.getChoose().setEffect(loginview.getDs3());
            }
            }
        });
    }

    public LogInView getLogInView() {
        return loginview;
    }
}
