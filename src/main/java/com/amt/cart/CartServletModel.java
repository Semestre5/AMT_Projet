package com.amt.cart;

import com.DAO.Access.ArticleOps;
import com.DAO.Access.CartOps;
import com.DAO.Objects.Cart;
import com.DAO.Objects.CartId;
import lombok.Getter;


import java.util.ArrayList;
import java.util.List;

public class CartServletModel {
    @Getter private final List<Cart> cartProductList;
    @Getter private Integer idSession = null;
    CartServletModel(Integer idSession){
        this.cartProductList = new ArrayList<>();

        // the user is a Member and retrieve his personal Cart
        if (idSession != null) {
            this.idSession = idSession;
            this.cartProductList.addAll(CartOps.fetchAllByUser(idSession));
        }
    }

    /**
     * Update the current state of cart with an Article. Can suppress, edit or add an Article
     * Note : If the user is connecter, the cart is save, otherwise, it would last the same time as the servlet
     * @param idArticle id of the element to add, suppres or modify in cart
     * @param quantity quantity of associated Article
     */
    public void update(int idArticle, int quantity) throws Exception/* throws Exception*/ {
        // the product is in the Cart
        if (has(idArticle)){
            if (quantity == 0){ // suppression of article in cart when 0 quantity
                delete(idArticle);
            }
            else { // modification of article quantity in cart
                for (Cart cartProduct : cartProductList) {
                    if (cartProduct.getId().getIdArticle() == idArticle) {
                        cartProduct.setQuantity(quantity);
                        if (idSession != null)
                            CartOps.update(cartProduct); //update in db
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
    private void add(int idProduct, int quantity) throws Exception {
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
    private void delete(int idProduct){
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