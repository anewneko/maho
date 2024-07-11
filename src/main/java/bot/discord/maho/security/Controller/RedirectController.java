package bot.discord.maho.security.Controller;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import bot.discord.maho.security.Model.User4Jwt;
import bot.discord.maho.security.Service.DiscordAPI;
import bot.discord.maho.security.Service.JwtService;
import bot.discord.maho.security.Service.UpdateMemberService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RedirectController {
	final private JwtService jwtService;
	final private DiscordAPI api;
	final private UpdateMemberService updateMemberService;
	final private RedisTemplate<String,String> redisTemplate;
	@Value("${discord.app.frontend}") private String frontEndUrl;
	
	@GetMapping("/redirect/mahoweb/homepage")
    public String redirectToExternalUrl(@PathParam("code") String code) {
		var uuid = UUID.randomUUID().toString();
		try {
			var token = api.getToken(code);
			var user = api.getUserInfo(token);
			var loginDetail = updateMemberService.createOrUpdate(user);
			var jwt = jwtService.generateToken(User4Jwt.of(loginDetail));
			redisTemplate.opsForValue().set(uuid, jwt , 10L , TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
        return "redirect:"+ frontEndUrl +"?id=" + uuid;
    }
}
