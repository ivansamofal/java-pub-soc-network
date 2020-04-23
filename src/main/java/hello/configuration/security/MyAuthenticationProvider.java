package hello.configuration.security;

import hello.entities.Role;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@Service
//public class MyAuthenticationProvider implements AuthenticationProvider, Serializable {
//
//    @Override
//    public boolean supports(Class<? extends Object> authentication) {
//        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
////         authentication.setAuthenticated(true);
//
//        //todo check
//
//        Role _role = new Role();
//        _role.setId(1L);
//        _role.setName("ROLE_USER");
//        List<GrantedAuthority> _roles = new ArrayList<>();
//        _roles.add(_role);
//        String password = authentication.getCredentials().toString();
//        Authentication authentication2
//                = new UsernamePasswordAuthenticationToken(authentication.getName(), password, _roles);
//         return authentication2;
//    }
//}