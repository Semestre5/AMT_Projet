import com.DAO.Objects.Article;
import com.DAO.Objects.Category;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


import static org.junit.jupiter.api.Assertions.*;


public class ArticleTest {
    Article article1 = new Article(BigDecimal.valueOf(10.67),"testing","Article1", 5, "placeholder");
    Category category1 = new Category("Category1");
    Category category2 = new Category("Category2");

    /**
     * Tests that it is not possible to have an article with a negative price value
     */
    @Test
    public void testNoNegativePriceValues(){
        Article article = new Article(BigDecimal.valueOf(-5),"testing","negative price", 4, "placeholder");
        assertEquals(BigDecimal.valueOf(0), article.getPrice());
        article.setPrice(BigDecimal.valueOf(-15.6));
        assertEquals(BigDecimal.valueOf(0), article.getPrice());
    }


    /**
     * Tests that it is not possible to have a negative Quantity value for an article
     */
    @Test
    public void testNoNegativeQuantityValues(){
        Article article = new Article(BigDecimal.valueOf(10),"testing","negative price", -15, "placeholder");
        assertEquals(0, article.getQuantity());
        article.setQuantity(Integer.MIN_VALUE);
        assertEquals(0, article.getQuantity());
    }

    /**
     * tests the behavior of the article quantity in edge cases (int max and zero here)
     */
    @Test
    public void testEdgeQuantityValues(){
        Article article = new Article(BigDecimal.valueOf(10),"testing","negative price", 0, "placeholder");
        assertEquals(0, article.getQuantity());
        article.setQuantity(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, article.getQuantity());
    }

    /**
     * Tests the isSellable method, that is works correctly for quantity of 0 or price of 0
     */
    @Test
    public void testIsSellable(){
        assertTrue(article1.isSellable());
        article1.setQuantity(0);
        assertFalse(article1.isSellable());
        article1.setQuantity(5);
        article1.setPrice(BigDecimal.valueOf(0));
        assertFalse(article1.isSellable());
        article1.setPrice(BigDecimal.valueOf(10.67));
    }

    /**
     * Tests that the hasCategory method works as intended, to detect if an article has a specific
     * category attributed to it
     */
    @Test
    public void testHasCategory(){
        article1.setId(1);
        category1.setId(2);
        assertTrue(article1.getCategories().isEmpty());
        assertFalse(article1.hasCategory(category1));
        article1.addCategory(category1);
        assertTrue(article1.hasCategory(category1));
    }

    /**
     * Tests the addition of a category to an article
     */
    @Test
    public void testCategoryAdd(){
        article1.setId(1);
        category1.setId(2);
        category2.setId(3);
        assertTrue(article1.getCategories().isEmpty());
        assertFalse(article1.hasCategory(category1));
        article1.addCategory(category1);
        assertTrue(article1.hasCategory(category1));
        assertFalse(article1.hasCategory(category2));
    }

    /**
     * Tests the removal of a category from an article
     */
    @Test
    public void testCategoryRemove(){
        article1.setId(1);
        category1.setId(2);
        category2.setId(3);
        assertTrue(article1.getCategories().isEmpty());
        assertFalse(article1.hasCategory(category1));
        article1.addCategory(category1);
        article1.addCategory(category2);
        assertTrue(article1.hasCategory(category2));
        article1.removeCategory(category2);
        assertFalse(article1.hasCategory(category2));

    }
}
