package com.DAO.Access;

import com.DAO.Objects.Article;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.jboss.logging.Logger;

import java.util.List;

public class ArticleOps {
    private static SessionFactory fc;
    public final static Logger logger = Logger.getLogger(UserOps.class);

    public static  SessionFactory _init(){
        Configuration configObj = new Configuration();
        configObj.configure( "hibernate.cfg.xml" );
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();

        return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public static Integer registerArticle( Article article){
        Session sessionObj = _init().openSession();
        // transaction object
        Transaction transObj = sessionObj.beginTransaction();
        // adding article to DB
        sessionObj.save(article);
        transObj.commit();
        logger.info("Successfully commited article"+article.getId()+"to DB");
        return article.getId();
    }


    public static Article fetchOne(Integer articleId){
        Session sessionObj = _init().openSession();
        // transaction object
        sessionObj.beginTransaction();
        Article articleObj = (Article) sessionObj.load(Article.class,articleId);

        // closing session
        sessionObj.close();
        return articleObj;
    }

    public static void updateArticle(Article article){
        Session sessionObj = _init().openSession();
        // transaction object
        Transaction transObj = sessionObj.beginTransaction();
        // fetching object to update
        Article articleObj = (Article) sessionObj.load(Article.class,article.getId());

        // updating article columns
        articleObj.setName( article.getName() );
        articleObj.setDescription( article.getDescription());
        articleObj.setPrice( article.getPrice() );
        articleObj.setQuantity(article.getQuantity());
        articleObj.setLink( article.getLink());

        // commit updates
        transObj.commit();
        sessionObj.close();
        logger.info("Article"+article.getId()+" updated successfully");
    }

    public static void deleteArticle(Integer articleId){
        Session sessionObj = _init().openSession();
        Transaction transObj = sessionObj.beginTransaction();
        Article  tmpArticle =  fetchOne(articleId);
        sessionObj.delete(tmpArticle);
        logger.info("Article"+ tmpArticle.getId()+"successfully deleted");

    }

    public static List<Article> fetchAll(){
        Session sessionObj = _init().openSession();
        List articleList = sessionObj.createQuery("FROM Article ").list();
        sessionObj.close();
        logger.info("Number of available articles is : "+articleList.size());
        return articleList;
    }

}
