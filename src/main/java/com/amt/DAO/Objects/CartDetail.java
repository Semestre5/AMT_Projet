package com.amt.DAO.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "article_cart", indexes = {
        @Index(name = "fk_article_has_cart_article1_idx", columnList = "idActicle"),
        @Index(name = "fk_article_has_cart_cart1_idx", columnList = "idCart")
})
@Entity
@Getter
@Setter
@ToString
public class CartDetail {
    public CartDetail( Integer quantity ) {
        this.quantity = quantity;
    }

    @EmbeddedId
    private CartDetailId id;

    private Integer quantity;

    public CartDetail() {

    }

    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity){this.quantity =quantity; }
    public CartDetailId getId() {
        return id;
    }
}