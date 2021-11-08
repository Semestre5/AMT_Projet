package com.amt.product;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class Product {

    @Getter @Setter @NonNull private int price;
    @Getter @Setter @NonNull private String description;
    @Getter @Setter @NonNull private String urlToImage; //use URL later
    @Getter @Setter @NonNull private int status; //use Enum when defined
    @Getter @Setter @NonNull private int quantity;

    public Product(int price, String description, String urlToImage, int status, int quantity) {
        this.price = price;
        this.description = description;
        this.urlToImage = urlToImage;
        this.status = status;
        this.quantity = quantity;
    }

    public static String exportProductPage(/*Add Product parameter when DB connection done*/){
        return "";
    }

    public static String exportProductSnippet(){
        return "";
    }


}
