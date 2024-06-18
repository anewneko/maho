package bot.discord.maho.web.Login.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import bot.discord.maho.core.Model.ApiResponse;
import bot.discord.maho.core.Util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PingController {
	private final JwtTokenUtil jwtService;

	@GetMapping("/ping")
	public ApiResponse ping() {
		return ApiResponse.success("pong");
	}
	
	@GetMapping("/login")
	public ApiResponse login() {
		jwtService.refreshToken();
		return ApiResponse.success("Login");
	}
	
	@GetMapping("/discord/token/{code}")
	public ApiResponse logout(@PathVariable String code) {
		System.out.println(code);
		return ApiResponse.success("token");
	}
}
