package bot.discord.maho.security.Component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import bot.discord.maho.security.Model.DiscordToken;
import bot.discord.maho.security.Model.DiscordUser;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class DiscordAPI {
	private static final String token_url = "https://discord.com/api/oauth2/token";
	private static final String user_url = "https://discord.com/api/users/@me";
	@Value("${discord.app.client.id}") private String clientId;
	@Value("${discord.app.secret}") private String clientSecret;
	@Value("${discord.app.redirect}") private String redirectUrl;
	final private RestTemplate restTemplate;
	
	public DiscordToken getToken(String code) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	    
	    MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("code", code);
        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("grant_type", "authorization_code");
        map.add("redirect_uri", redirectUrl);
		
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        
        return restTemplate.postForObject(token_url, request, DiscordToken.class);
	}
	
	public DiscordUser getUserInfo(DiscordToken token) {
		HttpHeaders headers = new HttpHeaders();
        
        headers = new HttpHeaders();
        headers.setBearerAuth(token.getAccess_token());
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        
        return restTemplate.exchange(user_url,  HttpMethod.GET,  entity,  DiscordUser.class).getBody();
		
	}

}
