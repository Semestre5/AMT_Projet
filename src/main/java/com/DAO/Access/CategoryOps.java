package com.DAO.Access;

import com.DAO.Objects.Category;
import com.DAO.SessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;

import java.util.*;

public class CategoryOps extends DbFetcherUtil{
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

    public static Category fetchOne(Integer catId) {
        return ((List<Category>) fetchFromDb("from Category c where c.id = :id", catId, "id")).get(0);
    }
    public static List<Category> fetchAllByIdList(Integer[] ids){
        return (List<Category>) fetchFromDb("from Category c where c.id in :ids",  Arrays.asList(ids), "ids");
    }
    public static List<Category> fetchAll(){
        return (List<Category>) fetchFromDb("from Category");
    }

    public static void deleteCategory(Integer categoryId) {
        ss  = SessionManager.sessionFactory.openSession();
        Transaction transObj = null;
        Category tmpCategory = null;
        try {
            transObj = ss.beginTransaction();
            tmpCategory = ss.load(Category.class, categoryId );
            if(tmpCategory!=null){
                ss.delete(tmpCategory);
            }
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
            list = ss.createQuery("from Category c where c.name = :name", Category.class)
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
}
