package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bookstore.entity.User;

import jakarta.transaction.Transactional;



public interface IUserRepository extends JpaRepository<User,Integer> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByUsername(String username);

    User findById(Long user);

    User findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_role (user_id, role_id) VALUE (?1, ?2)", nativeQuery = true)
    void addRoleToUser(Long userId , Long roleId);

    @Query("SELECT u.id FROM User u WHERE u.username = ?1")
    Long getUserIdByUserName(String username);

    @Query(value = "SELECT r.name FROM role r INNER JOIN user_role u_r ON r.id = u_r.role_id WHERE u_r.user_id = ?1", nativeQuery = true)
    String[] getRoleOfUser(Long userId);


    @Modifying
    @Transactional
    @Query(value = "UPDATE user_role SET role_id = ?2 WHERE user_id = ?1", nativeQuery = true)
    void updateRoleToUser(Long userId, long roleId);
}   