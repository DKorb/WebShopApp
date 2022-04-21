package WebShopApp.Application;

import WebShopApp.Application.Entity.Role;
import WebShopApp.Application.Entity.User;
import WebShopApp.Application.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testCreateUserWithAdminRole() {
        User adminUser = new User("dawid.korbecki@test.com","test","Dawid","Korbecki");
        Role adminRole = testEntityManager.find(Role.class, 1);
        adminUser.addRole(adminRole);

        User newAdminUser = userRepository.save(adminUser);

        assertThat(newAdminUser.getId()).isGreaterThan(0);
        assertThat(newAdminUser.getEmail()).isEqualTo("dawid.korbecki@test.com");
        assertThat(newAdminUser.getPassword()).isEqualTo("test");
        assertThat(newAdminUser.getFirstName()).isEqualTo("Dawid");
        assertThat(newAdminUser.getLastName()).isEqualTo("Korbecki");
    }

    @Test
    public void testGetUser() {
       User adminUser = userRepository.findById(1).get();
       assertThat(adminUser.getEmail()).isEqualTo("dawid.korbecki@test.com");
       assertThat(adminUser.getRoles()).isNotNull();
    }

    @Test
    public void testUpdateUser() {
        String testEmail = "dawid.korbecki@test.java";

        User adminUser = userRepository.findById(1).get();
        adminUser.setEmail(testEmail);
        adminUser.setStatus(true);

        userRepository.save(adminUser);

        assertThat(adminUser.getEmail()).isEqualTo(testEmail);
        assertThat(adminUser.isStatus()).isTrue();
    }
}