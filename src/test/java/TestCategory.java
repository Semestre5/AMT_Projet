import com.DAO.Objects.Article;
import com.DAO.Objects.Category;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCategory {
    Article article1 = new Article(BigDecimal.valueOf(10.67),"testing","Article1", 5, "placeholder");
    Category category1 = new Category("Category1");
    /**
     * Tests the addition of an article to a category
     */
    @Test
    public void testArticleAdd(){
        article1.setId(1);
        category1.setId(2);
        assertTrue(category1.getArticles().isEmpty());
        assertFalse(article1.hasCategory(category1));
        category1.addArticle(article1);
        assertTrue(article1.hasCategory(category1));
    }

    /**
     * Tests the removal of an article from a category
     */
    @Test
    public void testArticleRemove(){
        article1.setId(1);
        category1.setId(2);
        assertTrue(article1.getCategories().isEmpty());
        assertFalse(article1.hasCategory(category1));
        category1.addArticle(article1);
        assertTrue(article1.hasCategory(category1));
        category1.removeArticle(article1);
        assertFalse(article1.hasCategory(category1));
    }
}
