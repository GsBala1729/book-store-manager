package com.finzly.bookmanager.loader;

import com.finzly.bookmanager.models.UserRetrieveRequest;
import com.finzly.bookmanager.service.IUserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class UserDetailsLoader implements UserDetailsService {
    private static final String SALT_STRING = "findMe@1234";
    private final IUserService userService;
    private Map<String, UserDetails> userDetailsMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        userService.queryUsers(UserRetrieveRequest.builder().build()).stream()
                .map(user -> User.builder()
                        .username(user.getUserName())
                        .password(SALT_STRING)
                        .roles(user.getUserRole().toUpperCase())
                        .build()).forEach(user -> userDetailsMap.put(user.getUsername(), user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetailsMap.get(username);
    }
}
