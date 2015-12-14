package foodfinder.hslu.ch.foodfinderapp.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;

import foodfinder.hslu.ch.foodfinderapp.entity.Product;
import foodfinder.hslu.ch.foodfinderapp.settings.Settings;

public class TCPClient implements Runnable{

    private static TCPClient instance;

    private Socket tcpClient;
    private boolean send = false;

    private TCPClient() {
    }

    public static synchronized TCPClient getInstance() {
        if (instance == null){
            instance = new TCPClient();
        }
        return instance;
    }

    synchronized static void resetInstance(){
        instance = null; //reset the instance
    }

    @Override
    public void run() {
        //Verbinde mit Server (Smart Glasses)
        try {
            System.out.println("try to connect...");

            this.tcpClient = new Socket();

            SocketAddress sockaddr = new InetSocketAddress(Settings.ipServerAdress, Settings.port);

            tcpClient.connect(sockaddr, 5000);

            setSend(true); //Sendenflag setzen
            System.out.println("connected...");

        } catch (SocketTimeoutException ex){
            System.err.println("Timeout. Server ist nicht online!"+ex);
        } catch (IOException ex) {
            System.err.println("Fehler beim verbinden mit Server: "+ex);
        }
    }

    public void send(Product product){

        try{
            ObjectOutputStream outToServer = new ObjectOutputStream(this.tcpClient.getOutputStream());
            outToServer.writeObject(product);
            this.tcpClient.close();
        }catch (IOException ex){
            System.out.println("Fehler beim senden des Objekts: "+ex);
        }
    }

    public Product receive(){

        Product prd = null;

        try{
            ObjectInputStream inFromServer = new ObjectInputStream(this.tcpClient.getInputStream());

            prd = (Product) inFromServer.readObject();

        }catch(IOException ex){
            System.out.println("Fehler beim empfangen des Objekts: "+ex);
        }catch(ClassNotFoundException ex){
            System.out.println("Fehler Klasse nicht gefunden: "+ex);
        }
        return prd;
    }

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }

    public Socket getTcpClient() {
        return tcpClient;
    }

    public void setTcpClient(Socket tcpClient) {
        this.tcpClient = tcpClient;
    }
}
