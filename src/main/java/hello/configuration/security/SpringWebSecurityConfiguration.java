package hello.configuration.security;

import hello.filters.JwtRequestFilter;
import hello.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private MyAuthenticationProvider authProvider;
//    private DaoAuthenticationProvider authProvider;

//    public SpringWebSecurityConfiguration(DaoAuthenticationProvider authProvider) {
//        this.authProvider = authProvider;
//    }

        @Autowired
    private UserService userService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authProvider);
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {//todo для http auth
//        http.authorizeRequests().anyRequest().authenticated();
//                .and().httpBasic();
//    }

//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth)
//            throws Exception {
//
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .passwordEncoder(passwordEncoder())
//                .usersByUsernameQuery("select username, password from users where username='user'") //SQL query
//                .authoritiesByUsernameQuery("SELECT u.username, r.name FROM users u JOIN users_roles ur on u.id = ur.users_id JOIN roles r on ur.roles_id = r.id WHERE username = 'user'"); //SQL query
//    }

    @Bean
    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder().username("user5").password("password").roles("USER").build());

//        return manager;
        return userService;
    }

//    @Bean
//    public DaoAuthenticationProvider myAuthProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
////        provider.setPasswordEncoder(passwordEncoder());
////        provider.setUserDetailsService(securityService);
//        return provider;
//    }

    @Override
    public void configure(AuthenticationManagerBuilder builder)
            throws Exception {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userService);
//        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
//        builder.authenticationProvider(authProvider);

        builder.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/auth").permitAll()
                .antMatchers("/**").hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                .formLogin().defaultSuccessUrl("/hello/test");

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/", "/public/**").permitAll()
//                .antMatchers("/users/**").hasAuthority("USER")
//                .anyRequest().fullyAuthenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .failureUrl("/login?error")
//                .usernameParameter("email")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .deleteCookies("remember-me")
//                .logoutSuccessUrl("/")
////                .defaultSuccessUrl("/hello/test")
//                .permitAll()
//                .and()
//                .rememberMe();
//    }


//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//     auth.jdbcAuthentication()
//     .dataSource(dataSource)
////     .withDefaultSchema()
//     .usersByUsernameQuery(
//             "select username, password, enabled from users where username= ?")
//     .authoritiesByUsernameQuery(
//             "SELECT username, authority from authorities where username = ?")
//     .withUser(User.withUsername("user2")
//     .password(passwordEncoder().encode("some"))
//     .roles("USER"));
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
