package duksung.backend.hororok.ugeubi.common.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtProvider {
    private static final String TOKEN_TYPE = "Bearer";
    private static final Long ACCESS_TOKEN_VALID_TIME = 1000L * 60L * 60L * 24L;//하루
    //private static final Long REFRESH_TOKEN_VALID_TIME = 1000L * 60L * 60L * 24L * 30L;//한 달

    @Value("${secret.key}")
    private String secretKey;

    public String createAccessToken(String subject){
        return createJWT(subject, ACCESS_TOKEN_VALID_TIME);
    }

    public String getUserIdFromJwtHeader(String tokenHeader){
        Claims claims = getPayLoad(subStringTokenType(tokenHeader));
        return claims.getSubject();
    }

    public boolean isTokenValid(String tokenHeader){

        if(tokenHeader == null || isNotStartWithBearer(tokenHeader)
                || subStringTokenType(tokenHeader).isEmpty()){
            return false;
        }

        try{
            String token = subStringTokenType(tokenHeader);
            getPayLoad(token);
            return true;
        }catch (Exception e) {
            //1) ExpiredJwtException, 2) UnsupportedJwtException, 3) MalformedJwtException, 4)SignatureException, 5)IllegalArgumentException
            return false;
        }
    }

    private Claims getPayLoad(String token) {
        return Jwts.parser()
                .setSigningKey(generateKey(secretKey))
                .parseClaimsJws(token)
                .getBody();
    }

    private String createJWT(String subject, Long validTime){
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + validTime);

        return Jwts.builder()
                .setHeaderParam("type","JWT")
                .setIssuer("Ugeubi-Api-Server")
                .setSubject(subject)
                .setExpiration(expireDate)
                .setIssuedAt(now)
                .signWith(generateKey(secretKey))
                .compact();
    }

    private SecretKey generateKey(String key) {
        byte[] byteKey = key.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(byteKey);
    }

    private boolean isNotStartWithBearer(String tokenHeader){
        return !tokenHeader.startsWith(TOKEN_TYPE);
    }
    private String subStringTokenType(String tokenHeader){
        return tokenHeader.substring(TOKEN_TYPE.length());
    }
}