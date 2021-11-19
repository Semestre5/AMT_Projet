package com.DAO.Access;

import com.DAO.Objects.Cart;
import com.DAO.Objects.CartId;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CartOps {
    private static SessionFactory fc;
    /*
    * build a session using the session factory
    * */
    public static SessionFactory _init() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
        return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public static CartId save(Cart cartObj) {
        Session sessionObj = _init().openSession();
        //Creating Transaction Object
        Transaction transObj = sessionObj.beginTransaction();
        sessionObj.save(cartObj);
        // Transaction Is Committed To Database
        transObj.commit();
        sessionObj.close();
        return cartObj.getId();
    }

    public static Cart fetchOne(CartId cartId){
        Session sessionObj = _init().openSession();
        // transaction object
        sessionObj.beginTransaction();
        Cart cartObj =  sessionObj.load(Cart.class, cartId);

        // closing session
        sessionObj.close();
        return cartObj;
    }

    public static List<Cart> fetchAllByUser(Integer idUser) {
        String request = "from Cart where id.idUser = :idUser";
        Session sessionObj = _init().openSession();
        List<Cart> cartList = sessionObj.createQuery(request, Cart.class).setParameter("idUser", idUser).getResultList();

        // Closing The Session Object
        sessionObj.close();
        return cartList;
    }

    public static void deleteOne(CartId cartId){
        Session sessionObj = _init().openSession();
        Transaction transObj = sessionObj.beginTransaction();
        Cart tmpCart =  fetchOne(cartId);
        sessionObj.delete(tmpCart);

        transObj.commit();
        sessionObj.close();
    }

    public static void deleteAll(Integer UserID){
        Session sessionObj = _init().openSession();
        Transaction transObj = sessionObj.beginTransaction();
        // or String hqlDelete = "delete Customer where name = :oldName";
        sessionObj
                .createQuery( "delete Cart c where c.id.idUser = :idUser")
                .setParameter( "idUser", UserID )
                .executeUpdate();
        transObj.commit();
        sessionObj.close();
    }


    public static void update(Cart cart){
        Session sessionObj = _init().openSession();
        // transaction object
        Transaction transObj = sessionObj.beginTransaction();
        // fetching object to update
        Cart cartObj = (Cart) sessionObj.load(Cart.class, cart.getId());

        // updating article columns
        cartObj.setQuantity( cart.getQuantity());

        // commit updates
        transObj.commit();
        sessionObj.close();
    }
}
