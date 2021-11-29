package com.DAO.Objects;

import javax.persistence.*;

@Table(name = "cart", indexes = {
        @Index(name = "fk_article_has_cart_article1_idx", columnList = "idArticle"),
        @Index(name = "fk_article_has_cart_cart1_idx", columnList = "idUser")
})
@Entity
public class Cart {

    // DPE - Si tu ne crées pas le constructeur vide, tu en as par default donc pas besoin de l'écrire ;)
    public Cart() {
    }

    public Cart( Integer quantity ) {
        this.quantity = quantity;
    }

    @EmbeddedId
    private CartId id;

    @Column(name = "quantity")
    private Integer quantity;

    // DPE - Est-ce que vous connaissez lombok https://projectlombok.org/ ?

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