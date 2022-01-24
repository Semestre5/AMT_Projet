package com.DAO.Objects;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Table(name = "category", indexes = {
        @Index(name = "name_UNIQUE", columnList = "name", unique = true)
})
@Entity
@Proxy(lazy=false)
public class Category {
    public Category() {
    }

    public Category( String name ) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    @Getter @Setter
    private String name;

    @ManyToMany(mappedBy = "categories",fetch=FetchType.EAGER,cascade={CascadeType.MERGE,CascadeType.PERSIST})
    @Getter
    private Set<Article> articles = new HashSet<Article>();

    /**
     * helper function to add an article to our category
     * @param article the article we want to add
     */
    public void addArticle(Article article) {
        this.articles.add(article);
        article.getCategories().add(this);
    }

    /**
     * helper function to remove an article from our category
     * @param article the article we want to remove
     */
    public void removeArticle(Article article) {
        this.articles.remove(article);
        article.getCategories().remove(this);
    }

    /**
     * adds a list of articles to our category
     * @param listArticles a list containing all the articles you want to add
     */
    public void addArticlesListToCategory( List<Article> listArticles){
        for (Article art : listArticles){
            this.addArticle(art);
        }
    }

    /**
     * removes a list of articles from our category
     * @param listArticles a list containing all the articles you want to remove
     */
    public void removeArticlesListToCategory( List<Article> listArticles){
        for (Article art : listArticles){
            this.removeArticle(art);
        }
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (!(o instanceof Category)) return false;
        Category other = (Category) o;
        return Objects.equals(other.getId(), this.id);
    }
}