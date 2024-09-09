package br.com.application.authenticator.controller;

import br.com.application.authenticator.dto.AuthenticationData;
import br.com.application.authenticator.dto.JwtToken;
import br.com.application.authenticator.entity.User;
import br.com.application.authenticator.repository.UserRepository;
import br.com.application.authenticator.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticator")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationData authenticationData) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(authenticationData.login(), authenticationData.password());
        var authentication = authenticationManager.authenticate(authenticationToken);
        JwtToken jwtToken = new JwtToken(tokenService.generateJwt((User) authentication.getPrincipal()));
        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping("/createUser")
    public User createUser(@RequestBody AuthenticationData authenticationData) {
        return userRepository.save(new User(authenticationData.login(), authenticationData.password()));
    }
}
