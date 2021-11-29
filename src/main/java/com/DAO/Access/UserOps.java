package com.DAO.Access;
import com.DAO.Objects.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;
import org.jboss.logging.Logger;

import java.util.List;


public class UserOps {
    private static SessionFactory fc;
    public final static Logger logger = Logger.getLogger(UserOps.class);
    public static SessionFactory _init() {
        return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public static Integer register(User userObj){
        Session sessionObj = _init().openSession();
        //Creating Transaction Object
        Transaction transObj = sessionObj.beginTransaction();
        sessionObj.save(userObj);
        // Transaction Is Committed To Database
        transObj.commit();
        logger.info("Successfully Created " + userObj.toString());
        return userObj.getId();
    }
    public static User fetchOne( Integer userId){
        Session sessionObj = _init().openSession();
        User user = (User) sessionObj.load(User.class, userId);
        // Closing The Session Object
        sessionObj.close();
        return user;
    }
    public static void updateUser(User user){
        Session sessionObj = _init().openSession();
        Transaction transObj = sessionObj.beginTransaction();
        User userObj = (User) sessionObj.load(User.class,user.getId());
        // modification to other columns to be added after we add user colomns to db
        // userObj.setName(user.getName());
        // Commit transaction to Db
        transObj.commit();
        sessionObj.close();
        logger.info("User is successfully updated! ="+user.toString());
    }
    public static void deleteUser(Integer userId){
        Session sessionObj = _init().openSession();

        Transaction transObj = sessionObj.beginTransaction();
        User tmpUser = fetchOne(userId);
        sessionObj.delete(tmpUser);
        // Closing session object
        sessionObj.close();
        logger.info("Successfully Deleted"+ tmpUser.toString());

    }
    public static List fetchAll() {
        Session sessionObj = _init().openSession();
        List userList = sessionObj.createQuery("FROM User").list();

        // Closing The Session Object
        sessionObj.close();
        logger.info("Users Available In Database Are?= " + userList.size());
        return userList;
    }

}
