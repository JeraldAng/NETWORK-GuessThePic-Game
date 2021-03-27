package server;

import client.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Pictures;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Timer;

public class Server {
    public static final int portNumber = 8888;
    DatagramSocket socket;
    DatagramPacket packet;
    volatile boolean running;
    ServerModel model;
    private ServerListener listener;
    private ServerGameLoop gameLoop;
    byte[] buffer;
    ArrayList<Players> clients;
    Timer timer;
    int countDown;
    String roundWinner;
    String win;
    int round;
    Thread gametimer;
    boolean x;

    ArrayList<String> messageQueue;

    public Server() throws SocketException {
        model = new ServerModel();
        clients = new ArrayList<>();
        messageQueue = new ArrayList<>();
        listener = new ServerListener(this);
        gameLoop = new ServerGameLoop(this);
        socket = new DatagramSocket(portNumber);
        running = true;
        roundWinner = null;
        win = null;
        round = 1;
        gametimer = new Thread(gameLoop);
        x = false;
        System.out.println("Server has Successfully Launched!\nClients can connect now!");
    }

    public static void main(String args[]) throws SocketException {
        new Server().run();
    }

    public void run(){
        Thread tListener = new Thread(listener);
        tListener.start();

        while(running){
            try {
                synchronized (this){
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void receive() throws IOException {
        buffer = new byte[4096];
        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
    }

    public void send(String s, InetAddress address, int port) throws IOException {
        buffer = s.getBytes();
        packet = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(packet);
    }

    public String getMessage(){
        return new String(packet.getData(), 0, packet.getLength());
    }

    public synchronized void addClient(InetAddress address, int port) throws IOException {
        receive();
        String temp[] = getMessage().split(",");
        int id = clients.size();
        //System.out.println(temp[0] + " " + id);
        clients.add(new Players(address, port, id, temp[0] , Integer.parseInt(temp[1])));
        send(String.valueOf(id), address, port);
    }

    public synchronized void clientReady() throws IOException {
        receive();
        int id = Integer.parseInt(getMessage());
        clients.get(id).setReady();
    }

    public boolean hasClients(){
        synchronized (clients){
            return clients.size() > 0;
        }
    }

    public boolean allReady(){
        synchronized (clients){
            for(Players p: clients){
                if(!(p.isReady())){
                    return false;
                }
            }
            return true;
        }
    }

    public boolean hasWinner() {
        synchronized (clients){
            for(Players p: clients){
                if(p.getScore() == 3){
                    win = p.getName();
                    return true;
                }
            }
            return false;
        }
    }


    public boolean hasRoundWinner() {
        return roundWinner != null;
    }

    public void sendPicture(InetAddress address, int port) throws IOException {
        send(model.get().toString(), address, port);
    }

    public void sendName(InetAddress address, int port) throws IOException{
        StringBuilder str = new StringBuilder();
        for(Players p: clients){
            str.append(p.getName()).append(",").append(p.getIcon()).append(";");
        }
        send(str.toString(), address, port);
    }

    public void sendReadyCheck(InetAddress address, int port) {
        for(Players p: clients){
            if(!p.isReady()){
                try {
                    send("FALSE", address, port);
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            send("TRUE", address, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendUpdates(InetAddress address, int port){
        if(!x){
            x = true;
            startThread();
        }
        StringBuilder str = new StringBuilder();
        for(Players p: clients){
            str.append(p.getName()).append(",").append(p.getIcon()).append(",").append(p.getScore()).append(";");
        }
        str.append("#").append(round).append("#").append(countDown).append("#").append(win).append("#").append(hasWinner());
        try {
            send(str.toString(), address, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(InetAddress address, int port) {
        String temp = null;
        try {
            receive();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] check = getMessage().split(",");

        if(check[2].equals("true")){
            roundWinner = check[0];
            for(Players p : clients){
                if(p.getName().equalsIgnoreCase(check[0])) {
                    p.score();
                }
            }
            messageQueue.clear();
        }else{
            StringBuilder str = new StringBuilder();
            str.append(check[0]).append(": ").append(check[1]);
            temp = str.toString();
            messageQueue.add(str.toString());
        }

        StringBuilder str1 = new StringBuilder();
        for(String st : messageQueue){
            str1.append(st).append(";");
        }
        String tempo = str1.toString();

        try {
            send(tempo, address, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startThread(){
        gametimer.start();
    }

    public void stopThread(){
        if(gametimer.isAlive())
            gametimer.interrupt();
    }

    public void removeClients(){
        clients.clear();
    }

    public void exit(){
        System.exit(0);
    }
}
