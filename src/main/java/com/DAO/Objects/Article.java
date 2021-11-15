package com.DAO.Objects;

import com.amt.object.Product;
import org.hibernate.annotations.Proxy;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "article")
@Entity
@Proxy(lazy=false)
public class Article {
    public static Article TEST_ARTICLE1 = new Article( new BigDecimal("49.99"), "this a nice theme",
            "Calypso Theme", 1, "./resources/images/product1-1.jpg");
    public static Article TEST_ARTICLE2 = new Article( new BigDecimal("39.99"), "this a nice theme",
            "Mega cool Theme", 0, "./resources/images/product2-2.jpg");

    public Article() {

    }
    public Article( BigDecimal price, String description, String name, Integer quantity, String link ) {
        this.price = price;
        this.description = description;
        this.name = name;
        this.quantity = quantity;
        this.link = link;
    }

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
}