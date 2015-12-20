package foodfinder.hslu.ch.foodfinderapp.communicationInterface;

import foodfinder.hslu.ch.foodfinderapp.entity.Product;

public interface Communication {
    void send(Product product);
    Boolean receive();
}
