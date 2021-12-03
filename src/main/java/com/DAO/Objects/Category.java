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

    public void addArticle(Article article) {
        this.articles.add(article);
        article.getCategories().add(this);
    }

    public void removeArticle(Article article) {
        this.articles.remove(article);
        article.getCategories().remove(this);
    }

    public void addArticleToCategory(Article article){
        articles.add(article);
    }
    public void addArticlesListToCategory( List<Article> listArticles){
        articles.addAll(listArticles);
    }

    public boolean equals(Object o){
        if (o == this) return true;
        if (!(o instanceof Article)) return false;
        Category other = (Category) o;
        return Objects.equals(other.getId(), this.id);
    }
}