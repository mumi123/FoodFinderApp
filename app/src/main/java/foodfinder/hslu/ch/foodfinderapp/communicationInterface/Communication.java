package foodfinder.hslu.ch.foodfinderapp.communicationInterface;

import foodfinder.hslu.ch.foodfinderapp.entity.Product;

public interface Communication {
    public void connect(String ipAdresse, int port);
    public void disconnect();
    public void send(Product product);
    public void receive(Product product);
}
