package bot.discord.maho.web.Login.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import bot.discord.maho.core.POJO.ApiResponse;

@RestController
@CrossOrigin
public class PingController {

	@GetMapping("/ping")
	public ApiResponse ping() {
		return ApiResponse.success("pong");
	}
}
