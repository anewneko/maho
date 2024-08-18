package bot.discord.maho.api.Member.Controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import bot.discord.maho.core.Model.ApiResponse;
import bot.discord.maho.security.Component.JwtOwner;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
	private final JwtOwner owner;
	private final RedisTemplate<String,String> redisTemplate;
	
	@GetMapping("/member/info")
	public ApiResponse getInfo() {
		return ApiResponse.success(owner.getMember());
	}
	
	
	@GetMapping("/member/jwt/{uuid}")
	public ApiResponse getJwt(@PathVariable String uuid) {
		try {
            var jwt = redisTemplate.opsForValue().get(uuid);
            System.out.println(jwt);
            return ApiResponse.success(jwt);
        } catch (Exception e) {
			e.printStackTrace();
        }
		return ApiResponse.success("");
	}
	
	
	
	
	
	
	
	
	
	

}
