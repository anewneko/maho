package bot.discord.maho.web.Page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import bot.discord.maho.core.Util.JwtTokenUtil;
import bot.discord.maho.database.CrudService.UserService;
import bot.discord.maho.database.Entity.Member;
import bot.discord.maho.web.Page.Model.DiscordAPI;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RedirectController {
	final private JwtTokenUtil jwtService;
	final private DiscordAPI api;
	final private UserService userService;
	
	@GetMapping("/redirect/mahoweb/homepage")
    public String redirectToExternalUrl(@PathParam("code") String code) {
			
		var token = api.getToken(code);
		var user = api.getUserInfo(token);
		
		var member = userService.loadUserByDiscordId(user.getId());
		
		if (member == null) 
			member = userService.createUser(Member.of(user));
		
		jwtService.generateToken(member);
		
        return "redirect:http://localhost:3000/";
    }
}
