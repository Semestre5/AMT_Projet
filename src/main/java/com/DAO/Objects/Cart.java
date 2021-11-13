package com.DAO.Objects;

import javax.persistence.*;

@Table(name = "cart", indexes = {
        @Index(name = "idUser_UNIQUE", columnList = "idUser", unique = true)
})
@Entity
public class Cart {
    public Cart( Integer id, User idUser ) {
        this.id = id;
        this.idUser = idUser;
    }

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "idUser", nullable = false)
    private User idUser;
    public User getIdUser() {
        return idUser;
    }

    public void setIdUser( User idUser ) {
        this.idUser = idUser;
    }

    public Integer getCartId() {
        return id;
    }

    public void setCartId( Integer id ) {
        this.id = id;
    }
}