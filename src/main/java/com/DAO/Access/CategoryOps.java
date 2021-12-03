package com.DAO.Access;

import com.DAO.Objects.Article;
import com.DAO.Objects.ArticleCategory;
import com.DAO.Objects.Category;
import com.DAO.SessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.jboss.logging.Logger;

import java.util.List;

public class CategoryOps {
    static Session ss;
    private final static Logger logger = Logger.getLogger(CategoryOps.class);


    public static Integer addCategory(String name) {
        ss = SessionManager.sessionFactory.getCurrentSession();
        Transaction transObj = null;
        Category newCategory = null;
        try {
            transObj = ss.beginTransaction();
            newCategory = new Category( name );
            ss.save( newCategory );
            transObj.commit();
            logger.info( "Added category !" + name );
        } catch (Exception e) {
            logger.error( "Something wrong occured" + e );
            transObj.rollback();

        } finally {
            ss.close();
        }
        return newCategory.getId();
    }

    public static Category fetchOne(Integer id) {
        ss = SessionManager.sessionFactory.getCurrentSession();
        Transaction transObj = null;
        Category cat = null;
        try {
            transObj = ss.beginTransaction();
            cat = (Category) ss.load( Category.class, id );
            transObj.commit();

        } catch (Exception e) {
            transObj.rollback();
            logger.error( "Something wrong occured" + e );
        } finally {
            ss.close();
            return cat;
        }

    }

    public static List fetchAll(){
            ss = SessionManager.sessionFactory.getCurrentSession();
            Transaction transObj = null;
            List<Category> categories = null;
            try {
                transObj = ss.beginTransaction();
                Query<Category> query = ss.createQuery( "from Category ",Category.class );
                categories = query.getResultList();
                transObj.commit();

            }catch (Exception e) {
                transObj.rollback();
                logger.info( "Something wrong occured"+e);
            }finally{
                logger.info( "Loaded"+categories.size()+" categories successfully" );
                return categories;
            }
    }

    public static void deleteCategory(Integer categoryId) {
        ss  = SessionManager.sessionFactory.openSession();
        Transaction transObj = null;
        Category tmpCategory = null;
        try {
            transObj = ss.beginTransaction();
            tmpCategory = fetchOne( categoryId );
            ss.delete( tmpCategory );
            transObj.commit();
            logger.info( " Category " + tmpCategory.getId() + " named " + tmpCategory.getName() + " has been deleted" );
        } catch (Exception e) {
            transObj.rollback();
            System.out.println( "Something wrong occured " + e );
        } finally {
            ss.close();
        }
    }

    public static Integer isStored(String name){
        ss = SessionManager.sessionFactory.openSession();
        Transaction transObj = null;
        List<Category> list = null;
        try {
            transObj = ss.beginTransaction();
            list = ss.createQuery("from Category where name = :name", Category.class)
                    .setParameter("name",name)
                    .getResultList();
            transObj.commit();
        } catch (Exception e) {
            transObj.rollback();
            logger.error("Something went wrong " + e);
        } finally {
            ss.close();
            return list.isEmpty() ? null :list.get(0).getId();
        }
    }

    //TODO supprimer ? c'est inutile pour l'instant
    public static boolean hasArticle(Integer idCategory){
        ss = SessionManager.sessionFactory.getCurrentSession();
        Boolean hasArticle = false;
        List<ArticleCategory> list = null;
        Transaction transObj = null;
        try {
            transObj = ss.beginTransaction();
            list = ss.createQuery( "from ArticleCategory where id.idCategory = :idCategory", ArticleCategory.class )
                    .setParameter( "idCategory", idCategory )
                    .getResultList();
            transObj.commit();
        }catch (Exception e) {
            transObj.rollback();
            logger.info( "Something wrong occured"+e);
        }finally {
            logger.info( "Loaded articles for category "+ idCategory + " successfully" );
            return list != null;
        }
    }
}
