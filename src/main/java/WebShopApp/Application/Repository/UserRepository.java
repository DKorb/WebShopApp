package WebShopApp.Application.Repository;

import WebShopApp.Application.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
