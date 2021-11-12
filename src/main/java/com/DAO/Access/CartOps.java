package com.DAO.Access;

import org.hibernate.SessionFactory;
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

    public  void register(){

    }




}
