package ru.gb.springdemo.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.gb.springdemo.repository.UserRepository;

import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ru.gb.springdemo.model.User user = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(user.getName(), user.getPassword(), List.of(
                new SimpleGrantedAuthority(user.getRole())
        ));
    }
}
