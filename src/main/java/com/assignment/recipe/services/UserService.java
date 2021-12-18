package com.assignment.recipe.services;

import static com.assignment.recipe.models.Role.USER;

import com.assignment.recipe.dto.UserDto;
import com.assignment.recipe.exceptions.ItemNotFoundException;
import com.assignment.recipe.models.Role;
import com.assignment.recipe.models.User;
import com.assignment.recipe.repositories.RoleRepository;
import com.assignment.recipe.repositories.UserRepository;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User registerNewUserAccount(UserDto userDto) throws IllegalArgumentException {
        if (emailExist(userDto.getEmail())) {
            throw new IllegalArgumentException(
                    "There is an account with that email adress:" + userDto.getEmail());
        }
        if(!userDto.verifyPassword()){
            throw new IllegalArgumentException("Passwords doesn't match.");
        }
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setRoles(new HashSet(Arrays.asList(createRoleIfNotFound(USER))));
        return userRepository.save(user);
    }

    public List<User> listAllUser() {
        return userRepository.findAll();
    }

    public void updateUser(UserDto userDto, Long id) throws ItemNotFoundException, IllegalArgumentException{
        User existingUser = Optional.ofNullable(userRepository.getById(id))
                        .orElseThrow(() -> new ItemNotFoundException("Item not found"));
        if(!existingUser.getEmail().equals(userDto.getEmail())) {
            throw new IllegalArgumentException("Email can not be updated.");
        }
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        userRepository.save(existingUser);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

     public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private boolean emailExist(String email) {
        return userRepository.countUsersWithEmail(email) > 0;
    }

    @Transactional
    Role createRoleIfNotFound(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        if (role.isPresent()) {
            return role.get();
        }
        Role newRole = new Role();
        newRole.setName(name);
        roleRepository.save(newRole);
        return newRole;
    }
}
