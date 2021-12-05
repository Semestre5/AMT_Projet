package com.DAO.Objects;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ArticleCategoryId implements Serializable {
    private static final long serialVersionUID = 2366224517268388400L;
    @Column(name = "idArticle", nullable = false)
    private Integer idArticle;
    @Column(name = "idCategory", nullable = false)
    private Integer idCategory;

    public ArticleCategoryId(Integer idArticle, Integer idCategory){
        this.idArticle = idArticle;
        this.idCategory = idCategory;
    }

    public ArticleCategoryId() {

    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory( Integer idCategory ) {
        this.idCategory = idCategory;
    }

    public Integer getIdArticle() {
        return idArticle;
    }

    public void setIdArticle( Integer idArticle ) {
        this.idArticle = idArticle;
    }

    @Override
    public int hashCode() {
        return Objects.hash( idArticle, idCategory );
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || Hibernate.getClass( this ) != Hibernate.getClass( o ) ) return false;
        ArticleCategoryId entity = (ArticleCategoryId) o;
        return Objects.equals( this.idArticle, entity.idArticle ) &&
                Objects.equals( this.idCategory, entity.idCategory );
    }
}