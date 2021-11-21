package com.amt.cart;

import com.DAO.Access.ArticleOps;
import com.DAO.Access.CartOps;
import com.DAO.Objects.Article;
import com.DAO.Objects.Cart;
import com.DAO.Objects.CartId;
import lombok.Getter;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class CartServletModel {
    @Getter private final List<Cart> cartProductList;
    private Integer session;
    CartServletModel(HttpServletRequest request){
        this.session = 1; //TODO change it pls
        this.cartProductList = new ArrayList<>();
        if (session != null)
            this.cartProductList.addAll(CartOps.fetchAllByUser(session));
        else {
            CartId tmp = new CartId();
            tmp.setIdUser(1);
            tmp.setIdArticle(3);
            Cart cart = new Cart(3);
            cart.setId(tmp);
            cartProductList.add(cart);
        }
    }


    /**
     * Update the current state of cart with an Article. Can suppress, edit or add an Article
     * Note : If the user is connecter, the cart is save, otherwise, it would last the same time as the servlet
     * @param idArticle id of the element to add, suppres or modify in cart
     * @param quantity quantity of associated Article
     */
    public void update(int idArticle, int quantity)/* throws Exception*/ {
        if (has(idArticle)){
            if (quantity == 0){ // suppression of article
                delete(idArticle);
            }
            else { // modification of article quantity in cart
                for (Cart cartProduct : cartProductList) {
                    if (cartProduct.getId().getIdArticle() == idArticle) {
                        if (session != null)
                            CartOps.update(cartProduct); //update in db
                        else
                            cartProduct.setQuantity(quantity);
                        break;
                    }
                }
            }
        }
        else if (quantity > 0){
            add(idArticle, quantity);
        }
    }

    /**
     * Suppress all Articles from Cart. If the user is connected, it suppresses all Cart in db too.
     */
    public void deleteAll(){
        if (session != null){
            CartOps.deleteAll(session);
        }
        else{
            this.cartProductList.clear();
        }
    }

    /**
     * Verify if an Article is present in Cart
     * @param idArticle Id of the Article we look for
     * @return true if exist
     */
    private boolean has(int idArticle){
        for (Cart cartProduct : cartProductList){
            if (cartProduct.getId().getIdArticle() == idArticle)
                return true;
        }
        return false;
    }

    /**
     * Add an element to the cart
     * @param idProduct id of the Product, must exist in db
     * @param quantity quantity to add in Cart
     */
    private void add(int idProduct, int quantity){
        Article article = ArticleOps.fetchOne(idProduct); //throw if not exists
        CartId cartID = new CartId();
        cartID.setIdArticle(article.getId());
        cartID.setIdUser(this.session);
        Cart cart = new Cart(quantity);
        cart.setId(cartID);
        if (session != null){ //online, we save the cart
            CartOps.save(cart);
        }
        else{
            cartProductList.add(cart);
        }
    }

    /**
     * Delete an Article in Cart
     * @param idProduct id of the suppressed Article
     */
    private void delete(int idProduct){
        for (int i = 0; i < cartProductList.size(); i++) {
            if (cartProductList.get(i).getId().getIdArticle() == idProduct) {
                if (session != null){
                    CartOps.deleteOne(cartProductList.get(i).getId());
                }
                else{
                    cartProductList.remove(i);
                }
            }
        }
    }

    /**
     * Update the content of Cart in this
     */
    public void updateContent() {
        //TODO mettre à jour session (voir si deco)
        if (session != null)
            updateAll();
    }

    /**
     * Update the database in this
     */
    private void updateAll(){
        this.cartProductList.clear();
        this.cartProductList.addAll(CartOps.fetchAllByUser(session));
    }

    /**
     * Verify if the user is still connected
     * @param request Last user request
     */
    private void updateSession(HttpServletRequest request){
        if (request.getSession(false) != null) {
            Object sessionID = request.getSession(false).getAttribute("idUserSession");
            if (sessionID != null && sessionID.getClass() == Integer.class) {
                this.session = (Integer) sessionID;
                this.cartProductList.addAll(CartOps.fetchAllByUser(session));
            }
        }
    }
}