package com.DAO.Access;
import com.DAO.Objects.User;
import com.DAO.SessionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;
import org.jboss.logging.Logger;

import java.util.List;


public class UserOps {
    static Session ss;
    public final static Logger logger = Logger.getLogger(UserOps.class);


    public static Integer register(User userObj){
        ss = SessionManager.sessionFactory.getCurrentSession();
        //Creating Transaction Object
        Transaction transObj = null;
        try {
            transObj = ss.beginTransaction();
            ss.save(userObj);
            transObj.commit();
        }catch (Exception e) {
            logger.error("Something went wrong",e);
            transObj.rollback();
        }finally {
            ss.close();
            return userObj.getId();
        }
    }
    public static User fetchOne( Integer userId){
        ss = SessionManager.sessionFactory.getCurrentSession();
        Transaction transObj = null;
        User user = null;

        try {
            transObj = ss.beginTransaction();
            user = ss.load(User.class,userId);
            transObj.commit();

        }catch(Exception e){
            logger.error( "Something went wrong"+e);
            transObj.rollback();
        }finally {
            ss.close();
            return user;
        }


    }

    public static void deleteUser(Integer userId) {
        ss = SessionManager.sessionFactory.getCurrentSession();
        Transaction transObj = null;
        User tmpUser = null;
        try {
            transObj = ss.beginTransaction();
            tmpUser = fetchOne( userId );
            ss.delete( tmpUser );
            transObj.commit();
        } catch (Exception e) {
            transObj.rollback();
            logger.error( "Something went wrong" + e );
        } finally {
            ss.close();
            logger.info( "Successfully Deleted" + tmpUser.toString());
        }


    }
    public static List fetchAll() {
        ss = SessionManager.sessionFactory.getCurrentSession();
        List userList = null;
        Transaction transObj = null;
        try {
            transObj = ss.beginTransaction();
            userList  = ss.createQuery("FROM User").getResultList();
            logger.info("number of fetched users is  " + userList.size());
            transObj.commit();
        }catch (Exception e) {
            transObj.rollback();
            logger.error( "Something went wrong"+e);
        }finally {
            ss.close();
            return userList;
        }





    }

}
