package com.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.ejb.HibernateEntityManager;
import org.jboss.logging.Logger;

public  class SessionManager {
    public static   SessionFactory sessionFactory;


    public final static Logger logger = Logger.getLogger(SessionManager.class);

    private static final Session session;

    // to be always executed to create session
    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
            // get current session
            session = sessionFactory.getCurrentSession();
        } catch (Throwable ex) {
            // Making sure we log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }


}
