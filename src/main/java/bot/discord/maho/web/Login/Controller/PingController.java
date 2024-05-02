package bot.discord.maho.web.Login.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import bot.discord.maho.core.POJO.ApiResponse;

@RestController
public class PingController {

	@GetMapping("/ping")
	public ApiResponse ping() {
		return new ApiResponse(200, "pong");
	}
}
