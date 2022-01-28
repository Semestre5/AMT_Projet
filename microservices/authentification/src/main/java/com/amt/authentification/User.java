package com.amt.authentification;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;


@Table(name = "users_wip", uniqueConstraints = {
        @UniqueConstraint(name = "uc_user_name", columnNames = {"name"}),
        @UniqueConstraint(name = "uc_user_id", columnNames = {"id"})
})
@Entity
@Getter
@Setter
public class User {
    public User(){

    }
    public User(String username, String password, String role){
        this.name = username;
        this.password = password;
        this.role = role;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "password", nullable = false)
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public static User fetchOneByName(String name){
        // Create the SessionFactory from hibernate.cfg.xml
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        // get current session
        Session ss = sessionFactory.getCurrentSession();
        Transaction trans = ss.beginTransaction();
        // User user = (User) ss.load( User.class, name);
        Query query = ss.createQuery("FROM User u WHERE u.name=:name");
        query.setParameter("name", name);
        List users = query.getResultList();
        trans.commit();
        ss.close();
        User user = null;
        if (!users.isEmpty()){
            user = (User) users.get(0);
        }
        return user;
    }

    public static void register(User user){
        // Create the SessionFactory from hibernate.cfg.xml
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        // get current session
        Session ss = sessionFactory.getCurrentSession();
        Transaction transaction = ss.beginTransaction();
        ss.save( user);
        transaction.commit();
        ss.close();
    }

    public static void delete(User user){
        // Create the SessionFactory from hibernate.cfg.xml
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        // get current session
        Session ss = sessionFactory.getCurrentSession();
        Transaction transaction = ss.beginTransaction();
        ss.delete( user);
        transaction.commit();
        ss.close();
    }
}