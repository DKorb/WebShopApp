package com.webshopapp.common.entity.category;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 48, nullable = false, unique = true)
    private String name;

    @Column(length = 48, nullable = false, unique = true)
    private String alias;

    @Column(length = 128, nullable = false)
    private String image;

    @Column
    private boolean status;

    public Category(String name) {
        this.name = name;
        this.alias = name;
        this.image = "default.png";
    }

    public Category() {
    }

    public Category(Integer id) {
        this.id = id;
    }

    @Transient
    public String getImagePath() {
        if (this.id == null) return "/images/image_thumbnail.png";
        return "/category-images/" + this.id + "/" + this.image;
    }
}
