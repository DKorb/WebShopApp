package WebShopApp.Application;

import WebShopApp.Application.Entity.Role;
import WebShopApp.Application.Repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository repository;

    @Test
    public void testCreateRole() {
        Role roleAdmin = new Role("Admin");
        Role savedRole = this.repository.save(roleAdmin);

        assertThat(savedRole.getId()).isGreaterThan(0);
    }
}