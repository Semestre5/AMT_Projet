package com.DAO.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@ToString
public class CartDetailId implements Serializable {
    private static final long serialVersionUID = -3245562932714359042L;
    @Column(name = "idActicle", nullable = false)
    private Integer idActicle;
    @Column(name = "idCart", nullable = false)
    private Integer idCart;

    public Integer getIdCart() {
        return idCart;
    }

    public Integer getIdActicle() {
        return idActicle;
    }


    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || Hibernate.getClass( this ) != Hibernate.getClass( o ) ) return false;
        CartDetailId entity = (CartDetailId) o;
        return Objects.equals( this.idActicle, entity.idActicle ) &&
                Objects.equals( this.idCart, entity.idCart );
    }
}
