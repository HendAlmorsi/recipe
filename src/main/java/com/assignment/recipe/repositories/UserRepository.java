package com.assignment.recipe.repositories;

import com.assignment.recipe.models.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public Optional<User> findByEmail(String email);

    @Query("SELECT COUNT(u.id) FROM User u WHERE u.email=:email")
    Long countUsersWithEmail(String email);
}
