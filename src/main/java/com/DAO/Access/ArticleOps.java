package com.DAO.Access;

import com.DAO.Objects.Article;
import com.DAO.Objects.ArticleCategory;
import com.DAO.Objects.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;
import com.DAO.SessionManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArticleOps {
    static Session ss ;
    public final static Logger logger = Logger.getLogger(ArticleOps.class);


    public static Integer registerArticle( Article article){
        // transaction object
        ss = SessionManager.sessionFactory.getCurrentSession();
        Transaction transObj = null;
        // adding article to DB
        try {
            transObj = ss.beginTransaction();
            ss.save(article);
            transObj.commit();
            logger.info("Successfully commited article"+article.getId()+"to DB");
        } catch (Exception e){
            logger.error("Something went wrong",e);
            transObj.rollback();
        } finally {
            ss.close();
            return article.getId();
        }
    }


    public static Article fetchOne(Integer articleId){
        ss = SessionManager.sessionFactory.getCurrentSession();
        Article articleObj = null;
        // transaction object
        Transaction transaction = null;
        try {
            transaction = ss.beginTransaction();
            articleObj =  ss.load(Article.class,articleId);
            if (articleObj!= null){
                logger.info("Successfully fetched the article with id :"+articleId);
                return articleObj;
            }
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
            System.out.println("Something wrong occured"+e);
        } finally {
            ss.close();
            return articleObj;
        }
    }

    public static void updateArticle(Article article){
        ss = SessionManager.sessionFactory.getCurrentSession();
        // transaction object
        Transaction transObj = null;
        try{
            transObj = ss.beginTransaction();
            // fetching object to update
            Article articleObj = (Article) ss.load(Article.class,article.getId());
            // updating article columns
            articleObj.setName( article.getName() );
            articleObj.setDescription( article.getDescription());
            articleObj.setPrice( article.getPrice() );
            articleObj.setQuantity(article.getQuantity());
            articleObj.setLink( article.getLink());
            // commit updates
            transObj.commit();
        }catch (Exception e){
            transObj.rollback();
            System.out.println("Something wrong occured."+e);
        }finally {
            ss.close();
            logger.info( "Article" + article.getId() + " updated successfully" );
        }
    }

    public static void deleteArticle(Integer articleId) {
        ss  = SessionManager.sessionFactory.openSession();
        Transaction transObj = null;
        Article tmpArticle = null;
        try {
            transObj = ss.beginTransaction();
            tmpArticle = fetchOne( articleId );
            ss.delete( tmpArticle );
            transObj.commit();
        } catch (Exception e) {
            transObj.rollback();
            System.out.println( "Something wrong occured" + e );
        } finally {
            ss.close();
            logger.info( "Article" + tmpArticle.getId() + "is deleted" );
        }
    }

    public static List<?> fetchAll() {
        ss = SessionManager.sessionFactory.openSession();

        Transaction transObj = null;
        List<?> articleList = null;
        try {
            transObj = ss.beginTransaction();
            Query<Article> query = ss.createQuery( "from Article ", Article.class );
            query.setCacheable( true );
            query.setCacheRegion( "Items" );
            articleList = query.getResultList();
            transObj.commit();
        } catch (Exception e){
            transObj.rollback();
            logger.error( "Something went wrong"+e );
        }finally {
            ss.close();
            logger.info( "Number of available articles is : " + articleList.size() );
            return articleList;
        }
    }

    // SELECT * FROM article ORDER BY article.id DESC LIMIT 3
    public static List<?> fetchLastThree(){
        ss = SessionManager.sessionFactory.openSession();

        Transaction transObj = null;
        List<?> articleList = null;
        try {
            transObj = ss.beginTransaction();
            Query<Article> query = ss.createQuery( "from Article order by id desc", Article.class );
            query.setMaxResults(3);
            query.setCacheable( true );
            query.setCacheRegion( "Items" );
            articleList = query.getResultList();
            transObj.commit();
        } catch (Exception e){
            transObj.rollback();
            logger.error( "Something went wrong"+e );
        }finally {
            ss.close();
            logger.info( "Number of available articles is : " + articleList.size() );
            return articleList;
        }
    }


    public static List<?> fetchAllByCategory( Category category){
        ss   = SessionManager.sessionFactory.openSession();
        Transaction transObj = null;
        List<?> articleList = null;
        Set<Category> cats = new HashSet<Category>();
        cats.add(category);
        try {
            transObj = ss.beginTransaction();
            articleList = ss.createQuery("from Article a where :category in elements(categories) ").setParameter( "category",cats ).list();
            transObj.commit();
        }catch (Exception e) {
            transObj.rollback();
            logger.error( "Something wrong occured"+e);
        }finally{
            logger.info("Number of articles : "+articleList.size());
            ss.close();
            return articleList;
        }
    }
    public static Integer isStored(String name) {
        ss = SessionManager.sessionFactory.openSession();
        Transaction transObj = null;
        List<Article> list = null;
        try {
            transObj = ss.beginTransaction();
            list = ss
                    .createQuery( "from Article where name = :name", Article.class )
                    .setParameter( "name", name )
                    .getResultList();
            transObj.commit();

        } catch (Exception e) {
            transObj.rollback();
            logger.error( "Something wrong occured" + e );

        } finally {
            ss.close();
            return list.isEmpty() ? null :list.get( 0 ).getId() ;
        }
    }
}

