package hr.tvz.pios.caffebarmanager.repository;

import hr.tvz.pios.caffebarmanager.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    User user;
    @BeforeEach
    void setUp() {
        user=new User();
        user.setName("Roko");
        user.setLastName("Ročić");
        user.setEmail("roko@email.com");
        user.setPassword("password");
        user.setRole(2);
        userRepository.save(user);
    }
    @AfterEach
    void tearDown() {
        user=null;
        userRepository.deleteAll();
    }
    @Test
    void findByEmail_FoundTest()
    {
        Optional<User> userOptional= userRepository.findByEmail("roko@email.com");
        assertThat(userOptional.get().getName()).isEqualTo(user.getName());
        assertThat(userOptional.get().getLastName()).isEqualTo(user.getLastName());
        assertThat(userOptional.get().getPassword()).isEqualTo(user.getPassword());
        assertThat(userOptional.get().getRole()).isEqualTo(user.getRole());
    }
    @Test
    void findByEmail_NotFoundTest()
    {
        Optional<User> userOptional= userRepository.findByEmail("drugi@email.com");
        assertThat(userOptional.isEmpty()).isTrue();
    }
}
