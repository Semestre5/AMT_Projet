package com.DAO.Access;

import com.DAO.Objects.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.jboss.logging.Logger;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class CategoryOps {
    private final static Logger logger = Logger.getLogger(CategoryOps.class);

    public static SessionFactory _init(){
        Configuration confObj = new Configuration();
        confObj.configure( "hibernate.cfg.xml" );

        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings( confObj.getProperties()).build();
        return (SessionFactory)confObj.buildSessionFactory(serviceRegistryObj);

    }

    public static Integer addCategory(String name){
        Session sessionObj = _init().openSession();
        Transaction transObj = sessionObj.beginTransaction();
        Category newCategory=new Category(name);
        sessionObj.save(newCategory);
        transObj.commit();
        logger.info("Successfully added"+newCategory.getName()+" category");
        return newCategory.getId();
    }

    public static Category fetchOne(Category category){
        Session sessionObj = _init().openSession();
        Category cat = (Category) sessionObj.load(Category.class,category.getId());
        sessionObj.close();
        return cat;
    }

    public static List fetchAll(){
        Session sessionObj = _init().openSession();
        List categoryList = sessionObj.createQuery( "from Category " ).list();
        sessionObj.close();
        logger.info( "Categories : "+  categoryList.size());
        return categoryList;
    }



}
