package DbOpsTests;

import com.DAO.Access.ArticleOps;
import com.DAO.Access.CategoryOps;
import com.DAO.Objects.Article;
import com.DAO.Objects.Category;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestCategories {
    /*
    @Test
    public void testInsertCat(){

        Integer id = 0;
        id++;
        String name = "Cat"+id;
        Category cat = new Category ("Cat "+id);
    }
    @Test
    public void testFetchOneCat(){
        Category notfound = null;
        Category found = CategoryOps.fetchOne(2);
        try {
            notfound = CategoryOps.fetchOne( 10 );
        } catch ( Exception e ){
            System.out.println("Row not found");
            assertNull(notfound);
        }
        System.out.println(found.getId()+" NAME : "+found.getName());
        assertNotNull(found);



    }*/
    /*
    @Test
    public void testFetchAllCat() {
        // List des category
        List<Category> cats = CategoryOps.fetchAll();
        assertNotNull(cats);
    }

    @Test
    public void testInsertCategoriesToArticle(){
        Article art = ArticleOps.fetchOne(2);
        System.out.println("Article is :"+art.getName());
        Integer id = 1;
        Category cat = CategoryOps.fetchOne(id);
        art.addCategory(cat);
        cat.addArticleToCategory( art );

        System.out.println("Article"+art.getName()+"is under"+art.getCategories());
        assertNotNull(art);
        assertNotNull(cat);

    }

    @Test
    public void testFetchArticleByCategory(){
        Category cat = CategoryOps.fetchOne(2);
        assertNotNull(cat);
        List<?> articles = ArticleOps.fetchAllByCategory(cat);
        System.out.println("There is "+articles.size()+" article under this category");
    }
    */
     
}
