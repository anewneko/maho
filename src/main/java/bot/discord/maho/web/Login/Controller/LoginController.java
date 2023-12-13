package bot.discord.maho.web.Login.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bot.discord.maho.database.service.UserService;

@RestController
@RequestMapping("/Login")
public class LoginController {
	@Autowired
	UserService userService;

	@PostMapping
	public String login() {
		return "login";
	}
}
