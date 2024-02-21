package ru.gb.springdemo.service;

import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Role;
import ru.gb.springdemo.model.User;
import ru.gb.springdemo.repository.RoleRepository;
import ru.gb.springdemo.repository.UserRepository;

import java.util.List;

@Service
public class UserDetailsService {
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    public UserDetailsService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }
    public List<Role> getRoles(){
        return roleRepository.findAll();
    }
    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
