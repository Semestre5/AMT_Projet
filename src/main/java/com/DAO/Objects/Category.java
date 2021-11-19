package com.DAO.Objects;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "category", indexes = {
        @Index(name = "name_UNIQUE", columnList = "name", unique = true)
})
@Entity
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

    @ManyToMany(mappedBy = "categories")
    private List<Article> articles;

}