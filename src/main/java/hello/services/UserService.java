package hello.services;

import hello.entities.User;
import hello.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    final private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        System.out.println("!!!!!!!!!FOUND USERNAME: " + user.getUsername());
        System.out.println("!!!!!!!!!FOUND PASSWORD: " + user.getPassword());

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

//        final org.springframework.security.core.userdetails.User.UserBuilder userBuilder =
//                org.springframework.security.core.userdetails.User.builder().passwordEncoder(encoder::encode);

//        try {
//            SecurityContext context = SecurityContextHolder.getContext();
//            Authentication authentication = context.getAuthentication();
//            System.out.println("PASSWORD FROM USER: " + authentication.getPrincipal().toString());
//            System.out.println("PASSWORD FROM USER: " + authentication.getDetails().toString());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList("ROLE_USER")
        );

//        return userBuilder
//                .username(user.getUsername())
//                .password("password")
//                .roles("USER")
//                .build();

//        return user;
    }

}
