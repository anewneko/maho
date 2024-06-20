package bot.discord.maho.api.Member.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import bot.discord.maho.core.Model.ApiResponse;
import bot.discord.maho.security.Component.JwtOwner;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
	@Autowired private JwtOwner owner;
	
	@GetMapping("/member/info")
	@CrossOrigin
	public ApiResponse getInfo() {
		return ApiResponse.success(owner.getMember());
	}
	
	
	
	
	
	
	
	
	
	
	

}
