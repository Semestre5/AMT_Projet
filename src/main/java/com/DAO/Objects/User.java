package com.DAO.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "user")
@Entity
public class User {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

}