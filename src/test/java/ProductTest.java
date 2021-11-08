import com.amt.product.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ProductTest {

    @Test
    @DisplayName("Testing export as snippet")
    public void basicExportToSnippet(){
        Product p = new Product("product",0, "description", "urltoImage", 0, 1);
        assertEquals("<div class=\"productbox\">\n" +
                "                    <div class=\"fadeshop\">\n" +
                "                        <div class=\"captionshop text-center\" style=\"display: none;\">\n" +
                "                            <h3>"+p.getName()+"</h3>\n" +
                "                            <p>\n" + p.getDescription() + "\n" +
                "                            </p>\n" +
                "                            <p>\n" +
                "                                <a href=\"#\" class=\"learn-more detailslearn\"><i class=\"fa fa-shopping-cart\"></i> Purchase</a>\n" +
                "                                <a href=\"#\" class=\"learn-more detailslearn\"><i class=\"fa fa-link\"></i> Details</a>\n" +
                "                            </p>\n" +
                "                        </div>\n" +
                "                        <span class=\"maxproduct\"><img src=\""+p.getUrlToImage()+"\" alt=\"\"></span>\n" +
                "                    </div>\n" +
                "                    <div class=\"product-details\">\n" +
                "                        <a href=\"#\">\n" +
                "                            <h1>"+p.getName()+"</h1>\n" +
                "                        </a>\n" +
                "                        <span class=\"price\">\n" +
                "\t\t\t\t\t        <span class=\"edd_price\">$"+ p.getPrice() +"</span>\n" +
                "                        </span>\n" +
                "                        <a class=\"btn-buynow\" href=\"#\">Add to cart</a>\n" +
                "                    </div>\n" +
                "                </div>", p.exportProductSnippet());
    }

    @Test
    @DisplayName("Testing export as snippet")
    public void exportWithQuantityNullRemovesAddToCartButton(){
        Product p = new Product("product",0, "description", "urltoImage", 0, 0);
        assertEquals("<div class=\"productbox\">\n" +
                "                    <div class=\"fadeshop\">\n" +
                "                        <div class=\"captionshop text-center\" style=\"display: none;\">\n" +
                "                            <h3>"+p.getName()+"</h3>\n" +
                "                            <p>\n" + p.getDescription() + "\n" +
                "                            </p>\n" +
                "                            <p>\n" +
                "                                <a href=\"#\" class=\"learn-more detailslearn\"><i class=\"fa fa-shopping-cart\"></i> Purchase</a>\n" +
                "                                <a href=\"#\" class=\"learn-more detailslearn\"><i class=\"fa fa-link\"></i> Details</a>\n" +
                "                            </p>\n" +
                "                        </div>\n" +
                "                        <span class=\"maxproduct\"><img src=\""+p.getUrlToImage()+"\" alt=\"\"></span>\n" +
                "                    </div>\n" +
                "                    <div class=\"product-details\">\n" +
                "                        <a href=\"#\">\n" +
                "                            <h1>"+p.getName()+"</h1>\n" +
                "                        </a>\n" +
                "                        <span class=\"price\">\n" +
                "\t\t\t\t\t        <span class=\"edd_price\">$"+ p.getPrice() +"</span>\n" +
                "                        </span>\n" +
                "                    </div>\n" +
                "                </div>", p.exportProductSnippet());
    }
}
