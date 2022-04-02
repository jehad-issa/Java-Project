package com.hosttheworld.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    private UserDetailsService userDetailsService;
    
    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
	
    // add Bcyrpt bean
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder2() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
            authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/img/**","/**/registration", "/webjars/**").permitAll()
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .anyRequest().authenticated()
                .and()
                
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                
            .logout()
                .permitAll();
    }
	
    // 1
    // @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder2());
    } 
}
