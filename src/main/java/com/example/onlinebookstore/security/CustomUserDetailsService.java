package com.example.onlinebookstore.security;

import com.example.onlinebookstore.exception.EntityNotFoundException;
import com.example.onlinebookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("Can't find user with username: "
                        + username));
    }
}
