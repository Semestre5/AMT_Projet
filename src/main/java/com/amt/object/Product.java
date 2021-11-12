package com.amt.object;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


public class Product {

    public static final Product TEST_PRODUCT1 = new Product(1, "Calypso Theme", 49.99,
            "this a nice theme",  "./resources/images/product1-1.jpg", 0, 0);
    public static final Product TEST_PRODUCT2 = new Product(2,"Cobalt Theme", 49.99,
            "this a nice theme",  "./resources/images/product2-2.jpg", 0, 1);
    @Getter @Setter @NonNull private int id;
    @Getter @Setter @NonNull private String name;
    @Getter @Setter @NonNull private double price;
    @Getter @Setter @NonNull private String description;
    @Getter @Setter @NonNull private String urlToImage; //use URL later
    @Getter @Setter @NonNull private int status; //use Enum when defined
    @Getter @Setter @NonNull private int quantity;

    public Product(int id, String name, double price, String description, String urlToImage, int status, int quantity) {
        this.id = id;
        this.name= name;
        this.price = price;
        this.description = description;
        this.urlToImage = urlToImage;
        this.status = status;
        this.quantity = quantity;
    }

    public static void main(String[] args){
        List<Product> pl = new ArrayList<>();
        pl.add(TEST_PRODUCT1);
        System.out.println(pl);
    }

}
