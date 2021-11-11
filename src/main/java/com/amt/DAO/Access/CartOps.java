package com.amt.DAO.Access;

import com.amt.DAO.Objects.Article;
import com.amt.DAO.Objects.Cart;
import com.amt.DAO.Objects.CartDetail;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CartOps {
    private static SessionFactory fc;
    /*
    * build a session using the session factory
    * */
    public void _init(SessionFactory fc) {
        try {
            fc = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println( "Failed to create sessionFactory object." + ex );
            throw new ExceptionInInitializerError( ex );
        }
    }

    public  void addItem(int idArticle,int quantity){



    }
}
