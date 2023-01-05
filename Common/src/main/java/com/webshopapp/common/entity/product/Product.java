package com.webshopapp.common.entity.product;

import com.webshopapp.common.entity.category.Category;
import com.webshopapp.common.entity.brand.Brand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 64, nullable = false)
    private String name;

    @Column(unique = true, length = 64, nullable = false)
    private String alias;

    @Column(name = "full_description", length = 4096, nullable = false)
    private String fullDescription;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "updated_time")
    private Date updatedTime;

    private boolean status;

    @Column(name = "in_stock")
    private boolean inStock;

    private float price;

    @Column(length = 128, nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductDetail> details = new ArrayList<>();

    public void addDetails(String name, String value) {
        this.details.add(new ProductDetail(name, value, this));
    }

    public void addDetails(Integer id, String name, String value) {
        this.details.add(new ProductDetail(id, name, value, this));
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Transient
    public String getImagePath() {
        if (this.id == null) return "/images/image_thumbnail.png";
        return "/product-images/" + this.id + "/" + this.image;
    }
}