package com.DAO.Objects;

import lombok.Builder;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "article")
@Entity
@Proxy(lazy = false)
public class Article {

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

    public Article(BigDecimal price, String description, String name, Integer quantity, String link){
        this.price = (price.intValue() >= 0) ? price : BigDecimal.valueOf(0);
        this.description = description;
        this.name = name;
        this.quantity = quantity >= 0 ? quantity : 0;
        this.link = link;
    }

    public Article() {

    }

    public boolean isSellable(){
        if(this.price == BigDecimal.valueOf(0) || this.quantity == 0){
            return false;
        }else{
            return true;
        }
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