package com.example.demo.configuration;

import com.example.demo.filters.CustomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // Adding the roles
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("$2a$12$fzeaD3GPYVIQXPjdw.Ugfu6TqL19dbiZB8Z72sELIUL0fZpzGFTK6")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("$2a$12$fzeaD3GPYVIQXPjdw.Ugfu6TqL19dbiZB8Z72sELIUL0fZpzGFTK6")
                .roles("USER");
    }

    // Security config
    @Override
    protected void configure(HttpSecurity http) throws Exception {  // (2)
        http.authorizeRequests()
                .antMatchers("/public").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
                .antMatchers("/").hasAnyRole("USER", "ADMIN")
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll();

        http.addFilterAfter(customFilter(), FilterSecurityInterceptor.class);
    }

    // Function to encode the password.
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomFilter customFilter(){
        return new CustomFilter();
    }

}
