package com.amt.authentification;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "user", uniqueConstraints = {
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
    @Column(name = "id", nullable = false)
    private Long id;

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
        User user = (User) ss.load( User.class, name);
        ss.close();
        return user;
    }

    public static Long register(User user){
        // Create the SessionFactory from hibernate.cfg.xml
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        // get current session
        Session ss = sessionFactory.getCurrentSession();
        ss.save( user);
        user = ss.load( User.class, user.getName());
        ss.close();
        return user.getId();
    }
}