package scam.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

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
//        try{
//            filterChain.doFilter(httpServletRequest,httpServletResponse);
//        }catch (BadCredentialsException e){
//
//            handlerExceptionResolver.resolveException(httpServletRequest,httpServletResponse,null,e);
//
//            httpServletResponse.resetBuffer();
//            httpServletResponse.setStatus(403);
//            httpServletResponse.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
//            httpServletResponse.getOutputStream();
//            //httpServletResponse.getOutputStream().print(new ObjectMapper().writeValueAsString(errorMessage));
//            httpServletResponse.flushBuffer();

            filterChain.doFilter(httpServletRequest,httpServletResponse);
        //}
    }
}
