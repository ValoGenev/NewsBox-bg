package scam.security.filters;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import scam.security.authentication.JwtAuthentication;
import scam.security.jwt.JwtTokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public JwtTokenAuthenticationFilter(AuthenticationManager authenticationManager,JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil=jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        System.out.println("JWT FILTER CALLED");
//
//        Cookie[] cookies = httpServletRequest.getCookies();
//
//        Cookie jwtCookie =  Arrays.stream(cookies)
//                .filter(cookie -> cookie.getName().equals("jwt-token"))
//                .findFirst().orElseThrow(() -> new BadCredentialsException("BAD CREDENTIALS"));
//
//
//        Authentication auth= authenticationManager.authenticate(new JwtAuthentication(jwtCookie.getValue(),null));
//
//        if(auth.isAuthenticated()){
//
//            SecurityContextHolder.getContext().setAuthentication(auth);
//        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
      return (request.getServletPath().equals("/config/api/v1/login") || request.getServletPath().equals("/config/api/v1/register") || request.getServletPath().equals("/config/api/v1/logout"));
    }
}
