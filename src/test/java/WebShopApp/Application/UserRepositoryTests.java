package WebShopApp.Application;

import WebShopApp.Application.Entity.Role;
import WebShopApp.Application.Entity.User;
import WebShopApp.Application.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

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
    public void testCreateUserWithTwoRoles() {
        User userWithTwoRoles = new User("jan.kowalski@test.com","test","Jan","Kowalski");
        Role adminRole = testEntityManager.find(Role.class, 1);
        Role supplierRole = testEntityManager.find(Role.class, 2);

        userWithTwoRoles.addRole(adminRole);
        userWithTwoRoles.addRole(supplierRole);

        User newUserWithTwoRoles = userRepository.save(userWithTwoRoles);

        assertThat(newUserWithTwoRoles.getId()).isGreaterThan(0);
        assertThat(newUserWithTwoRoles.getEmail()).isEqualTo("jan.kowalski@test.com");
        assertThat(newUserWithTwoRoles.getPassword()).isEqualTo("test");
        assertThat(newUserWithTwoRoles.getFirstName()).isEqualTo("Jan");
        assertThat(newUserWithTwoRoles.getLastName()).isEqualTo("Kowalski");
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

    @Test
    public void testCreateUserWithEncryptedPass() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode("test");
        User userWithEncryptedPass = new User("stefan.zeromski@test.com", encodedPassword,
                "Stefan", "Zeromski");
        userWithEncryptedPass.setStatus(true);
        Role adminRole = testEntityManager.find(Role.class, 1);
        userWithEncryptedPass.addRole(adminRole);

        userRepository.save(userWithEncryptedPass);

        assertThat(userWithEncryptedPass.isStatus()).isTrue();
        assertThat(userWithEncryptedPass.getPassword()).isEqualTo(encodedPassword);
    }

    @Test
    public void testGetUserByEmail() {
        String email = "dawid.korbecki@test.java";
        User user = userRepository.getUserByEmail(email);

        assertThat(user).isNotNull();
    }
}