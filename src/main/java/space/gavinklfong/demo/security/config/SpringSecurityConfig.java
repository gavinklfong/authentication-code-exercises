/*
 * =============================================================================
 *
 *   Copyright (c) 2011-2016, The THYMELEAF team (http://www.thymeleaf.org)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 * =============================================================================
 */
package space.gavinklfong.demo.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    public SpringSecurityConfig() {
        super();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login-error")
            .and()
                .logout()
                .logoutSuccessUrl("/")
            .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/shared/**").hasAnyRole("USER","ADMIN")
            .and()
                .exceptionHandling()
                .accessDeniedPage("/403");

    }


    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(new CustomAuthenticationProvider(initializeUserService()));

//        auth
//                .inMemoryAuthentication()
//                .withUser("jim").password("{noop}demo").roles("ADMIN")
//                .and()
//                .withUser("bob").password("{noop}demo").roles("USER")
//                .and()
//                .withUser("ted").password("{noop}demo").roles("USER","ADMIN");
    }

    private UserDetailsService initializeUserService() {
        List<UserDetails> users = Arrays.asList(
                User.builder().username("jim").password("demo").roles("ADMIN").build(),
                User.builder().username("bob").password("demo").roles("USER").build(),
                User.builder().username("ted").password("demo").roles("ADMIN", "USER").build()
        );

        return new InMemoryUserDetailsManager(users);
    }






}
