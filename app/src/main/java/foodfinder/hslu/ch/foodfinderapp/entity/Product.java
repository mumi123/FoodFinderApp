package foodfinder.hslu.ch.foodfinderapp.entity;

import java.io.Serializable;

public class Product implements Serializable{

    static final long serialVersionUID = -50077493051991107L;

    private int id;
    private String name;
    private Category category;

    public Product(){};

    public Product(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
