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

//TODO: peut etre enlever les @SETTER parce qu'on peut modifier depuis le navigateur les trucs de la DB
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
    @Getter @Setter
    private BigDecimal price;

    @Lob
    @Column(name = "description")
    @Getter @Setter
    private String description;

    @Column(name = "name", nullable = false, length = 45)
    @Getter @Setter
    private String name;

    @Column(name = "quantity", nullable = false)
    @Getter @Setter
    private Integer quantity;

    @Column(name = "link", length = 200)
    @Getter @Setter
    private String link;

    public Article(BigDecimal price, String description, String name, Integer quantity, String link){
        this.price = (price.intValue() >= 0) ? price : BigDecimal.valueOf(0);
        this.description = description;
        this.name = name;
        this.quantity = quantity >= 0 ? quantity : 0;
        this.link = link;

    }

    public Article() {

    }

    public boolean isSellable(){
        return this.price.compareTo(BigDecimal.ZERO) > 0 && this.quantity > 0;
    }

    @ManyToMany(cascade={CascadeType.PERSIST},fetch=FetchType.EAGER)
    @JoinTable(
            name = "article_category",
            joinColumns = {@JoinColumn(name ="idArticle")},
            inverseJoinColumns = {@JoinColumn(name="idCategory")}
    )
    @Getter
    private Set<Category> categories = new HashSet<Category>();

    public void addCategory(Category category) {
        this.categories.add(category);
        category.getArticles().add(this);
    }

    public void removeCategory(Category category) {
        this.categories.remove(category);
        category.getArticles().remove(this);
    }

    //TODO contains marchait pas pour ça, peut-être modifier le equals ? jsp si c'est propre ou pas
    public boolean hasCategory(Category category){
        Boolean hasCategory = false;
        for(Category c : this.getCategories()){
            if( c.getId() == category.getId()){
                hasCategory = true;
            }
        }
        return hasCategory;
    }

    public void addCategoryList( List<Category> categoryList){
        categories.addAll( categoryList );
    }

    public boolean equals(Object o){
        if (o == this) return true;
        if (!(o instanceof Article)) return false;
        Article other = (Article) o;
        return Objects.equals(other.getId(), this.id);
    }


}