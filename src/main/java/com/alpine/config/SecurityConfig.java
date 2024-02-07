/**
 * Configuration class responsible for setting up security configurations for the Alpine Book Store application.
 * This class extends WebSecurityConfigurerAdapter to provide custom security configurations.
 */
package com.alpine.config;
import com.alpine.service.impl.UserSecurityService;
import com.alpine.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private Environment env;
    @Autowired
    private UserSecurityService userSecurityService;
    /**
     * Configures the password encoder to be used for encoding passwords.
     * @return BCryptPasswordEncoder instance for password encoding.
     */
    private BCryptPasswordEncoder passwordEncoder() {
        return SecurityUtility.passwordEncoder();
    }

    // Array of public matchers for permit all access
    private static final String[] PUBLIC_MATCHERS = {
            "/css/**",
            "/js/**",
            "/images/**",
            "/",
            "myAccount",
            "/newUser",
            "/login",
            "logout",
            "/forgetPassword",
            "/fonts/**",
            "/bookshelf",
            "/bookDetails",
            "/hours",
            "/faq",
            "/searchByCategory",
            "/searchBook"
    };

    /**
     * Configures HTTP security settings for the application.
     * @param http HttpSecurity object to configure security settings.
     * @throws Exception Exception if any error occurs during configuration.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().
                /* antMatchers ("/**").*/
                antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();

        http
                .csrf().disable().cors().disable().formLogin().failureUrl("/login?error")
                .defaultSuccessUrl("/").loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll()
                .and()
                .rememberMe();
    }

    /**
     * Configures global authentication manager to use custom user details service and password encoder.
     * @param auth AuthenticationManagerBuilder object to configure authentication manager.
     * @throws Exception Exception if any error occurs during configuration.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
    }
}
