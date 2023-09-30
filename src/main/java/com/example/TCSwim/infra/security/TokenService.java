package com.example.TCSwim.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.TCSwim.model.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    public String generateToken(Users users){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("TCSWIM-API")//quem criou o token
                    .withSubject(users.getUsername())//usuario que ta recebendo token (salva o nome do user no token)
                    .withExpiresAt(genExpirationDate())//tempo de expiração
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw new RuntimeException("Erro na geração do token!", e);
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now()//pega o tempo exato(agora)
                .plusHours(2)//adiciona duas horas (tempo de expiração do token)
                .toInstant(ZoneOffset.of("-03:00"));//trasnforma no tipo Intant do Java e coloca no timezone do brasil (-3h)
    }

    public String validadeToken(String token){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("TCSWIM-API")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTCreationException e){
            return "";
        }
    }


}
