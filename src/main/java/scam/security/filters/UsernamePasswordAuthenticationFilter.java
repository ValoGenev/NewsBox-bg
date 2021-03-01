package scam.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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

        System.out.println("USERNAME PASSWORD AUTHENTICATION FILTER");

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

//            if (cookies != null && cookies.length > 0) {
//                List<Cookie> cookieList = Arrays.asList(cookies);
//                Cookie sessionCookie = cookieList
//                        .stream()
//                        .filter(cookie -> SESSION_COOKIE_NAME.equals(cookie.getName())).findFirst().orElse(null);
//                if (sessionCookie != null) {
//                    resp.setHeader(HttpHeaders.SET_COOKIE, sessionCookie.getName() + "=" + sessionCookie.getValue() + SAME_SITE_ATTRIBUTE_VALUES);
//                }
//            }

           httpServletResponse.setHeader(HttpHeaders.SET_COOKIE,cookie.getName()+"="+cookie.getValue()+";HttpOnly;Secure;SameSite=None");

           //httpServletResponse.addCookie(cookie);

        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/config/api/v1/login");
    }
}
