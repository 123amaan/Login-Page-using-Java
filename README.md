This project demonstrates how to implement authentication and authorization in a Spring Boot application using Java. The application includes a login page where users can authenticate themselves, and upon successful login, they are granted access to specific parts of the application based on their roles (authorization).

Below is a video demo to showcase the project : 


Features
1. User Authentication: Validate user credentials against a database or an in-memory store.
2. Role-Based Authorization: Grant or deny access to resources based on user roles.
3. Secure Password Storage: Use BCrypt to securely store passwords.
4. Custom Login Page: Create a custom login page for user authentication.
5. Session Management: Handle user sessions, including session timeout and logout.
6. Remember Me Functionality: Option for persistent login sessions.
7. Exception Handling: Proper handling of authentication and authorization exceptions.

Prerequisites
1. Java 8+
2. Maven or Gradle
3. Spring Boot 2.x
4. MySQL (optional, for database authentication)
5. Thymeleaf

Implementation Details
1. Security Configuration
The SecurityConfig class is where the security configurations are defined. Here, you can set up user authentication, define role-based access control, and configure the custom login page.

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/login", "/register").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .permitAll()
            .and()
            .logout()
                .permitAll()
            .and()
            .exceptionHandling()
                .accessDeniedPage("/403");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

2. UserDetailsService Implementation
The CustomUserDetailsService class loads user-specific data. It retrieves the user information from a database or in-memory store.

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
            .collect(Collectors.toList());
    }

3. Custom Login Page
Create a custom login page using Thymeleaf or any other templating engine. The login.html file will be placed in the templates directory.

        <!DOCTYPE html>
        <html xmlns:th="http://www.thymeleaf.org">
        <head>
            <title>Login</title>
        </head>
        <body>
        <h1>Login</h1>
            <form th:action="@{/login}" method="post">
                <div>
                    <label for="username">Username</label>
                        <input type="text" id="username" name="username" />
                </div>
        <div>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" />
        </div>
        <div>
            <input type="checkbox" name="remember-me" /> Remember me
        </div>
        <div>
            <button type="submit">Login</button>
        </div>
        </form>
            <div th:if="${param.error}">
                Invalid username or password.
            </div>
       <div th:if="${param.logout}">
            You have been logged out.
        </div>
        </body>
       </html>

4. Testing
You can write unit and integration tests to verify the authentication and authorization functionalities. Use the @SpringBootTest annotation to load the full application context and test the security configurations.


       @SpringBootTest   
       public class AuthenticationTests {
    
       @Test
       public void contextLoads() {
       // Add test cases for authentication and authorization
       }

