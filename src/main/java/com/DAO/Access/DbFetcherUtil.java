package com.DAO.Access;

import com.DAO.SessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract public class DbFetcherUtil {
    static Session ss;
    private final static Logger logger = Logger.getLogger(CategoryOps.class);

    /**
     *
     * @param args
     * @return
     */
    static Object fetchFromDb(Object... args){
        if(args.length == 0)
            throw new IllegalArgumentException("Too few arguments");
        ss  = SessionManager.sessionFactory.openSession();
        Transaction transObj = null;
        Object output = new Object();
        try {
            transObj = ss.beginTransaction();
            String query_str =  (String) args[0];
            switch (args.length){
                case(1):
                    //Execute simple query string
                    output = ss.createQuery(query_str).list();
                    break;
                case(2):
                    //TODO fetch one by id
                    output = ss.load((Class<?>) args[0], (Integer) args[1]);
                    break;
                case(3):
                    //Execute complex query string with foreign keys or list parameters
                    if(args[1] instanceof List)
                        output = ss.createQuery(query_str).setParameterList((String)args[2], (List<?>)args[1]).list();
                    else
                        output = ss.createQuery(query_str).setParameter((String) args[2], args[1]).list();
                    break;
                default:
                    throw new IllegalArgumentException("Too many arguments");
            }
            transObj.commit();
        } catch (Exception e) {
            transObj.rollback();
            logger.error( "Something wrong occured"+e);
        } finally {
            logger.info("Query executed");
            ss.close();
            return output;
        }
    }
}
