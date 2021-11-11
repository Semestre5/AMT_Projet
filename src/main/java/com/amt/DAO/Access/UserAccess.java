package com.amt.DAO.Access;
import com.amt.DAO.*;
import com.amt.DAO.Objects.User;
import org.hibernate.Session;

public class UserAccess {

    public void register(User user){
        Session ss = hibernateUtil.beginTransaction();
        ss.persist(user);
        hibernateUtil.commitTransaction( ss );
    }
    public void fetch(User user){
        Session ss = hibernateUtil.beginTransaction();
        user = ss.find(User.class,user.getId());


    }

}
