package foodfinder.hslu.ch.foodfinderapp.communication;

import java.io.IOException;
import java.net.Socket;

public class TCPClient implements Runnable{

    private Socket tcpClient;
    private final String ipFromServer;
    private final int port;

    public TCPClient(String ipFromServer, int port) {
        this.ipFromServer = ipFromServer;
        this.port = port;
    }

    @Override
    public void run() {
        //Verbinde mit Server (Smart Glasses)
        try {
            System.out.println("Try to connect with server...");
            this.tcpClient = new Socket(this.ipFromServer, this.port);
            System.out.println("Connected with Server!");
        } catch (IOException ex) {
            System.err.println("Fehler beim verbinden mit Server: "+ex);
        }
    }



}
