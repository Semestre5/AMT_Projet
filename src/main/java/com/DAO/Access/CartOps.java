package com.DAO.Access;

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

public class CartOps {
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
        ss = SessionManager.sessionFactory.getCurrentSession();
        Transaction transObj = null;
        Cart cartObj = null;

        try {
            transObj = ss.beginTransaction();
            cartObj = ss.load( Cart.class, cartId );
            transObj.commit();

        } catch (Exception e) {
            logger.error( "Something went wrong" + e );
        } finally {
            ss.close();
            return cartObj;
        }
    }

    public static List<Cart> fetchAllByUser(Integer idUser) {
        ss = SessionManager.sessionFactory.getCurrentSession();
        Transaction transObj = null;
        List<Cart> cartList = null;
        try {
            transObj = ss.beginTransaction();
            Query query = ss.createQuery( "from Cart where id.idUser = :idUser" ).setParameter( "idUser", idUser );
            cartList = query.getResultList();
            transObj.commit();

        } catch (Exception e) {
            logger.error( "Something went wrong" + e );
            transObj.rollback();

        } finally {
            ss.close();
            return cartList;
        }
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
