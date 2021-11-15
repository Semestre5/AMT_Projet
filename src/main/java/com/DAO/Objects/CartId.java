package com.DAO.Objects;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CartId implements Serializable {
    private static final long serialVersionUID = -7193782011114742332L;
    @Column(name = "idArticle", nullable = false)
    private Integer idArticle;
    @Column(name = "idUser", nullable = false)
    private Integer idUser;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser( Integer idUser ) {
        this.idUser = idUser;
    }

    public Integer getIdArticle() {
        return idArticle;
    }

    public void setIdArticle( Integer idArticle ) {
        this.idArticle = idArticle;
    }

    @Override
    public int hashCode() {
        return Objects.hash( idUser, idArticle );
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || Hibernate.getClass( this ) != Hibernate.getClass( o ) ) return false;
        CartId entity = (CartId) o;
        return Objects.equals( this.idUser, entity.idUser ) &&
                Objects.equals( this.idArticle, entity.idArticle );
    }
}