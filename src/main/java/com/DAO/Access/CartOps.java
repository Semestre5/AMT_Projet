package com.DAO.Access;

import com.DAO.Objects.Article;
import com.DAO.Objects.Cart;
import com.DAO.Objects.CartId;
import com.DAO.SessionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;

import java.lang.reflect.Array;
import java.util.List;

public class CartOps extends DbFetcherUtil{
    static Session ss;
    public final static Logger logger = Logger.getLogger(CartOps.class);

    public static CartId save(Cart cartObj) {
        ss = SessionManager.sessionFactory.getCurrentSession();
        //Creating Transaction Object
        Transaction transObj = null;

        try {
            transObj = ss.beginTransaction();
            ss.save( cartObj );
            transObj.commit();
        } catch (Exception e) {
            logger.error( "Something went wrong" + e );
            transObj.rollback();
        } finally {
            ss.close();
            return cartObj.getId();
        }
    }

    public static Cart fetchOne(CartId cartId) {
        return ((List<Cart>) fetchFromDb("from Cart c where c.id = :id", cartId, "id")).get(0);
    }

    public static List<Cart> fetchAllByUser(Integer idUser) {
        return (List<Cart>) fetchFromDb("from Cart where id.idUser = :idUser", idUser, "idUser");
    }

    public static void deleteOne(CartId cartId){
        ss = SessionManager.sessionFactory.getCurrentSession();
        Transaction transObj = null;
        Cart tmpCart = null;
        try {
            transObj = ss.beginTransaction();
            tmpCart = fetchOne(cartId);
            ss.delete(tmpCart);
            transObj.commit();
        }catch (Exception e) {
            transObj.rollback();
            logger.error( "Something went wrong"+e );
        }finally{
            ss.close();
        }
    }

    public static void deleteAll(Integer UserID){
        ss = SessionManager.sessionFactory.getCurrentSession();
        Transaction transObj = null;
        Cart tmpCart = null;
        try {
            transObj = ss.beginTransaction();

            ss.createQuery( "delete Cart c where c.id.idUser = :idUser")
                    .setParameter( "idUser", UserID )
                    .executeUpdate();

            transObj.commit();
        }catch (Exception e) {
            transObj.rollback();
            logger.error( "Something went wrong"+e );
        }finally{
            ss.close();
        }

    }


    public static void update(Cart cart){
        ss = SessionManager.sessionFactory.getCurrentSession();
        // transaction object
        Transaction transObj = null;
        Cart cartObj = null;
        try {
            transObj = ss.beginTransaction();
            // fetching & updating cart
            cartObj = ss.load(Cart.class, cart.getId());
            cartObj.setQuantity( cart.getQuantity());

            transObj.commit();
        }catch (Exception e) {
            transObj.rollback();
            logger.error( "Something went wrong"+e );
        }finally{
            ss.close();
        }
    }
}
