package bot.discord.maho.security.Service;

import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bot.discord.maho.core.Util.MahoTool;
import bot.discord.maho.database.CrudService.Impl.LoginDetailService;
import bot.discord.maho.database.Entity.LoginDetail;
import bot.discord.maho.security.Component.JwtOwner;
import bot.discord.maho.security.Model.User4Jwt;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecurityUserService  implements UserDetailsService  {
//	private final UserService userService;
	private final LoginDetailService loginDetailService;
	@Autowired JwtOwner owner;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var id = MahoTool.isUUID(username) ? UUID.fromString(username) : null;
		return id == null ? null : loadAndVerify(() -> loginDetailService.findById(id).orElse(null));
	}
	
//	public UserDetails loadUserByDiscordId(Long discordId) throws UsernameNotFoundException  {
//		return loadAndVerify(() -> userService.findByDiscordId(discordId));
//	}
	
	private UserDetails loadAndVerify(Supplier<LoginDetail> supplier) {
		var loginDetail = supplier.get();
		if (loginDetail != null && loginDetail.getIsVerify() && loginDetail.getIsUsed())
			owner.of(loginDetail.getMember());
		else
			return null;
		return User4Jwt.of(loginDetail);
	}

}
