package bot.discord.maho.web.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import bot.discord.maho.core.Model.ApiResponse;
import bot.discord.maho.core.Model.JwtOwner;
import bot.discord.maho.database.CrudService.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
	@Autowired private JwtOwner owner;
	private final UserService userService;
	
	@GetMapping("/member/info")
	@CrossOrigin
	public ApiResponse getInfo() {
		var member = userService.loadMemberById(owner.getUser().getUsername());
		return ApiResponse.success(member);
			
	}
	
	
	
	
	
	
	
	
	
	
	

}
