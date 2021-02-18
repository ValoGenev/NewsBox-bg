package scam.security.providers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import scam.security.authentication.UsernamePasswordAuthentication;
import scam.security.userdetails.UserDetailsServiceImpl;

public class UsernamePasswordAuthProvider implements AuthenticationProvider {

    private final UserDetailsServiceImpl userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsernamePasswordAuthProvider(UserDetailsServiceImpl userDetailsService,
                                        PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String passWord = (String) authentication.getCredentials();
        UserDetails user = userDetailsService.loadUserByUsername(username);

        if(passwordEncoder.matches(passWord,user.getPassword())){
            return new UsernamePasswordAuthentication(username,passWord,user.getAuthorities());
        }

        throw new BadCredentialsException(":(");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthentication.class.equals(aClass);
    }
}

