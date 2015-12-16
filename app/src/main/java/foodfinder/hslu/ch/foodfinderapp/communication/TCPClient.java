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

    private TCPClient() {
    }

    public static synchronized TCPClient getInstance() {
        if (instance == null){
            instance = new TCPClient();
            tcpClient = new Socket();
        }
        return instance;
    }

    public synchronized static void resetInstance(){
        instance = null; //reset the instance
    }

    @Override
    public void run() {
        //Verbinde mit Server (Smart Glasses)
        doConnect();
    }

    public void send(Product product){
        doConnect();
        try{
            ObjectOutputStream outToServer = new ObjectOutputStream(getInstance().getTcpClient().getOutputStream());
            outToServer.writeObject(product);
            outToServer.flush();
        }catch (IOException ex){
            System.out.println("Fehler beim senden des Objekts: " + ex);
        }
    }

    public Boolean receive(){
        Boolean foundProduct = false;
        doConnect();
        try{
            ObjectInputStream inFromServer = new ObjectInputStream(getInstance().getTcpClient().getInputStream());
            foundProduct = (Boolean) inFromServer.readObject();
        }catch(IOException ex){
            System.out.println("Fehler beim empfangen des Objekts: "+ex);
        }catch(ClassNotFoundException ex){
            System.out.println("Fehler Klasse nicht gefunden: "+ex);
        }
        return foundProduct;
    }

    public Socket getTcpClient() {
        return tcpClient;
    }

    public void doConnect(){
        try{
            if(getTcpClient() != null){
                if(!getTcpClient().isConnected()) {
                    getTcpClient().connect(new InetSocketAddress(Settings.ipServerAdress, Settings.port), 100);
                }
            }else {
                getTcpClient().connect(new InetSocketAddress(Settings.ipServerAdress, Settings.port), 100);
            }
        }catch (IOException ex){
            System.err.println("Timeout. Server ist nicht online!"+ex);
        }

    }

}
