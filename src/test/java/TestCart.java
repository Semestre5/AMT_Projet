import com.DAO.Access.CartOps;
import com.DAO.Objects.Article;
import com.DAO.Objects.Cart;
import com.amt.cart.CartModel;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCart {
    @Getter
    private  List<Cart> cartProductList;
    @Getter
    private Integer idSession = null;
    Integer qt = 12;
    CartModel cm = new CartModel(idSession);


    /**
     * With this test , we can test 3 cart mechanisms in one test.
     * 1- Cart creation.
     * 2- Items insertion in cart
     * 3- Get items in cart
     * @throws Exception : SQL Exceptions might be raised
     */
    @Test
    public void testInsertArticleToCart() throws Exception {
        // we need to check that the cart is empty
        assertNull(cartProductList);
        cm.add(1,qt);

        cartProductList = cm.getCartProductList();
        // since we added a product , the list must not be empty
        assertNotNull(cartProductList);
    }

    /**
     *   To test update_incremental method
     *    it adds the old qt to the new one
     * @throws Exception : SQL exception might be raised
     */
    @Test
    public void testUpdateCartIncremental() throws Exception {

        assertNull( cartProductList );
        cm.add(1,qt);
        Integer newQt = 5;

        cm.update_incremental(1,newQt);
        assertEquals( newQt+qt ,cm.getCartProductList().get( 0 ).getQuantity());
    }

    /**
     * The only diffrence between this one and the previous ,
     *  is that this method replaces the old qt
     *  to the new one.
     * @throws Exception
     */
    @Test
    public void testUpdateCart() throws Exception {
        assertNull(cartProductList);
        cm.add( 1,qt );
        Integer newQt = 5;

        cm.update( 1,newQt );
        assertEquals( newQt,cm.getCartProductList().get( 0 ).getQuantity());

    }

    /**
     *  Testing has method. It checks if a cart has the item or not.
     * @throws Exception : SQL Exceptions might rise
     */
    @Test
    public void testCartHasItem() throws Exception {
        assertNull(cartProductList);
        cm.add( 1,qt );
        cm.add(2,qt);
        cm.add(3,qt);

       assertEquals(1, cm.getCartProductList().get(0).getId().getIdArticle());
       assertEquals(2,cm.getCartProductList().get(1).getId().getIdArticle());
       assertEquals(3,cm.getCartProductList().get(2).getId().getIdArticle());

       // making sure has returns true for the items we added above
        assertTrue( cm.has(1));
        assertTrue( cm.has(2));
        assertTrue( cm.has(3));
        // making sure has returns false for the items that are not in the cart.
        assertFalse( cm.has(4));
        assertFalse( cm.has(5));


    }

    /**
     * testing item removal from cart
     * @throws Exception : might raise an SQL exception
     */
    @Test
    public void testDeleteItemFromCart() throws Exception{
        assertNull(cartProductList);
        cm.add( 1,qt );
        cm.add(2,qt);
        cm.add(3,qt);


        cm.delete( 1 );
        cm.delete( 2 );
        cm.delete( 3 );

        assertFalse(cm.has(1));
        assertFalse(cm.has(2));
        assertFalse(cm.has(3));

    }
}
