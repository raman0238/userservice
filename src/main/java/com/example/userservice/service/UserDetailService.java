package com.example.userservice.service;

import com.example.userservice.model.Student;
import com.example.userservice.model.User;
import com.example.userservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> user = userRepository.findByUserName(username);
        if(user.size() > 0) {
            UserDetailsImpl userDetails = new UserDetailsImpl(user.get(0).getUserName(), user.get(0).getPassword());
            return userDetails;
        } else {
            throw new UsernameNotFoundException("No user Found");
        }
    }
}
