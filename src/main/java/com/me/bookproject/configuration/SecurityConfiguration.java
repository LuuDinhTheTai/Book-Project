package com.me.bookproject.configuration;

import com.me.bookproject.security.jwt.CustomJwtDecoder;
import com.me.bookproject.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
  
  @Autowired
  private CustomJwtDecoder jwtDecoder;
  
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
            rq -> rq
                          // PUBLIC ENDPOINTS
                          .requestMatchers(HttpMethod.GET,
                                  "/registration").permitAll()
                          .anyRequest().authenticated()
    );
    http.oauth2ResourceServer(
            oauth2 -> oauth2.jwt(
                    jwtConfigurer -> jwtConfigurer
                                             .jwtAuthenticationConverter(jwtAuthenticationConverter())
            )
    );
    http.csrf(
            c -> c.disable()
    );
    return http.build();
  }
  
  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService());
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
  }
  
  @Bean
  public UserDetailsService userDetailsService() {
    return new CustomUserDetailsService();
  }
  
  @Bean
  JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//    jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
    
    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
    
    return jwtAuthenticationConverter;
  }
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
