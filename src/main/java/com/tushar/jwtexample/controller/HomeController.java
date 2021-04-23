package com.tushar.jwtexample.controller;

import com.tushar.jwtexample.exceptionhandling.InputFieldEmptyException;
import com.tushar.jwtexample.exceptionhandling.InvalidUsernamePasswordException;
import com.tushar.jwtexample.util.JwtRequest;
import com.tushar.jwtexample.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/home")
    public String home() {
        return "Hello from HOME CONTROLLER";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        if (jwtRequest.getUsername().length() == 0 || jwtRequest.getUsername() == null || jwtRequest.getPassword().length() == 0 || jwtRequest.getPassword() == null)
            throw new InputFieldEmptyException();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(), jwtRequest.getPassword()
                    ));
        } catch (Exception e) {
            throw new InvalidUsernamePasswordException();
        }
        return jwtUtil.generateToken(jwtRequest.getUsername());
    }
}
