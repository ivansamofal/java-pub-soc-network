package hello.filters;

import hello.services.UserService;
import hello.utils.auth.JwtUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private JwtUtils jwtUtils;
    private UserService userService;

    public JwtRequestFilter(JwtUtils jwtUtils, UserService userService) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        System.out.println("TOKEN: " + authorizationHeader);
        String username = "";

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwt = authorizationHeader.substring(7);

            System.out.println(jwt);
            username = jwtUtils.extractUsername(jwt);

            System.out.println(username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userService.loadUserByUsername(username);
                System.out.println(userDetails.getUsername());
                System.out.println(Arrays.deepToString(userDetails.getAuthorities().toArray()));
                if (jwtUtils.validateToken(jwt, userDetails)) {
                    System.out.println("TOKEN IS VALID!!");
                    UsernamePasswordAuthenticationToken uPassToken =
                            new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
                    uPassToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(uPassToken);
//                    if (SecurityContextHolder.getContext().getAuthentication() != null) {
//                        SecurityContextHolder.getContext().getAuthentication().
//                    } else {
//                        System.out.println("BBBBBBBBBBBB");
//                    }
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
