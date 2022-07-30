package com.webshopapp.adminpanel.user;


import com.webshopapp.common.entity.user.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    @Query("SELECT user from User user WHERE user.email = :email")
    User getUserByEmail(@Param("email") String email);

    Long countById(Integer id);

    @Query("UPDATE User user SET user.status = ?2 WHERE user.id = ?1")
    @Modifying
    void updateStatus(Integer id, boolean status);
}
