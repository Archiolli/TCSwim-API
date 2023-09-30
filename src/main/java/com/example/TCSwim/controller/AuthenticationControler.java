package com.example.TCSwim.controller;

import com.example.TCSwim.dto.AthleteDto;
import com.example.TCSwim.dto.UsersDto;
import com.example.TCSwim.infra.security.TokenService;
import com.example.TCSwim.model.Athlete;
import com.example.TCSwim.model.Users;
import com.example.TCSwim.repository.AthleteRepository;
import com.example.TCSwim.repository.UserRepository;
import jakarta.validation.Valid;
import org.apache.catalina.User;
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
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<String> login(@RequestBody @Valid AuthenticationDto authenticationDto ) {
        try {
            var userNamePass = new UsernamePasswordAuthenticationToken(authenticationDto.userName(), authenticationDto.password());
            var auth = authenticationManager.authenticate(userNamePass);
            var token = tokenService.generateToken((Users)auth.getPrincipal());
            String respLogged = "Usuário autenticado! \nToken: " + token;
            // Verifica se a autenticação foi feita com sucesso
            if (auth.isAuthenticated()) {
                return ResponseEntity.status(HttpStatus.OK).body(respLogged);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("A autenticação falhou!");
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorretos!");
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
