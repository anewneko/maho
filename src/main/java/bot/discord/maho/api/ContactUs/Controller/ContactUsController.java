package bot.discord.maho.api.ContactUs.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bot.discord.maho.api.ContactUs.DTO.ContactUsDTO;
import bot.discord.maho.core.Model.ApiResponse;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;

@RestController
@RequiredArgsConstructor
public class ContactUsController {
	private final JDA bot; 
	
	
	@PostMapping("/contactUs")
	public ApiResponse contactUs(@RequestBody ContactUsDTO dto) {
		var user = bot.getUserById(276004500462895104L);
		if (user != null) 
            user.openPrivateChannel()
            	.queue(privateChannel -> privateChannel.sendMessage(dto.toString()).queue(), 
            							 Throwable::printStackTrace);
		return ApiResponse.success();
	}

}
