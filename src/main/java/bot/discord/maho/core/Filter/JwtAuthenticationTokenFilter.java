package bot.discord.maho.core.Filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import bot.discord.maho.core.Model.AuthHeader;
import bot.discord.maho.core.Util.JwtTokenUtil;
import bot.discord.maho.core.Util.SecurityTool;
import bot.discord.maho.database.CrudService.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
	private final JwtTokenUtil jwtService;
    private final UserService userService;
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
