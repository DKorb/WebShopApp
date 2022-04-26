package WebShopApp.Application.Repository;

import WebShopApp.Application.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("SELECT user from User user WHERE user.email = :email")
    public User getUserByEmail(@Param("email") String email);

    public Long countById(Integer id);
}
