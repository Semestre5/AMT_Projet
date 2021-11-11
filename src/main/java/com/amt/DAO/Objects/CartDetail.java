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
    @EmbeddedId
    private CartDetailId id;

    private Integer quantity;

    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public CartDetailId getId() {
        return id;
    }
}