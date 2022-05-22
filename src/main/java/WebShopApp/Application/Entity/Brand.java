package WebShopApp.Application.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 48, nullable = false, unique = true)
    private String name;

    @Column(length = 128, nullable = false)
    private String logo;

    @ManyToMany
    @JoinTable(
            name = "brands_categories",
            joinColumns = @JoinColumn(name = "brand_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    public Brand() {

    }

    public Brand(String name) {
        this.name = name;
        this.logo = "default-logo.png";
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", categories=" + categories +
                '}';
    }

    @Transient
    public String getLogoPath() {
        if (this.id == null) return "/images/image_thumbnail.png";
        return "brand-images/" + this.id + "/" + this.logo;
    }
}