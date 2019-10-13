package Main;

import Object.Users;
import org.apache.log4j.Logger;

public class Main {

    public final static Logger LOGGER = Logger.getLogger(Server_Socket.class);

    public static void main(String[]args){
        ConectDB.getSessionFactory();
        Server_Socket.serverSocket();
    }
}
