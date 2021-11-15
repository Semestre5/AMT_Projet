package com.amt.cart;

import com.DAO.Objects.Article;
import lombok.Getter;
import lombok.Setter;

public class CartProduct {
    @Getter
    private Article article;
    @Getter @Setter
    private int quantity;

    public CartProduct(Article article, int quantity) {
        this.article = article;
        this.quantity = quantity;
    }
}
