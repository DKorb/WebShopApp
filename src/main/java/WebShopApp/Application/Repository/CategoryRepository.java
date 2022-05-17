package WebShopApp.Application.Repository;

import WebShopApp.Application.Entity.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {

    public Long countById(Integer id);

    @Query("UPDATE Category categories SET categories.status = ?2 WHERE categories.id = ?1")
    @Modifying
    public void updateStatus(Integer id, boolean status);
}