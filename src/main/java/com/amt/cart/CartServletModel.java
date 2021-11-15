package com.amt.cart;

import com.DAO.Access.ArticleOps;
import com.DAO.Objects.Article;
import lombok.Getter;


import java.util.ArrayList;
import java.util.List;

public class CartServletModel {
    @Getter private final List<CartProduct> cartProductList;

    CartServletModel(){
        this.cartProductList = new ArrayList<>();
        update(1,2);
        update(2,4);
        update(3,1);
    }

    public void update(int idProduct, int quantity)/* throws Exception*/ {
        if (has(idProduct)){
            if (quantity == 0){
                delete(idProduct);
            }
            else {
                for (CartProduct cartProduct : cartProductList) {
                    if (cartProduct.getArticle().getId() == idProduct) {
                        cartProduct.setQuantity(quantity);
                        break;
                    }
                }
            }
        }
        else if (quantity > 0){
            add(idProduct, quantity);
        }
    }

    private boolean has(int idProduct){
        for (CartProduct cartProduct : cartProductList){
            if (cartProduct.getArticle().getId() == idProduct)
                return true;
        }
        return false;
    }

    private void add(int idProduct, int quantity){
        Article article = ArticleOps.fetchOne(idProduct);
        article.setId(idProduct);
        cartProductList.add(new CartProduct(article,quantity));
    }

    private void delete(int idProduct){
        for (int i = 0; i < cartProductList.size(); i++) {
            if (cartProductList.get(i).getArticle().getId() == idProduct)
                cartProductList.remove(i);
        }
    }
}
