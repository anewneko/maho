package bot.discord.maho.web.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import bot.discord.maho.core.Model.User4Jwt;
import bot.discord.maho.core.Util.JwtTokenUtil;
import bot.discord.maho.web.Page.Model.DiscordToken;
import bot.discord.maho.web.Page.Model.DiscordUser;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RedirectController {
	private static final String discord_token_url = "https://discord.com/api/oauth2/token";
	private static final String discord_user_url = "https://discord.com/api/users/@me";
	@Autowired private HttpServletResponse response;
	final private RestTemplate restTemplate;
	final private JwtTokenUtil jwtService;
	
	@Value("${discord.app.client.id}")
	private String clientId;
	@Value("${discord.app.secret}")
	private String clientSecret;
	
	@GetMapping("/redirect/mahoweb/homepage")
    public String redirectToExternalUrl(@PathParam("code") String code) {
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	    var redirectUrl = "http://localhost:8081/mahoBotServer/redirect/mahoweb/homepage";
	    
	    MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("code", code);
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("grant_type", "authorization_code");
        map.add("redirect_uri", redirectUrl);
		
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        
        var token = restTemplate.postForObject(discord_token_url, request, DiscordToken.class);
        
        headers = new HttpHeaders();
        headers.setBearerAuth(token.getAccess_token());
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        
        var user = restTemplate.exchange(discord_user_url,  HttpMethod.GET,  entity,  DiscordUser.class);
        var userDetail = new User4Jwt();
        userDetail.setDiscordId(user.getBody().getId());
        
        Cookie cookie = new Cookie("Maho_token", jwtService.generateToken(userDetail));
        cookie.setPath("/");
        response.addCookie(cookie);
			
		
        return "redirect:http://localhost:3000/";
    }
}
