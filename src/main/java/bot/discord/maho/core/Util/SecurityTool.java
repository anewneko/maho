package bot.discord.maho.core.Util;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityTool {
	private final JwtTokenUtil jwtService;
	@Autowired private HttpServletRequest request;
	
	public void verift(String jwt , UserDetails user ) throws AuthenticationException {
		if (jwtService.validateToken(jwt, user)){
			permit(user);
			jwtService.refreshToken();
		}
		else 
			throw new AuthenticationException();
	}
	
	
	public void permit(UserDetails user) {
		var authToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authToken);
	}

}
