package com.pureamorous.spring_rbac_jwt.service;

import com.pureamorous.spring_rbac_jwt.entities.User;
import com.pureamorous.spring_rbac_jwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JPAUserService implements UserService, UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByUsername(username);
        if (user.isEmpty()) throw new UsernameNotFoundException("username not found");
        return user.get();
    }

    public Optional<User> find(String username){
        return repository.findByUsername(username);
    }

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }
}
