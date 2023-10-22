package com.finzly.bookmanager.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@Configuration
//@EnableWebSecurity
@RequiredArgsConstructor
@EnableJpaRepositories(basePackages = "com.finzly.bookmanager.database")
public class BookManagerConfiguration {/*
    private final ApplicationContext applicationContext;

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http,
                                                   final UserDetailsService userDetailsService) throws Exception {
*//*        http.httpBasic(Customizer.withDefaults())
                .userDetailsService(userDetailsService)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeRequests()
                .requestMatchers("/**").permitAll();
                //.anyRequest().authenticated();*//*

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowCredentials(true); // Allow credentials (e.g., cookies, HTTP authentication)
        corsConfig.setAllowedOrigins(Collections.singletonList("localhost:4200"));
        corsConfig.addAllowedHeader("*"); // Allow all headers
        corsConfig.addAllowedMethod("*"); // Allow all HTTP methods

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return new CorsFilter(source);
    }
*/
}

