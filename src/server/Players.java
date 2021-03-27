package server;

import java.net.InetAddress;

public class Players {
    private InetAddress address;
    private int port;
    private String name;
    private int id;
    private int score;
    private int icon;
    private boolean ready;

    public Players(InetAddress address, int port, int id, String name, int icon){
        this.address = address;
        this.port = port;
        this.id = id;
        this.name = name;
        this.score = 0;
        this.icon = icon;
        this.ready = false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReady(){
        ready = true;
    }

    public boolean isReady() {
        return ready;
    }

    public void score() {this.score++;}

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }
}
