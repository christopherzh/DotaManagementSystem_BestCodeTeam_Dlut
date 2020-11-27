package util;

import java.util.HashMap;

public class MapUtil {
    public HashMap<Integer,String> serverIdToName;
    public MapUtil (){
        this.serverIdToName =   new HashMap<>();
        serverIdToName.put(1,Server.SERVER_ONE.getServerName());
        serverIdToName.put(2,Server.SERVER_TWO.getServerName());
        serverIdToName.put(3,Server.SERVER_THREE.getServerName());
    }
    public String getServerName(int i){
        return serverIdToName.get(i);
    }

}
