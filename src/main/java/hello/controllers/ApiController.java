package hello.controllers;

import hello.dtos.AuthRequestDto;
import hello.dtos.AuthResponseDto;
import hello.services.AppUserDetailsService;
import hello.services.UserService;
import hello.utils.auth.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private AuthenticationManager authenticationManager;
    private AppUserDetailsService userService;
    private JwtUtils jwtUtils;

    public ApiController(AuthenticationManager authenticationManager, AppUserDetailsService userService, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponseDto> auth(@RequestBody AuthRequestDto requestDto) {
        System.out.println("TRY TO AUTHENTICATE!!!" + requestDto.getUsername());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword())
        );

        UserDetails userDetails = userService.loadUserByUsername(requestDto.getUsername());

        String jwt = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponseDto(jwt));
    }
}
