package com.example.TCSwim.controller;

import com.example.TCSwim.dto.UsersDto;
import com.example.TCSwim.infra.security.TokenService;
import com.example.TCSwim.responses.AuthenticationResponse;
import com.example.TCSwim.model.Users;
import com.example.TCSwim.repository.UserRepository;
import com.example.TCSwim.responses.ResponseData;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.TCSwim.dto.AuthenticationDto;

@RestController()
public class AuthenticationControler {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDto authenticationDto ) {
        try {
            var userNamePass = new UsernamePasswordAuthenticationToken(authenticationDto.userName(), authenticationDto.password());
            var auth = authenticationManager.authenticate(userNamePass);
            var token = tokenService.generateToken((Users) auth.getPrincipal());
            if (auth.isAuthenticated()) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new AuthenticationResponse(token));
            } else {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(new ResponseData("Falha na autenticação. Verifique suas credenciais."));
            }
        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseData("Falha na autenticação. Verifique suas credenciais."));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody @Valid UsersDto usersDto){
        var userModel = new Users();
        String encryptedPass = new BCryptPasswordEncoder().encode(usersDto.password());
        BeanUtils.copyProperties(usersDto, userModel);
        userModel.setPassword(encryptedPass);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(userModel));
    }


}
