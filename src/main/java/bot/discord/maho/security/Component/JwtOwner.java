package bot.discord.maho.security.Component;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Data;

@Component
@RequestScope
@Data
public class JwtOwner {
	private UserDetails user;
}
