package bot.discord.maho.security.Controller;

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
	
	@GetMapping("/redirect/mahoweb/homepage")
    public String redirectToExternalUrl(@PathParam("code") String code) {
		var token = api.getToken(code);
		var user = api.getUserInfo(token);
		var member =  memberService.findByDiscordId(user.getId());
		
		if(member == null)
			member = memberService.save(Member.of(user));
		if(member.update(user))
			memberService.save(member);
				
		jwtService.generateToken(User4Jwt.of(member));
        return "redirect:http://localhost:3000/";
    }
}
