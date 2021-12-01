package com.DAO.Access;

import com.DAO.Objects.Article;
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
        return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    //TODO modify this to look like the registerArticle in ArticleOps (not good to pass the name)
    public static Integer addCategory(String name){
        Session sessionObj = _init().openSession();
        Transaction transObj = sessionObj.beginTransaction();
        Category newCategory=new Category(name);
        sessionObj.save(newCategory);
        transObj.commit();
        sessionObj.close();
        logger.info("Successfully added"+newCategory.getName()+" category");
        return newCategory.getId();
    }

    public static Category fetchOne(Integer id){
        Session sessionObj = _init().openSession();
        Category cat = (Category) sessionObj.load(Category.class,id);
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

    public static Integer isStored(String name){
        Session sessionObj = _init().openSession();
        List<Category> list = sessionObj
                .createQuery("from Category where name = :name", Category.class)
                .setParameter( "name",name )
                .getResultList();
        sessionObj.close();
        return list.size() > 0 ? list.get(0).getId() : null;
    }
}
