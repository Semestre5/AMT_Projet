package DbOpsTests;
import com.DAO.Access.ArticleOps;
import com.DAO.Objects.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestArticle {

    @Test
    public void testInsertOneArticle() {

         // Create new article object
        BigDecimal price = new BigDecimal("2.365");
        Article article= new Article(price,"testing article class","ArticleTest",2,"Link");
        // register it to db
        Integer resp = ArticleOps.registerArticle(article);

    }
}
