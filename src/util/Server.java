package util;

public enum Server {
    SERVER_ONE("SouthEast_Asia",1),SERVER_TWO("China",2),SERVER_THREE("North America",3);
    private String serverName;
    private int serverId;
    private Server(String serverName,int serverId){
        this.serverId = serverId;
        this.serverName = serverName;
    }
    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    @Override
    public String toString() {
        return "Server{" +
                "district:" + serverName  +
                '}';
    }
}
