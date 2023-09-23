package com.maddoxgraham.TimeToToast.Config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.maddoxgraham.TimeToToast.DTOs.PersonDto;
import com.maddoxgraham.TimeToToast.Models.Event;
import com.maddoxgraham.TimeToToast.Models.Person;
import com.maddoxgraham.TimeToToast.Services.PersonService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {

    private final PersonService personService;

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public Map<String, String> createTokens(Object userDto) {
        String accessToken = createAccessToken(userDto);
        String refreshToken = createRefreshToken(userDto);

        if(userDto instanceof PersonDto) {
            PersonDto user = (PersonDto) userDto;
            user.setRefreshToken(refreshToken);
            personService.save(user);
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

        String userType = ((PersonDto) userDto).getRole();
        String first_name = ((PersonDto) userDto).getFirstName();
        String last_name = ((PersonDto) userDto).getLastName();
        String login = ((PersonDto) userDto).getLogin();

        return JWT.create()
                .withIssuer(userType)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("first_name", first_name)
                .withClaim("last_name", last_name)
                .withClaim("login", login)
                .sign(Algorithm.HMAC256(secretKey));
    }

    public String createGuestToken(Person guest, Event event) {
        Date now = new Date();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.of(event.getEventDate(), localTime);
        Date dateEvent = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        long eventPlusSevenDays = dateEvent.getTime();
        eventPlusSevenDays += 7L * 24 * 60 * 60 * 1000;
        Date validity = new Date(eventPlusSevenDays);
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        String userType = guest.getRole().toString();
        String email = guest.getEmail();
        Long idEvent = event.getIdEvent();

        return JWT.create()
                .withIssuer(userType)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("email", email)
                .withClaim("idEvent", idEvent)
                .sign(Algorithm.HMAC256(secretKey));

    }


    private String createRefreshToken(Object userDto) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 86400000); //1 jour
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        String userType = ((PersonDto) userDto).getRole();

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

        userDto = personService.findByRefreshToken(refreshToken);

        return createAccessToken(userDto);
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT decoded = verifier.verify(token);
        String userType = decoded.getIssuer();
        String login = decoded.getClaim("login").asString();
        String email = decoded.getClaim("email").asString();

        if(userType.equals("USER")){
            Object personDto;
            personDto = personService.findByLogin(login);

            return new UsernamePasswordAuthenticationToken(personDto, null, Collections.emptyList());
        }
        if(userType.equals("GUEST")){

            Object personDto;
            personDto = personService.findByEmail(email);

            return new UsernamePasswordAuthenticationToken(personDto, null, Collections.emptyList());
        }
        return  null;
    }


    public Authentication validateTokenStrongly(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT decoded = verifier.verify(token);
        String userType = decoded.getIssuer();
        String login = decoded.getClaim("login").asString();
        String email = decoded.getClaim("email").asString();

        if(userType.equals("USER")){
            Object userDto;
            userDto = personService.findByLogin(login);

            return new UsernamePasswordAuthenticationToken(userDto, null, Collections.emptyList());
        }
        if(userType.equals("GUEST")){

            Object guestDto;
            guestDto = personService.findByEmail(email);
            System.out.println(guestDto);

            return new UsernamePasswordAuthenticationToken(guestDto, null, Collections.emptyList());
        }
        return  null;

    }

    public String verifyGuest(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decoded = verifier.verify(token);

        return decoded.getClaim("email").asString();
    }
}
