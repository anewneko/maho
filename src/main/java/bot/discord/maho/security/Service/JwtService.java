package bot.discord.maho.security.Service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtService {
	private final Key secretKey;
	
	private JwtService(Environment environment) throws NoSuchAlgorithmException {
		String token = environment.getProperty("discord.bot.token");
		secretKey = new SecretKeySpec(token.getBytes(), "HmacSHA256");
	}

    public String getOwner(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDate(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
    	var parser = Jwts.parserBuilder()
 			   			 .setSigningKey(secretKey)
 			   			 .build() ;
    	
    	return parser.parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDate(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
         return doGenerateToken(new HashMap<>(), userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject)
			                 .setIssuedAt(new Date(System.currentTimeMillis()))
			                 .setExpiration(new Date(System.currentTimeMillis() + 604800000))
			                 .signWith(secretKey,SignatureAlgorithm.HS512)
			                 .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getOwner(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
