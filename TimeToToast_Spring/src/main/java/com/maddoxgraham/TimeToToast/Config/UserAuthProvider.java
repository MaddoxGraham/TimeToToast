package com.maddoxgraham.TimeToToast.Config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.maddoxgraham.TimeToToast.DTOs.UserDto;
import com.maddoxgraham.TimeToToast.Services.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.*;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {

    private final UserService userService;

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public Map<String, String> createTokens(Object userDto) {
        String accessToken = createAccessToken(userDto);
        String refreshToken = createRefreshToken(userDto);

        if(userDto instanceof UserDto) {
            UserDto user = (UserDto) userDto;
            user.setRefreshToken(refreshToken);
            userService.save(user);
        }

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }

    private String createAccessToken(Object userDto) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3_600_000);
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        String userType = ((UserDto) userDto).getRole();
        String first_name = ((UserDto) userDto).getFirstName();
        String last_name = ((UserDto) userDto).getLastName();
        String login = ((UserDto) userDto).getLogin();

        return JWT.create()
                .withIssuer(userType)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("first_name", first_name)
                .withClaim("last_name", last_name)
                .withClaim("login", login)
                .sign(Algorithm.HMAC256(secretKey));
    }

    private String createRefreshToken(Object userDto) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 86400000); //1 jour
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        String userType = ((UserDto) userDto).getRole();

        return JWT.create()
                .withIssuer(userType)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);
    }


    public String refreshAccessToken(String refreshToken) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decoded = verifier.verify(refreshToken);
        String userType = decoded.getIssuer();

        Object userDto;

        UserDto user = userService.findByRefreshToken(refreshToken);
        userDto = user;

        return createAccessToken(userDto);
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT decoded = verifier.verify(token);
        String userType = decoded.getIssuer();
        String login = decoded.getClaim("login").asString();

        Object userDto;
        userDto = userService.findByLogin(login);

        return new UsernamePasswordAuthenticationToken(userDto, null, Collections.emptyList());
    }


    public Authentication validateTokenStrongly(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT decoded = verifier.verify(token);
        String userType = decoded.getIssuer();
        String login = decoded.getClaim("login").asString();

        Object userDto;
        userDto = userService.findByLogin(login);

        return new UsernamePasswordAuthenticationToken(userDto, null, Collections.emptyList());
    }
}
