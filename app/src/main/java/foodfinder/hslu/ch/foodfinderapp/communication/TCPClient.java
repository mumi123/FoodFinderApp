package foodfinder.hslu.ch.foodfinderapp.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import foodfinder.hslu.ch.foodfinderapp.entity.Product;
import foodfinder.hslu.ch.foodfinderapp.settings.Settings;

public class TCPClient implements Runnable{

    private static TCPClient instance;
    private static Socket tcpClient;
    private static SocketAddress sockaddr;

    private TCPClient() {
    }

    public static synchronized TCPClient getInstance() {
        if (instance == null){
            instance = new TCPClient();
            tcpClient = new Socket();
            sockaddr = new InetSocketAddress(Settings.ipServerAdress, Settings.port);;
        }
        return instance;
    }

    public synchronized static void resetInstance(){
        instance = null; //reset the instance
    }

    @Override
    public void run() {
        //Verbinde mit Server (Smart Glasses)

        //tcpClient = new Socket();

        try {
            if(getTcpClient() != null){
                if(!getTcpClient().isConnected()) {
                    getTcpClient().connect(getSockaddr(), 100);
                }
            }else {
                getTcpClient().connect(getSockaddr(), 100);
            }

            /*
            this.tcpClient = new Socket();
            this.tcpClient.connect(sockaddr, 5000);
            setSend(true); //Sendenflag setzen
            this.tcpClient.setKeepAlive(true);
            */
            /*
            if(this.tcpClient != null){
                if(!this.tcpClient.isConnected()) {
                    this.tcpClient.connect(this.sockaddr, 5000);
                    setSend(true); //Sendenflag setzen
                }
            }else {
                this.tcpClient = new Socket();
                this.tcpClient.connect(sockaddr, 5000);
                setSend(true); //Sendenflag setzen
            }
            */
        } catch (SocketTimeoutException ex){
            System.err.println("Timeout. Server ist nicht online!"+ex);
        } catch (IOException ex) {
            System.err.println("Fehler beim verbinden mit Server: "+ex);
        }
    }

    public void send(Product product){

        try{
            ObjectOutputStream outToServer = new ObjectOutputStream(getInstance().getTcpClient().getOutputStream());
            outToServer.writeObject(product);
        }catch (IOException ex){
            System.out.println("Fehler beim senden des Objekts: "+ex);
        }
    }

    public Boolean receive(){
        Boolean foundProduct = false;
        try{
            ObjectInputStream inFromServer = new ObjectInputStream(getInstance().getTcpClient().getInputStream());
            foundProduct = (Boolean) inFromServer.readObject();
        }catch(IOException ex){
            System.out.println("Fehler beim empfangen des Objekts: "+ex);
        }catch(ClassNotFoundException ex){
            System.out.println("Fehler Klasse nicht gefunden: "+ex);
        }

        try {
            getInstance().getTcpClient().close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return foundProduct;
    }

    public static SocketAddress getSockaddr() {
        return sockaddr;
    }

    public Socket getTcpClient() {
        return tcpClient;
    }

}
