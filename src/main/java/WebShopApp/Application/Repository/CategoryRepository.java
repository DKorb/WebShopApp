package WebShopApp.Application.Repository;

import WebShopApp.Application.Entity.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {

}