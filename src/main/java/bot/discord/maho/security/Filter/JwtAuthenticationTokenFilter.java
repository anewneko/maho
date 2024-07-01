package bot.discord.maho.security.Filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import bot.discord.maho.security.Component.SecurityTool;
import bot.discord.maho.security.Model.AuthHeader;
import bot.discord.maho.security.Service.JwtService;
import bot.discord.maho.security.Service.SecurityUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
	private final JwtService jwtService;
    private final SecurityUserService userService;
    private final SecurityTool securityTool;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			System.out.println("request_url: " + request.getRequestURL());
			
			var jwt = AuthHeader.of(request.getHeader("Authorization")).getJwt();
			var owner = jwt.isEmpty() ? "" : jwtService.getOwner(jwt);
			var user = userService.loadUserByUsername(owner);
			if (user != null) 
				securityTool.verift(jwt, user);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		} finally {
			filterChain.doFilter(request, response);
		}
		
	}
}
