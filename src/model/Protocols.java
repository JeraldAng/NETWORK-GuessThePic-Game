package model;

public enum Protocols {
    CONNECT("I am Connected", "Client has connected to server"),
    READY("The Game will start soon", "Client is ready"),
    UPDATE("Requesting update", "Client is requesting update"),
    NAME("Request name", "Client is requesting names"),
    PICTURE("Requesting Picture", "Client is requesting Pictures"),
    READYCHECK("Checking Ready Players", "Client wants to see the ready players"),
    MESSAGE("SEND MESSAGE", "Client wants to send a message"),
    REMOVE("REMOVE CLIENT", "Clients wants to exit");

    private String protocol;
    private String description;

    Protocols(String protocol, String description) {
        this.protocol = protocol;
        this.description = description;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getDescription() {
        return description;
    }

    public static Protocols getProtocols(String input){
        for(Protocols p : Protocols.values()){
            if(input.equals(p.getProtocol())){
                return p;
            }
        }
        return null;
    }


}
