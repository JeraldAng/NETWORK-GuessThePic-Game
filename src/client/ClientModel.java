package client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Pictures;
import model.Protocols;
import server.Server;
import view.LobbyView;
import view.MainGameView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ClientModel {
    private Pictures cPic;
    private DatagramSocket socket;
    private DatagramPacket packet;
    private byte[] buffer;
    private String name;
    private int id;
    private int icon;
    private InetAddress serverAddress;
    private MainGameView view;
    private LobbyView lview;
    String players[];
    int round;
    int countdown;

    ObservableList<String> messages;
    String message;

    public ClientModel(InetAddress serverAddress, MainGameView view, LobbyView lview) throws SocketException {
        this.serverAddress = serverAddress;
        this.view = view;
        this.lview = lview;
        this.messages = FXCollections.observableArrayList();
        cPic = new Pictures();
        socket = new DatagramSocket();
    }

    public void connect(String name, int icon) throws IOException {
        this.name = name;
        this.icon = icon;
        send(Protocols.CONNECT.getProtocol());
        send(name + "," + icon);
        receive();
        id = Integer.parseInt(getMessage());
    }

    public void ready() throws IOException {
        send(Protocols.READY.getProtocol());
        send(String.valueOf(id));
    }

    public void requestPicture(){
        try{
            send(Protocols.PICTURE.getProtocol());
            receive();
            String[] hold = getMessage().split(",");
            String[] images = new String[4];
            for(int i = 0; i < 4; i++){
                images[i] = hold[i+2];
            }
            cPic.setCategory(hold[0]);
            cPic.setImagepath(images);
            cPic.setWord(hold[1]);
            view.setImages(hold[0] ,images);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void requestNames(){
        try{
            send(Protocols.NAME.getProtocol());
            receive();
            players = getMessage().split(";");
            lview.updatePlayers(players);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void attach(MainGameView view){
        this.view = view;
    }

    public void receive() throws IOException {
        buffer = new byte[4096];
        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
    }

    public void send(String s) throws IOException {
        buffer = s.getBytes();
        packet = new DatagramPacket(buffer, buffer.length, serverAddress, Server.portNumber);
        socket.send(packet);
    }

    public String getMessage(){
        return new String(packet.getData(), 0, packet.getLength());
    }

    public boolean readyCheck() {
        try {
            send(Protocols.READYCHECK.getProtocol());
            receive();
            if(getMessage().equals("TRUE")){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void requestUpdate(){
        try{
            send(Protocols.UPDATE.getProtocol());
            receive();
            String temp[] = getMessage().split("#");
            players = temp[0].split(";");
            round = Integer.parseInt(temp[1]);
            countdown = Integer.parseInt(temp[2]);
            if(temp[3] != null && temp[4].equalsIgnoreCase("true"))
                view.displayWinner(temp[3]);
            view.updateInformation(players, temp[1], temp[2]);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void requestMessage(String name, String message) {
        try{
            send(Protocols.MESSAGE.getProtocol());
            Boolean temp = isGuessCorrect(message);
           // System.out.println(name + "%" + message + "%" + temp);
            send(name + "," + message + "," + temp);
            receive();
            //message = getMessage();
            String hold[] = getMessage().split(";");
            messages.clear();
            for(String s : hold){
                messages.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public boolean isGuessCorrect(String guess){
        if(guess.equalsIgnoreCase(cPic.getWord()) || guess.contains(cPic.getWord())){
            return true;
        }
        return false;
    }

    public ObservableList<String> getMessages() {
        return messages;
    }

    public String getMsg(){
        return message;
    }
}
