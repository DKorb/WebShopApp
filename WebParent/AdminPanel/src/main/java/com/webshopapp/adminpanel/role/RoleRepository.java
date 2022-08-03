package com.webshopapp.adminpanel.role;


import com.webshopapp.common.entity.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}