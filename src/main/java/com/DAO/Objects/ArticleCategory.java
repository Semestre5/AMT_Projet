package com.DAO.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Table(name = "article_category", indexes = {
        @Index(name = "fk_article_has_category_article_idx", columnList = "idArticle"),
        @Index(name = "fk_article_has_category_category1_idx", columnList = "idCategory")
})
@Entity
public class ArticleCategory {
    @EmbeddedId
    private ArticleCategoryId id;

    public ArticleCategoryId getId() {
        return id;
    }

    public void setId( ArticleCategoryId id ) {
        this.id = id;
    }
}