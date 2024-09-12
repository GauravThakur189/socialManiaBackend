package com.springmaniya.springmaniya.controller;


import com.springmaniya.springmaniya.config.JwtProvider;
import com.springmaniya.springmaniya.exception.UserException;
import com.springmaniya.springmaniya.model.User;
import com.springmaniya.springmaniya.model.Varification;
import com.springmaniya.springmaniya.repository.UserRepository;
import com.springmaniya.springmaniya.response.AuthResponse;
import com.springmaniya.springmaniya.service.CustomUserDetailsServiceImplementation;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthController {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtProvider jwtProvider;
    
    @Autowired
    private CustomUserDetailsServiceImplementation customUserDetailsServiceImplementation;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {

        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();
        String birthDate = user.getBirthDate();

        User isEmailExist = userRepository.findByEmail(email);

        if(isEmailExist !=  null){
            throw new UserException("email already used");
        }

        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setFullName(fullName);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setBirthDate(birthDate);
        createdUser.setVarification(new Varification());

        User savedUser = userRepository.save(createdUser);

        // creating token
        Authentication authentication = new UsernamePasswordAuthenticationToken(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse(token,true);

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }

//    @PostMapping("/signin")
//    public  ResponseEntity<AuthResponse> signin(@RequestBody User user){
//        String username = user.getEmail();
//        String password = user.getPassword();
//
//        Authentication authentication = authenticate(username,password);
//
//        String token = jwtProvider.generateToken(authentication);
//
//        AuthResponse authResponse = new AuthResponse(token,true);
//
//        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.ACCEPTED);
//
//
//    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody User user){
        System.out.println("Received signin request with: " + user);
        try {
            String username = user.getEmail();
            String password = user.getPassword();

            Authentication authentication = authenticate(username, password);

            String token = jwtProvider.generateToken(authentication);

            AuthResponse authResponse = new AuthResponse(token, true);

            return new ResponseEntity<>(authResponse, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println("Error during signin: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


    private Authentication authenticate(String username, String password) {

        UserDetails userDetails = customUserDetailsServiceImplementation.loadUserByUsername(username);

        if(userDetails == null){
            throw  new BadCredentialsException("Invalid UserName");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw  new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

}
