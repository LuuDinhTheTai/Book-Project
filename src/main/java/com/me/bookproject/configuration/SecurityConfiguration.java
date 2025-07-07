package com.me.bookproject.configuration;

import com.me.bookproject.constant.Constant;
import com.me.bookproject.security.filter.TokenCookieAuthFilter;
import com.me.bookproject.security.jwt.CustomJwtDecoder;
import com.me.bookproject.security.service.CustomUserDetailsService;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
  
  @Value("${jwt.signerKey}")
  private String SIGNER_KEY;
  
  @Autowired
  private TokenCookieAuthFilter tokenCookieAuthFilter;
  @Autowired
  private CustomJwtDecoder jwtDecoder;
  
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
            rq -> rq
                          // PUBLIC ENDPOINTS
                          .requestMatchers(HttpMethod.GET,
                                  "/registration",
                                  "/login").permitAll()
                          .requestMatchers(HttpMethod.POST,
                                  "/register",
                                  "/login").permitAll()
                          
                          // ADMIN ENDPOINTS
                          .requestMatchers(HttpMethod.GET,
                                  "/admin").hasRole(Constant.ROLE.ADMIN)
                          
                          // USER ENDPOINTS
                          .requestMatchers(HttpMethod.GET,
                                  "/user").hasRole(Constant.ROLE.USER)
                          
                          .anyRequest().authenticated()
    );
    http.oauth2ResourceServer(
            oauth2 -> oauth2
                              .jwt(
                                      jwtConfigurer -> jwtConfigurer
                                                               .decoder(jwtDecoder)
                                                               .jwtAuthenticationConverter(jwtAuthenticationConverter())
                              )
                              .bearerTokenResolver(customTokenResolver())
    );
    http.csrf(
            c -> c.disable()
    );
    return http.build();
  }
  
  @Bean
  public BearerTokenResolver customTokenResolver() {
    return request -> {
      Cookie[] cookies = request.getCookies();
      if (cookies != null) {
        for (Cookie cookie : cookies) {
          if ("token".equals(cookie.getName())) {
            return cookie.getValue();
          }
        }
      }
      return null;
    };
  }
  
  @Bean
  public UserDetailsService userDetailsService() {
    return new CustomUserDetailsService();
  }
  
  @Bean
  JwtAuthenticationConverter jwtAuthenticationConverter() {
    JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
    
    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
    
    return jwtAuthenticationConverter;
  }
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
