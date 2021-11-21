package com.DAO.Objects;

import javax.persistence.*;

@Table(name = "cart", indexes = {
        @Index(name = "fk_article_has_cart_article1_idx", columnList = "idArticle"),
        @Index(name = "fk_article_has_cart_cart1_idx", columnList = "idUser")
})
@Entity
public class Cart {
    public Cart() {
    }

    public Cart( Integer quantity ) {
        this.quantity = quantity;
    }

    @EmbeddedId
    private CartId id;

    @Column(name = "quantity")
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity( Integer quantity ) {
        this.quantity = quantity;
    }

    public CartId getId() {
        return id;
    }

    public void setId( CartId id ) {
        this.id = id;
    }
}