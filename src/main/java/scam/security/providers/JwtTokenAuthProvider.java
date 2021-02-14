package scam.security.providers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import scam.security.authentication.JwtAuthentication;
import scam.security.authentication.UsernamePasswordAuthentication;
import scam.security.jwt.JwtTokenUtil;
import scam.security.userdetails.UserDetailsServiceImpl;

public class JwtTokenAuthProvider  implements AuthenticationProvider {

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public JwtTokenAuthProvider(UserDetailsServiceImpl userDetailsService,
                                 JwtTokenUtil jwtTokenUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String token = (String) authentication.getPrincipal();
        String userName = jwtTokenUtil.getUsernameFromToken(token);

        UserDetails loggedInUser = userDetailsService.loadUserByUsername(userName);

        if(jwtTokenUtil.validateToken(token,loggedInUser)){
            return new UsernamePasswordAuthentication(loggedInUser.getUsername(),loggedInUser.getPassword(),loggedInUser.getAuthorities());
        }

        throw  new BadCredentialsException(":(");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return JwtAuthentication.class.equals(aClass);
    }
}
