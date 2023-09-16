package com.finzly.bookmanager.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableJpaRepositories(basePackages = "com.finzly.bookmanager.database")
public class BookManagerConfiguration {
    private final ApplicationContext applicationContext;

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http,
                                                   final UserDetailsService userDetailsService) throws Exception {
        http.httpBasic(Customizer.withDefaults())
                .userDetailsService(userDetailsService)
                .formLogin((FormLoginConfigurer) -> new FormLoginConfigurer())
                .authorizeRequests()
                .requestMatchers("/login").permitAll()
                .requestMatchers(HttpMethod.GET, "**/user/**","**/book/**").authenticated()
                .requestMatchers(HttpMethod.POST, "**/user/**","**/book/**").authenticated()
                .anyRequest().authenticated();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
