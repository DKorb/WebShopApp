package WebShopApp.Application;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PasswordEncoderTests {

    @Test
    public void testEncodePassword() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = "testPassword";
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        boolean isValid = bCryptPasswordEncoder.matches(password, encodedPassword);

        assertThat(isValid).isTrue();
    }
}