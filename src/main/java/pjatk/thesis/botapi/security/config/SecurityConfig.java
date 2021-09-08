package pjatk.thesis.botapi.security.config;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import pjatk.thesis.botapi.security.filter.AuthFilter;
import pjatk.thesis.botapi.security.filter.AuthorizationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableSpringDataWebSupport
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final Algorithm algorithm;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    public boolean hasUserId(Authentication authentication, Long userId) {
        System.out.println(userId);
        return true;
        // do your check(s) here
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthFilter authFilter = new AuthFilter(authenticationManagerBean(), algorithm);
        AuthorizationFilter authorizationFilter = new AuthorizationFilter(algorithm);
        authFilter.setFilterProcessesUrl("/api/login");
        http
                .cors().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/login").permitAll()
                .and().authorizeRequests()
//                .antMatchers(GET, "/api/security/users/")
//                    .hasAnyAuthority("ROLE_USER")
//                .antMatchers(GET, "/api/security/users/{userId}/")
//                    .hasAnyAuthority("ROLE_ADMIN")
//                .antMatchers(GET, "/api/security/users/{userId}/")
//                    .access("@securityConfig.hasUserId(authentication,#userId)")

//                .antMatchers(POST, "api/security/role-user/**").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilter(authFilter)
                .addFilterBefore(authorizationFilter, AuthFilter.class);
    }


    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
