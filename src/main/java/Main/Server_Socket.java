package Main;

import Service.Service;
import org.json.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

public class Server_Socket {
    private static Socket clientSocket; //сокет дл¤ общени¤
    private static ServerSocket server; // серверсокет

    private static InputStream sin;
    private static OutputStream sout;

    private static DataInputStream in;
    private static DataOutputStream out;



    public static void serverSocket() {
        try {
            server = new ServerSocket(8081);
            Main.LOGGER.info("Server is running!");
            while (true) {
                clientSocket = server.accept();
                Main.LOGGER.info("Client connection");
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            sin = clientSocket.getInputStream();
                            sout = clientSocket.getOutputStream();

                            in = new DataInputStream(sin);
                            out = new DataOutputStream(sout);

                            Service.func(new JSONObject(in.readUTF()));
                        } catch (IOException e) {
                            Main.LOGGER.error(e);
                        }

                    }
                }).start();
            }
        } catch (IOException e) {
            Main.LOGGER.error(e);
        }
    }

//    public static void message(Cat cat) {
//        try {
//            cat.JSONObject(new JSONObject(in.readUTF()));
//        } catch (IOException e) {
//            System.out.println("Error");
//        }
//        System.out.println(cat.toString());
//    }

    public static void getMessage(JSONObject obj) {
        try {
            out.writeUTF(obj.toString());
            out.flush();
        } catch (IOException e) {
            Main.LOGGER.error(e);
        } finally {
            try {
//                clientSocket.cl;
                in.close();
                out.close();
//                server.close();
            } catch (IOException e) {
                Main.LOGGER.error(e);
            }
        }
    }
}
