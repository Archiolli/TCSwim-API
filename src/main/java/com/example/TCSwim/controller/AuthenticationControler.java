package com.example.TCSwim.controller;

import com.example.TCSwim.dto.AthleteDto;
import com.example.TCSwim.dto.UsersDto;
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
    @PostMapping("/login")
        public ResponseEntity<Users> login(@RequestBody @Valid AuthenticationDto authenticationDto ) {
        var userNamePass = new UsernamePasswordAuthenticationToken(authenticationDto.userName(), authenticationDto.password());
        var auth = authenticationManager.authenticate(userNamePass);

        return ResponseEntity.ok().build();
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
