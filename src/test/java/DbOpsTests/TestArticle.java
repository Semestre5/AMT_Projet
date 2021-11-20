package DbOpsTests;
import com.DAO.Access.ArticleOps;
import com.DAO.Access.CategoryOps;
import com.DAO.Objects.Article;
import com.DAO.Objects.Category;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestArticle {

    /*
          Testing register article
     */
    @Test
    public void testInsertOneArticle() {

         // Create new article object
        BigDecimal price = new BigDecimal("2.365");
        Article article= new Article(price,"testing article class","ArticleTest",2,"Link");
        // register it to db
        Integer resp = ArticleOps.registerArticle(article);

    }
    @Test
    public void testFetchOne(){
        Integer id = 1;
        Article article = ArticleOps.fetchOne(id);
        System.out.println("Article is :"+"Name : "+article.getName()+" Description : "+article.getDescription());
        assertNotNull( article );
    }
    /*
    * Testing fetchAll method
    * */
    @Test
    public void testFetchAllArticle(){
        // Create new article object
            List articles = ArticleOps.fetchAll();
        System.out.println("Article is :"+articles.toString());
        assertNotNull( articles );
    }



    @Test
    public void testFetchAllByCategories(){
        Category cat = CategoryOps.fetchOne( 2 );

        List<?> articles = ArticleOps.fetchAllByCategory( cat );
        System.out.println("Articles in this category are :");
    }
}
