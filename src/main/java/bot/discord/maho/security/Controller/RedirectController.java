package bot.discord.maho.security.Controller;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import bot.discord.maho.database.CrudService.Impl.UserService;
import bot.discord.maho.database.Entity.Member;
import bot.discord.maho.security.Component.DiscordAPI;
import bot.discord.maho.security.Component.JwtTokenUtil;
import bot.discord.maho.security.Model.User4Jwt;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RedirectController {
	final private JwtTokenUtil jwtService;
	final private DiscordAPI api;
	final private UserService memberService;
	final private RedisTemplate<String,String> redisTemplate;
	@Value("${discord.app.frontend}") private String frontEndUrl;
	
	@GetMapping("/redirect/mahoweb/homepage")
    public String redirectToExternalUrl(@PathParam("code") String code) {
		var token = api.getToken(code);
		var user = api.getUserInfo(token);
		var member =  memberService.findByDiscordId(user.getId());
		
		if(member == null)
			member = memberService.save(Member.of(user));
		if(member.update(user))
			memberService.save(member);
		
		var jwt = jwtService.generateToken(User4Jwt.of(member));
		var uuid = UUID.randomUUID().toString();
		redisTemplate.opsForValue().set(uuid, jwt , 10L , TimeUnit.SECONDS);
			
        return "redirect:"+ frontEndUrl +"?id=" + uuid;
    }
}
