package org.example.config;

import org.example.exception.CustomException;
import org.example.model.Security;
import org.example.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${admin.login}")
  private String login;

  @Value("${admin.role}")
  private String role;

  @Value("${admin.password}")
  private String password;

  @Autowired private SecurityService service;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    try {
      auth.inMemoryAuthentication()
          .withUser(login)
          .password(passwordEncoder().encode(password))
          .roles(role);

      auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());

    } catch (CustomException e) {
    }
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return user -> {
      try {
        Security userFromDb = service.findByLogin(user);
        return User.withUsername(userFromDb.getLogin())
            .password(passwordEncoder().encode(userFromDb.getPassword()))
            .roles(userFromDb.getRole())
            .build();
      } catch (CustomException e) {
        throw new UsernameNotFoundException(e.getMessage());
      }
    };
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers(HttpMethod.GET, "/api/posts/**")
        .hasAnyRole("POSTS", "EDITOR", "ADMIN")
        .antMatchers(HttpMethod.GET, "/api/users/**")
        .hasAnyRole("USERS", "EDITOR", "ADMIN")
        .antMatchers(HttpMethod.GET, "/api/albums/**")
        .hasAnyRole("ALBUMS", "EDITOR", "ADMIN")
        .antMatchers(HttpMethod.GET, "/api/security/**")
        .hasAnyRole("SECURITY", "EDITOR", "ADMIN")
        .antMatchers(HttpMethod.POST, "/api/posts/**")
        .hasAnyRole("POSTS", "VIEWER", "ADMIN")
        .antMatchers(HttpMethod.POST, "/api/users/**")
        .hasAnyRole("USERS", "VIEWER", "ADMIN")
        .antMatchers(HttpMethod.POST, "/api/albums/**")
        .hasAnyRole("ALBUMS", "VIEWER", "ADMIN")
        .antMatchers(HttpMethod.POST, "/api/security/**")
        .hasAnyRole("SECURITY", "VIEWER", "ADMIN")
        .antMatchers(HttpMethod.DELETE, "/api/posts/**")
        .hasAnyRole("POSTS", "VIEWER", "ADMIN")
        .antMatchers(HttpMethod.DELETE, "/api/users/**")
        .hasAnyRole("USERS", "VIEWER", "ADMIN")
        .antMatchers(HttpMethod.DELETE, "/api/albums/**")
        .hasAnyRole("ALBUMS", "VIEWER", "ADMIN")
        .antMatchers(HttpMethod.DELETE, "/api/security/**")
        .hasAnyRole("SECURITY", "VIEWER", "ADMIN")
        .and()
        .httpBasic()
        .and()
        .csrf()
        .disable();
  }
}
