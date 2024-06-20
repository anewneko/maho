package bot.discord.maho.security.Filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import bot.discord.maho.security.Component.JwtTokenUtil;
import bot.discord.maho.security.Component.SecurityTool;
import bot.discord.maho.security.Component.SecurityUserService;
import bot.discord.maho.security.Model.AuthHeader;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
	private final JwtTokenUtil jwtService;
    private final SecurityUserService userService;
    private final SecurityTool securityTool;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		var jwt = AuthHeader.of(request.getHeader("Authorization")).getJwt();
		var owner = jwt.isEmpty() ? "" : jwtService.getOwner(jwt);
		var user = userService.loadUserByUsername(owner);
		if (user != null) 
			securityTool.verift(jwt, user);
		
		filterChain.doFilter(request, response);
	}
}
