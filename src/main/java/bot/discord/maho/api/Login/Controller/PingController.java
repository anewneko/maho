package bot.discord.maho.api.Login.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import bot.discord.maho.core.Model.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PingController {

	@GetMapping("/ping")
	public ApiResponse ping() {
		return ApiResponse.success("pong");
	}
}
