package com.webshopapp.adminpanel.repository;


import com.webshopapp.adminpanel.entity.role.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
