package WebShopApp.Application.Service;

import WebShopApp.Application.Entity.Role;
import WebShopApp.Application.Entity.User;
import WebShopApp.Application.Repository.RoleRepository;
import WebShopApp.Application.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    public List<Role> listRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }
}