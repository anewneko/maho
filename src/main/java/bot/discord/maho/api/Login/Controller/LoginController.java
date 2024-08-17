package bot.discord.maho.api.Login.Controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import bot.discord.maho.api.Login.Manager.LoginManager;
import bot.discord.maho.core.Model.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {
	private final LoginManager loginManager;
	
	@GetMapping("/login/verifyUser/{speedKey}")
	public ApiResponse getVerifyNumber(@PathVariable UUID speedKey) {
		loginManager.verifyUser(speedKey);
		return ApiResponse.success();
	}
	
	@GetMapping("/login/{speedKey}")
	public ApiResponse getLogin(@PathVariable UUID speedKey) {
		return ApiResponse.success(loginManager.login(speedKey));
	}

}
