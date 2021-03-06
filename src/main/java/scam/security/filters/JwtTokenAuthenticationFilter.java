package scam.security.filters;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import scam.security.authentication.JwtAuthentication;
import scam.security.jwt.JwtTokenUtil;
import scam.security.wrappers.WrappedHttpServletRequest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public JwtTokenAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("JWT FILTER CALLED");

        Cookie[] cookies = httpServletRequest.getCookies();

        Cookie jwtCookie;

        try {
             jwtCookie = Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals("jwt-token"))
                    .findFirst().orElseThrow(() -> new BadCredentialsException("BAD CREDENTIALS"));
        }catch (NullPointerException e){
            throw new BadCredentialsException("BAD CREDENTIALS");
        }

        if(StringUtils.isBlank(jwtCookie.getValue())) {
            throw new BadCredentialsException("BAD CREDENTIALS");
        }

        Authentication auth = authenticationManager.authenticate(new JwtAuthentication(jwtCookie.getValue(), null));

        if (auth.isAuthenticated()) {

            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        //TODO disable not logged in user admin : GET ALL USERS
        String method = request.getMethod();

        if (method.equals("GET")
                && request.getServletPath().matches("/config/api/v1/posts.*")) {
            return true;
        }

        if(method.equals("PATCH") && request.getServletPath().matches("/config/api/v1/posts/.*/incrementView")){
            return true;
        }

        if(method.equals("GET") || method.equals("POST") && request.getServletPath().matches("/config/api/v1/users.*")){
            return true;
        }

        if(request.getServletPath().matches("/config/api/v1/comments.*")){
            return true;
        }


        return (request.getServletPath().equals("/config/api/v1/login") || request.getServletPath().equals("/config/api/v1/logout"));
    }
}
