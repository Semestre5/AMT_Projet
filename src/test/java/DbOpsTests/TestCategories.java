package DbOpsTests;

import com.DAO.Access.ArticleOps;
import com.DAO.Access.CategoryOps;
import com.DAO.Objects.Article;
import com.DAO.Objects.Category;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
public class TestCategories {

    @Test
    public void testInsertCat(){

    }
    @Test
    public void testFetchAllCat() {
        // List des category
        List<Category> cats = CategoryOps.fetchAll();
        assertNotNull(cats);

    }
}
