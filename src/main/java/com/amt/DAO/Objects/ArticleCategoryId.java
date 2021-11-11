package com.amt.DAO.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@ToString
public class ArticleCategoryId implements Serializable {
    private static final long serialVersionUID = 757640453026037402L;
    @Column(name = "idArticle", nullable = false)
    private Integer idArticle;
    @Column(name = "idCategory", nullable = false)
    private Integer idCategory;

    public Integer getIdCategory() {
        return idCategory;
    }

    public Integer getIdArticle() {
        return idArticle;
    }
}

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || Hibernate.getClass( this ) != Hibernate.getClass( o ) ) return false;
        ArticleCategoryId entity = (ArticleCategoryId) o;
        return Objects.equals( this.idArticle, entity.idArticle ) &&
                Objects.equals( this.idCategory, entity.idCategory );
    }

    @Override
    public int hashCode() {
        return Objects.hash( idArticle, idCategory );
    }