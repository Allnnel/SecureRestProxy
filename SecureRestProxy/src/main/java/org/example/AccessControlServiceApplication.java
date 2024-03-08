package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Bean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AccessControlServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccessControlServiceApplication.class, args);
    }

//    @EnableWebSecurity
//    @EnableGlobalMethodSecurity(prePostEnabled = true)
//    public static class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.inMemoryAuthentication()
//                    .withUser("admin").password("adminpass").roles("ADMIN")
//                    .and()
//                    .withUser("posts").password("postspass").roles("POSTS")
//                    .and()
//                    .withUser("users").password("userspass").roles("USERS")
//                    .and()
//                    .withUser("albums").password("albumspass").roles("ALBUMS");
//        }
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.authorizeRequests()
//                    .antMatchers("/api/posts/**").hasAnyRole("ADMIN", "POSTS")
//                    .antMatchers("/api/users/**").hasAnyRole("ADMIN", "USERS")
//                    .antMatchers("/api/albums/**").hasAnyRole("ADMIN", "ALBUMS")
//                    .and().httpBasic()
//                    .and().csrf().disable();
//        }
//
//        @Bean
//        public PasswordEncoder getPasswordEncoder() {
//            return NoOpPasswordEncoder.getInstance();
//        }
//    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

