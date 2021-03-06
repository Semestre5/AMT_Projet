package com.amt.cart;

import com.DAO.Access.CartOps;
import com.DAO.Objects.Cart;
import com.DAO.Objects.CartId;
import lombok.Getter;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartModel {
    @Getter private final List<Cart> cartProductList;
    // the id of the session won't change
    @Getter private Integer idSession = null;
    public CartModel(Integer idSession){
        this.cartProductList = new ArrayList<>();

        // the user is a Member and retrieve his personal Cart
        if (idSession != null) {
            this.idSession = idSession;
            this.cartProductList.addAll(CartOps.fetchAllByUser(idSession));
        }
    }

    /**
     * Update the current state of cart with an Article. If Article doesn't exist, it do nothing
     * Note : If the user is connected, the cart is saved, otherwise, it would last the same time as the servlet
     * @param idArticle id of the element to add, delete or modify in cart
     * @param quantity quantity of associated Article
     */
    public void update(int idArticle, int quantity) throws Exception/* throws Exception*/ {
        for (Cart cartProduct : cartProductList) {
            if (cartProduct.getId().getIdArticle() == idArticle) {
                cartProduct.setQuantity(quantity);
                if (idSession != null)
                    CartOps.update(cartProduct); //update in db
                break;
            }
        }
    }

    /*public void update(int idArticle, int quantity){

    }*/

    /**
     * Increment the quantity of an article with existing quantity in Cart
     * @param idArticle id of modified Article in Cart
     * @param quantity added quantity
     */
    public void update_incremental(Integer idArticle, Integer quantity){
        for (Cart cartProduct : cartProductList) {
            if (Objects.equals(cartProduct.getId().getIdArticle(), idArticle)) {
                cartProduct.setQuantity(cartProduct.getQuantity() + quantity);
                if (idSession != null)
                    CartOps.update(cartProduct); //update in db
                break;
            }
        }
    }

    /**
     * Suppress all Articles from Cart. If the user is connected, it suppresses all Cart in db too.
     */
    public void deleteAll(){
        if (idSession != null){
            CartOps.deleteAll(idSession);
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
    public boolean has(int idArticle){
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
    public void add(int idProduct, int quantity) throws Exception {
        CartId cartID = new CartId();
        cartID.setIdArticle(idProduct);
        cartID.setIdUser(this.idSession);
        Cart cart = new Cart(quantity);
        cart.setId(cartID);
        if (idSession != null){ //online, we save the cart
            if (CartOps.save(cart) == null){
                throw new Exception("the product has not been added in database");
            }
        }
        else{
            cartProductList.add(cart);
        }
    }

    /**
     * Delete an Article in Cart
     * @param idProduct id of the suppressed Article
     */
    public void delete(int idProduct){
        for (int i = 0; i < cartProductList.size(); i++) {
            if (cartProductList.get(i).getId().getIdArticle() == idProduct) {
                if (idSession != null){
                    CartOps.deleteOne(cartProductList.get(i).getId());
                }
                else{
                    cartProductList.remove(i);
                }
                return;
            }
        }
    }
}