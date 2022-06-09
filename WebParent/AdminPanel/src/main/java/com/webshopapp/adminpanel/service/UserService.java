package com.webshopapp.adminpanel.service;


import com.webshopapp.common.entity.role.Role;
import com.webshopapp.common.entity.user.User;
import com.webshopapp.adminpanel.exceptions.UserNotFoundException;
import com.webshopapp.adminpanel.repository.RoleRepository;
import com.webshopapp.adminpanel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class UserService {

    public static final int USERS_PER_PAGE = 10;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> listAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public List<Role> listAllRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    public void save(User user) {
        boolean isUpdatingUser = (user.getId() != null);
        if (isUpdatingUser) {
            User existingUser = userRepository.findById(user.getId()).get();
            if (user.getPassword().isEmpty()) {
                user.setPassword(existingUser.getPassword());
            } else {
                encodePassword(user);
            }
        } else {
            encodePassword(user);
        }
        userRepository.save(user);
    }

    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public boolean isEmailUnique(String email) {
        User userByEmail = userRepository.getUserByEmail(email);
        if (userByEmail == null) {
            return true;
        }
        return false;
    }

    public User getUser(Integer id) throws UserNotFoundException {
        try {
            return userRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new UserNotFoundException("Could not find aby user with ID " + id);
        }
    }

    public void deleteUser(Integer id) throws UserNotFoundException {
        Long countById = userRepository.countById(id);

        if (countById == null || countById == 0) {
            throw new UserNotFoundException("Could not find aby user with ID " + id);
        }

        userRepository.deleteById(id);
    }

    public void updateUserStatus(Integer id, boolean status) {
        userRepository.updateStatus(id, status);
    }

    public Page<User> listByPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, USERS_PER_PAGE);
        return userRepository.findAll(pageable);
    }
}