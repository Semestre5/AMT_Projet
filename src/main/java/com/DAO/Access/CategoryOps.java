package com.DAO.Access;

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
}
