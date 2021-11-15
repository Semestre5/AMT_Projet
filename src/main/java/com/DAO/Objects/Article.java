package com.DAO.Objects;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "article")
@Entity
@Proxy(lazy = false)
public class Article {
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
    private Integer id;

    @Column(name = "price", precision = 8, scale = 2)
    private BigDecimal price;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "link", length = 200)
    private String link;


    public Article() {

    }
    public String getLink() {
        return link;
    }

    public void setLink( String link ) {
        this.link = link;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity( Integer quantity ) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice( BigDecimal price ) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }
}