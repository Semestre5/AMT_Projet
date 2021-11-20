package DbOpsTests;
import com.DAO.Access.ArticleOps;
import com.DAO.Objects.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

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
        Article article = new Article(price, "testing article class", "test",1, "Link");
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
}
