package WebShopApp.Application;

import WebShopApp.Application.entity.role.Role;
import WebShopApp.Application.repository.RoleRepository;
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
    private RoleRepository roleRepository;

    @Test
    public void testCreateAdminRole() {
        Role roleAdmin = new Role("Admin");
        Role savedRole = this.roleRepository.save(roleAdmin);

        assertThat(savedRole.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateSupplierRole() {
        Role roleSupplier = new Role("Supplier");
        Role savedRole = this.roleRepository.save(roleSupplier);

        assertThat(savedRole.getId()).isGreaterThan(0);
    }
}