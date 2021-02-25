package scam.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import scam.exception.ErrorMessage;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnauthorizedExceptionFilter extends OncePerRequestFilter {

    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("UNAUTHORIZED FILTER CALLED");

        try{
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
        catch (BadCredentialsException | UsernameNotFoundException e) {

            handlerExceptionResolver.resolveException(httpServletRequest, httpServletResponse, null, e);

            System.out.println(e.getMessage());
            httpServletResponse.resetBuffer();
            httpServletResponse.setStatus(401);
            httpServletResponse.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            httpServletResponse.getOutputStream();
            httpServletResponse.getOutputStream().print(new ObjectMapper().writeValueAsString(new ErrorMessage("Wrong username or password",401)));
            httpServletResponse.flushBuffer();

            //filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
