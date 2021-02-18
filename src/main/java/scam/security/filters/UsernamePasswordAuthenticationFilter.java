package scam.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;
import scam.dto.user.UserLoginRegisterDto;
import scam.security.authentication.UsernamePasswordAuthentication;
import scam.security.jwt.JwtTokenUtil;
import scam.security.userdetails.UserDetailsImpl;
import scam.security.wrappers.WrappedHttpServletRequest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class UsernamePasswordAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    public UsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        httpServletRequest = new WrappedHttpServletRequest(httpServletRequest);

        UserLoginRegisterDto user = new ObjectMapper().readValue(IOUtils.toString(httpServletRequest.getInputStream()), UserLoginRegisterDto.class);

        System.out.println(user.getUsername() + " "+ user.getPassword());

        Authentication a = new UsernamePasswordAuthentication(user.getUsername(),user.getPassword());
        a=authenticationManager.authenticate(a);

        String loggedUserUsername = (String) a.getPrincipal();
        String loggedUserPassword = (String) a.getCredentials();
        Set<GrantedAuthority> loggedUserAuthorities= new HashSet<>(a.getAuthorities());

        if(a.isAuthenticated()){
            String token = jwtTokenUtil.generateToken(new UserDetailsImpl(loggedUserUsername,loggedUserPassword,loggedUserAuthorities));

            Cookie cookie = new Cookie("jwt-token",token);

            httpServletResponse.addCookie(cookie);

        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/config/api/v1/login");
    }
}
