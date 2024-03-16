package com.demo.backendtestjava.controller;

import com.demo.backendtestjava.dto.AuthenticationDTO;
import com.demo.backendtestjava.dto.LoginResponseDTO;
import com.demo.backendtestjava.dto.RegisterDTO;
import com.demo.backendtestjava.repository.UserRepository;
import com.demo.backendtestjava.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.demo.backendtestjava.entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }



    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO dto) {

        if(repository.findByLogin(dto.login()) != null){
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        User user = new User(dto.name(), dto.login(), encryptedPassword, dto.role());

        this.repository.save(user);

        return ResponseEntity.ok().build();
    }


}
