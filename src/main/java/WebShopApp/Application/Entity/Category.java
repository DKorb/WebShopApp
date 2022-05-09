package WebShopApp.Application.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private Set<Category> children = new HashSet<>();

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

    public Category(String name, Category parent) {
        this.name = name;
        this.alias = name;
        this.image = "default.png";
        this.parent = parent;
    }
}
