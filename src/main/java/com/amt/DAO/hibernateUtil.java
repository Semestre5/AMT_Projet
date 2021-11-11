package com.amt.DAO;

import com.amt.DAO.Objects.Article;
import com.amt.DAO.Objects.Cart;
import com.amt.DAO.Objects.Category;
import com.amt.DAO.Objects.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class hibernateUtil {
    private static EntityManagerFactory factory;

    public static Session getSession() {
        EntityManager entityManager = PersistanceManager.getEntityManager();

        Session session = entityManager.unwrap(org.hibernate.Session.class);

        SessionFactory factory = session.getSessionFactory();

        return session;
    }

    public static Session beginTransaction() {
        Session hibernateSession = getCurrentSession();
        hibernateSession.beginTransaction();
        return hibernateSession;
    }

    public static void commitTransaction(Session s) {
        s.getTransaction().commit();
    }

    public static void rollbackTransaction(Session s) {
        s.getTransaction().rollback();
    }

    public static void closeSession(Session s) {
        s.close();
    }


    private static Session getCurrentSession() {
        Map<String, String> settings = new HashMap<>();
        settings.put("connection.driver_class", "com.mysql.jdbc.Driver");
        settings.put("dialect", "org.hibernate.dialect.MySQL8Dialect");
        // db name
        settings.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/mydb");
        settings.put("hibernate.connection.username", "root");
        settings.put("hibernate.connection.password", "toor");
        settings.put("hibernate.current_session_context_class", "thread");
        settings.put("hibernate.show_sql", "true");
        settings.put("hibernate.format_sql", "true");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(settings).build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addAnnotatedClass( Article.class);
        metadataSources.addAnnotatedClass ( User.class);
        metadataSources.addAnnotatedClass ( Category.class);
        metadataSources.addAnnotatedClass ( Cart.class);


        Metadata metadata = metadataSources.buildMetadata();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }
}
