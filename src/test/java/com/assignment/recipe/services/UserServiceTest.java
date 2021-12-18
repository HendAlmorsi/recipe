package com.assignment.recipe.services;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.assignment.recipe.dto.UserDto;
import com.assignment.recipe.exceptions.ItemNotFoundException;
import com.assignment.recipe.models.User;
import com.assignment.recipe.repositories.RecipeRepository;
import com.assignment.recipe.repositories.RoleRepository;
import com.assignment.recipe.repositories.UserRepository;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServiceTest {

    UserRepository userRepository = mock(UserRepository.class);
    RoleRepository roleRepository = mock(RoleRepository.class);
    UserService userService = new UserService(new BCryptPasswordEncoder(), userRepository, roleRepository);

    @Test
    public void registerNewUserAccount() {
        when(userRepository.save(any())).thenReturn(new User());

        UserDto dto = new UserDto();
        dto.setFirstName("test");
        dto.setLastName("test");
        dto.setEmail("test@test.test");
        dto.setPassword("1234");
        dto.setMatchingPassword("1234");

        assertNotNull(userService.registerNewUserAccount(dto));
    }

    @Test
    public void registerNewUserAccountValidation() {
        when(userRepository.save(any())).thenReturn(new User());

        UserDto dto = new UserDto();
        dto.setFirstName("test");
        dto.setLastName("test");
        dto.setEmail("test@test.test");
        dto.setPassword("1234");
        dto.setMatchingPassword("12345");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userService.registerNewUserAccount(dto));

        assertEquals("Passwords doesn't match.", exception.getMessage());
    }

    @Test
    public void updateUser() {
        when(userRepository.getById(any())).thenReturn(null);

        UserDto dto = new UserDto();
        ItemNotFoundException exception = assertThrows(ItemNotFoundException.class, () -> userService.updateUser(dto, 1L));

        assertEquals("Item not found", exception.getMessage());

        User user = mock(User.class);
        when(user.getEmail()).thenReturn("email@email.com");
        when(userRepository.getById(any())).thenReturn(user);
        dto.setEmail("test@test.test");

        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> userService.updateUser(dto, 1L));

        assertEquals("Email can not be updated.", exception2.getMessage());
    }
}