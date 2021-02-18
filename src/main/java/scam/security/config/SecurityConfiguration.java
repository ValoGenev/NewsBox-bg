package scam.security.config;

import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.apache.tomcat.util.http.Rfc6265CookieProcessor;
import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import scam.repository.IUserRepository;
import scam.security.filters.CustomLogoutFilter;
import scam.security.filters.JwtTokenAuthenticationFilter;
import scam.security.filters.UnauthorizedExceptionFilter;
import scam.security.filters.UsernamePasswordAuthenticationFilter;
import scam.security.jwt.JwtTokenUtil;
import scam.security.providers.JwtTokenAuthProvider;
import scam.security.providers.UsernamePasswordAuthProvider;
import scam.security.userdetails.UserDetailsServiceImpl;

import static scam.model.UserRole.ADMIN;
import static scam.model.UserRole.USER;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public UnauthorizedExceptionFilter unauthorizedExceptionFilter() {
        return new UnauthorizedExceptionFilter();
    }

    @Bean
    JwtTokenAuthProvider jwtTokenAuthProvider(JwtTokenUtil jwtTokenUtil) {
        return new JwtTokenAuthProvider(userDetailsService, jwtTokenUtil);
    }

    @Autowired
    IUserRepository userRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    UsernamePasswordAuthProvider usernamePasswordAuthProvider() {
        return new UsernamePasswordAuthProvider(userDetailsService, passwordEncoder());
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).and()
                .authenticationProvider(usernamePasswordAuthProvider())
                .authenticationProvider(jwtTokenAuthProvider(jwtTokenUtil()));
    }

    @Bean
    CustomLogoutFilter customLogoutFilter() {
        return new CustomLogoutFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index", "/css/*", "/js/*").permitAll()
                .antMatchers("/config/api/v1/login", "/config/api/v1/logout").permitAll()

                .antMatchers(HttpMethod.POST,"/config/api/v1/posts").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.PUT,"/config/api/v1/posts/**").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.DELETE,"/config/api/v1/posts/**").hasRole(ADMIN.name())

                .antMatchers(HttpMethod.PUT,"/config/api/v1/users/**").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.DELETE,"/config/api/v1/users/**").hasRole(ADMIN.name())

                .antMatchers(HttpMethod.POST,"/config/api/v1/thumbnails").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.DELETE,"/config/api/v1/thumbnails/**").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.PUT,"/config/api/v1/thumbnails/**").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.GET,"/config/api/v1/thumbnails").hasRole(ADMIN.name())

                .antMatchers(HttpMethod.POST,"/config/api/v1/pictures").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.DELETE,"/config/api/v1/pictures/**").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.PUT,"/config/api/v1/pictures/**").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.GET,"/config/api/v1/pictures").hasRole(ADMIN.name())
                .antMatchers("**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.addFilterAt(customLogoutFilter(),LogoutFilter.class);
        http.addFilterAt(usernamePasswordAuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class);
        http.addFilterBefore(jwtTokenAuthenticationFilter(authenticationManager(),jwtTokenUtil()), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(unauthorizedExceptionFilter(), CustomLogoutFilter.class);
    }

//    @Bean
////    public TomcatContextCustomizer sameSiteCookiesConfig() {
////        return context -> {
////            final Rfc6265CookieProcessor cookieProcessor = new Rfc6265CookieProcessor();
////            cookieProcessor.setSameSiteCookies(SameSiteCookies.NONE.getValue());
////            context.setCookieProcessor(cookieProcessor);
////        };
////    }

    @Bean
    @Profile("prod")
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> cookieProcessorCustomizer() {
        return (factory) -> factory.addContextCustomizers((context) -> {
            LegacyCookieProcessor legacyCookieProcessor = new LegacyCookieProcessor();
            legacyCookieProcessor.setSameSiteCookies(SameSiteCookies.NONE.getValue());
            context.setCookieProcessor(legacyCookieProcessor);
        });
    }


    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        return new UsernamePasswordAuthenticationFilter(authenticationManager);
    }

    @Bean
    public JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        return new JwtTokenAuthenticationFilter(authenticationManager, jwtTokenUtil);
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(5);
    }

}
