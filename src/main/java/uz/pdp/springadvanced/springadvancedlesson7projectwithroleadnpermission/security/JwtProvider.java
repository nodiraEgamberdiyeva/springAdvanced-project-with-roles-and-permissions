package uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.Role;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.enums.Permission;

import java.util.Date;
import java.util.List;

@Component
public class JwtProvider {
    private static final long expirationDate = 36000000;
    private static final String key = "keykeykey";


    public String generateJwt(String username, Role roles){
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, key)
                .setSubject(username)
                .claim("roles", roles.getName())
                .setExpiration(new Date(System.currentTimeMillis() + expirationDate))
                .setIssuedAt(new Date())
                .compact();
        System.out.println(token);
        return token;
    }

    public String getUsernameByToken(String token){
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
