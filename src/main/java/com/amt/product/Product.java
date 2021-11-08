package com.amt.product;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class Product {

    @Getter @Setter @NonNull private String name;
    @Getter @Setter @NonNull private int price;
    @Getter @Setter @NonNull private String description;
    @Getter @Setter @NonNull private String urlToImage; //use URL later
    @Getter @Setter @NonNull private int status; //use Enum when defined
    @Getter @Setter @NonNull private int quantity;

    public Product(String name, int price, String description, String urlToImage, int status, int quantity) {
        this.name= name;
        this.price = price;
        this.description = description;
        this.urlToImage = urlToImage;
        this.status = status;
        this.quantity = quantity;
    }

    public static String exportProductPage(/*Add Product parameter when DB connection done*/){
        return "";
    }

    public String exportProductSnippet(){
        String buttonAddToCartString =  (this.quantity > 0) ? "                        <a class=\"btn-buynow\" href=\"#\">Add to cart</a>\n" : "";
        return "<div class=\"productbox\">\n" +
                "                    <div class=\"fadeshop\">\n" +
                "                        <div class=\"captionshop text-center\" style=\"display: none;\">\n" +
                "                            <h3>"+this.name+"</h3>\n" +
                "                            <p>\n" + this.description + "\n" +
                "                            </p>\n" +
                "                            <p>\n" +
                "                                <a href=\"#\" class=\"learn-more detailslearn\"><i class=\"fa fa-shopping-cart\"></i> Purchase</a>\n" +
                "                                <a href=\"#\" class=\"learn-more detailslearn\"><i class=\"fa fa-link\"></i> Details</a>\n" +
                "                            </p>\n" +
                "                        </div>\n" +
                "                        <span class=\"maxproduct\"><img src=\"" + this.urlToImage + "\" alt=\"\"></span>\n" +
                "                    </div>\n" +
                "                    <div class=\"product-details\">\n" +
                "                        <a href=\"#\">\n" +
                "                            <h1>"+this.name+"</h1>\n" +
                "                        </a>\n" +
                "                        <span class=\"price\">\n" +
                "\t\t\t\t\t        <span class=\"edd_price\">$" + this.price + "</span>\n" +
                "                        </span>\n" + buttonAddToCartString+
                "                    </div>\n" +
                "                </div>";
    }


}
