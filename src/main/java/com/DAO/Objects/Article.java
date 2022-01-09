package com.DAO.Objects;

import com.DAO.Access.CategoryOps;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Proxy;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Table(name = "article")
@Entity
@Proxy(lazy=false)
public class Article {
    public static Article TEST_ARTICLE1 = new Article( new BigDecimal("49.99"), "this a nice theme",
            "Calypso Theme", 1, "./resources/images/product1-1.jpg");
    public static Article TEST_ARTICLE2 = new Article( new BigDecimal("39.99"), "this a nice theme",
            "Mega cool Theme", 0, "./resources/images/product2-2.jpg");


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter @Setter
    private Integer id;

    @Column(name = "price", precision = 8, scale = 2)
    @Getter
    private BigDecimal price;

    @Lob
    @Column(name = "description")
    @Getter @Setter
    private String description;

    @Column(name = "name", nullable = false, length = 45)
    @Getter @Setter
    private String name;

    @Column(name = "quantity", nullable = false)
    @Getter
    private Integer quantity;

    @Column(name = "link", length = 200)
    @Getter @Setter
    private String link;

    @ManyToMany(cascade={CascadeType.PERSIST},fetch=FetchType.EAGER)
    @JoinTable(
            name = "article_category",
            joinColumns = {@JoinColumn(name ="idArticle")},
            inverseJoinColumns = {@JoinColumn(name="idCategory")}
    )
    @Getter
    private Set<Category> categories = new HashSet<Category>();

    public Article(BigDecimal price, String description, String name, Integer quantity, String link){
        this.price = (price.intValue() >= 0) ? price : BigDecimal.valueOf(0);
        this.description = description;
        this.name = name;
        this.quantity = quantity >= 0 ? quantity : 0;
        this.link = link;

    }

    public Article() {

    }

    /**
     * Setter for the article price, not generated cause we want to make sure it cannot be a negative number
     * @param price the new value for the article price
     */
    public void setPrice(BigDecimal price) {
        this.price =  (price.intValue() >= 0) ? price : BigDecimal.valueOf(0);
    }

    /**
     * Setter for the article quantity, because we don't want it to be negative
     * @param quantity the new value for the article quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = this.quantity = quantity >= 0 ? quantity : 0;;
    }

    /**
     * determines wether or not an article is "sellable", which means that it has a price
     * that is over 0 and a quantity that is over 0 as well
     * @return true if the article has non-null price and quantity
     */
    public boolean isSellable(){
        return this.price.compareTo(BigDecimal.ZERO) > 0 && this.quantity > 0;
    }


    /**
     * helper function to add a category to an article
     * and then add our article to the category
     * @param category the category we want to add to our article
     */
    public void addCategory(Category category) {
        this.categories.add(category);
        category.getArticles().add(this);
    }

    /**
     * helper function to remove a category from an article
     * it also removes the article from that category
     * @param category the category we want to remove
     */
    public void removeCategory(Category category) {
        this.categories.remove(category);
        category.getArticles().remove(this);
    }


    /**
     * checks if an article has a certain category
     * @param category the category you want to check
     * @return true if the category has the same id as a category in the article's set
     */
    public boolean hasCategory(Category category){
        Boolean hasCategory = false;
        for(Category c : this.getCategories()){
            //TODO apparently this isn't good (cf code quality in github, will fix later)
            if( c.getId() == category.getId()){
                hasCategory = true;
            }
        }
        return hasCategory;
    }

    /**
     * adds a list of categories to our article
     * @param categoryList a list containing all the categories you want to add
     */
    public void addCategoryList( List<Category> categoryList){
        for( Category cat : categoryList){
            this.addCategory(cat);
        }
    }

    /**
     * removes a list of categories from our article
     * @param categoryList a list containing all the categories you want to remove
     */
    public void removeCategoryList(List<Category> categoryList){
        for(Category cat: categoryList){
            this.removeCategory(cat);
        }
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (!(o instanceof Article)) return false;
        Article other = (Article) o;
        return Objects.equals(other.getId(), this.id);
    }


}