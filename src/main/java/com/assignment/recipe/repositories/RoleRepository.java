package com.assignment.recipe.repositories;

import com.assignment.recipe.models.Recipe;
import com.assignment.recipe.models.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
