package bot.discord.maho.core.Util;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import bot.discord.maho.core.Model.User4Jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtTokenUtil {
	private final Key secretKey;
	@Autowired private HttpServletResponse response;
	
	private JwtTokenUtil(Environment environment) throws NoSuchAlgorithmException {
		String token = environment.getProperty("discord.bot.token");
		secretKey = new SecretKeySpec(token.getBytes(), "HmacSHA256");
	}
	
	public String refreshToken() {
		var token = generateToken(User4Jwt.fake());
		response.setHeader("Authorization", "Bearer " + token);
		return token;
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
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
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
