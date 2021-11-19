package com.DAO.Objects;

import org.hibernate.annotations.Proxy;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//TODO: peut etre enlever les @SETTER parce qu'on peut modifier depuis le navigateur les trucs de la DB
@Table(name = "article")
@Entity
@Proxy(lazy=false)
public class Article {
    public static Article TEST_ARTICLE1 = new Article( new BigDecimal("49.99"), "this a nice theme",
            "Calypso Theme", 1, "./resources/images/product1-1.jpg");
    public static Article TEST_ARTICLE2 = new Article( new BigDecimal("0"), "this a nice theme",
            "Mega cool Theme", 0, "./resources/images/product2-2.jpg");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter @Setter
    private Integer id;

    @Column(name = "price", precision = 8, scale = 2)
    @Getter @Setter
    private BigDecimal price;

    @Lob
    @Column(name = "description")
    @Getter @Setter
    private String description;

    @Column(name = "name", nullable = false, length = 45)
    @Getter @Setter
    private String name;

    @Column(name = "quantity", nullable = false)
    @Getter @Setter
    private Integer quantity;

    @Column(name = "link", length = 200)
    @Getter @Setter
    private String link;

    /*
    @ElementCollection
    @Getter @Setter
    private List<Integer> categoriesID;

     */

    public Article() {

    }
    public Article( BigDecimal price, String description, String name, Integer quantity, String link ) {
        this.price = price;
        this.description = description;
        this.name = name;
        this.quantity = quantity;
        this.link = link;
        /*
        this.categoriesID = new ArrayList<>();
        this.categoriesID.add(1);
        this.categoriesID.add(2);
        */

    }
//TODO bouger plus tard
    public boolean isSellable(){
        if(this.price == null)
            return false;
        else {
            BigDecimal zero =new BigDecimal("0");
            return !this.price.equals(zero);
        }

    }
}