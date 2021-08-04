package space.gavinklfong.demo.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userService;

    public CustomAuthenticationProvider(UserDetailsService userService) {
        super();
        this.userService = userService;
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        log.info("authenicate() - user={}, password={}", username, password);

        UserDetails user = userService.loadUserByUsername(username);

        if (user == null || !user.getUsername().equalsIgnoreCase(username)) {
            log.error("username not found");
            throw new BadCredentialsException("Username not found.");
        }

        if (!password.equals(user.getPassword())) {
            log.error("Wrong password");
            throw new BadCredentialsException("Wrong password.");
        }

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    public boolean supports(Class<?> arg0) {
        return true;
    }

}
