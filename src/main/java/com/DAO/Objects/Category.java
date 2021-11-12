package com.DAO.Objects;

import javax.persistence.*;

@Table(name = "category", indexes = {
        @Index(name = "name_UNIQUE", columnList = "name", unique = true)
})
@Entity
public class Category {

    public Category(String name ) {

        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }
}