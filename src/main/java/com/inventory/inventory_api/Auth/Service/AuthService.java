package com.inventory.inventory_api.Auth.Service;

import com.inventory.inventory_api.Auth.Dto.AuthResponse;
import com.inventory.inventory_api.Auth.Dto.LoginRequest;
import com.inventory.inventory_api.Auth.Dto.SignUpRequest;
import com.inventory.inventory_api.Entity.Role;
import com.inventory.inventory_api.Entity.User;
import com.inventory.inventory_api.Repository.RoleRepository;
import com.inventory.inventory_api.Repository.UserRepository;
import com.inventory.inventory_api.Security.Jwt.JwtUtil;
import com.inventory.inventory_api.Security.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponse signUp(SignUpRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("The username already exists");
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("The email is already registered");
        }

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);
        user.setStatus(true);

        userRepository.save(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtUtil.generateToken(userDetails, role.getName());

        return new AuthResponse(token, user.getUsername(), role.getName(), "User successfully registered");
    }

    public AuthResponse signIn(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            User user = customUserDetailsService.getUserByUsername(request.getUsername());

            if (!user.isStatus()) {
                throw new RuntimeException("Inactive user");
            }

            String token = jwtUtil.generateToken(userDetails, user.getRole().getName());

            return new AuthResponse(token, user.getUsername(), user.getRole().getName(), "Login exitoso");

        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid credentials");
        } catch (Exception e) {
            throw new RuntimeException("Error logging in: " + e.getMessage());
        }
    }
}