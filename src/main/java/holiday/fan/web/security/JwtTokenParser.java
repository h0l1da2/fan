package holiday.fan.web.security;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtTokenParser {

    private final JwtParser jwtParser;

    public JwtTokenParser(@Value("${custom.jwt.secutiry-key}") String secretKey) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    }

//    public Neo4jProperties.Authentication extractAuthentication(String accessToken) {
//
//    }
//
//    public boolean validateAccessToken(String token) {
//
//    }
//
//    private boolean validateToken(String token, boolean isRefreshToken) {
//
//    }
}
