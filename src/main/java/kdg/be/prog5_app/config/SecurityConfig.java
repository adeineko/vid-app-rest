package kdg.be.prog5_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import static org.springframework.security.web.util.matcher.RegexRequestMatcher.regexMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        http.authorizeHttpRequests(
                        auths -> auths
                                .requestMatchers(regexMatcher("^/(cdhannel\\?.+|channels|videos|comments\\?.+|search-channels)"))
                                .permitAll()
                                .requestMatchers(
                                        antMatcher(HttpMethod.GET, "/js/**"),
                                        antMatcher(HttpMethod.GET, "/webjars/**"),
                                        antMatcher(HttpMethod.GET, "/css/**"),
                                        regexMatcher(HttpMethod.GET, "\\.ico$"))
                                .permitAll()
                                .requestMatchers(
                                        antMatcher(HttpMethod.GET, "/api/**"),
                                        antMatcher(HttpMethod.POST, "/api/**"),
                                        antMatcher(HttpMethod.GET, "/js/login.js"),
                                        antMatcher(HttpMethod.POST, "/js/signup.js"),
                                        antMatcher(HttpMethod.GET, "/js/signup.js"),
                                        antMatcher(HttpMethod.GET, "/signup/**")
                                )
                                .permitAll()
                                .requestMatchers(antMatcher(HttpMethod.GET, "/"))
                                .permitAll()
                                .requestMatchers(antMatcher(HttpMethod.GET, "/add"))
                                .hasRole("ADMIN")
                                .anyRequest()
                                .authenticated()
                )
                .formLogin(formLogin ->
                        formLogin.loginPage("/login")
                                .permitAll())
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(
                                (request, response, exception) -> {
                                    if (request.getRequestURI().startsWith("/api")) {
                                        response.setStatus(HttpStatus.UNAUTHORIZED.value());
                                    } else if (request.getRequestURI().startsWith("/error")) {
                                        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                                    } else {
                                        response.sendRedirect(request.getContextPath() + "/login");
                                    }
                                })
                );
//        @formater:on
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
