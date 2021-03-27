package client;

import javafx.application.*;
import javafx.stage.Stage;
import java.net.InetAddress;
import java.util.Scanner;

public class Client extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter IP Address: ");
        String address = sc.nextLine();
        InetAddress add = InetAddress.getByName(address);
        System.out.println(add.toString());
        new MainController(primaryStage, add);
    }

    public static void main(String args[]){
        launch(args);
    }
}
