package bot.discord.maho.api.Login.Manager;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import bot.discord.maho.api.Login.Exception.SpeedKeyNotFoundException;
import bot.discord.maho.database.CrudService.Impl.LoginDetailService;
import bot.discord.maho.security.Model.User4Jwt;
import bot.discord.maho.security.Service.JwtService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

@Service
@RequiredArgsConstructor
public class LoginManager {
	private final LoginDetailService spKeySvc;
	private final JDA jda;
	private final JwtService jwtSvc;
	
	public void verifyUser(UUID speedKey) {
		var key = spKeySvc.findById(speedKey).orElseThrow(() -> SpeedKeyNotFoundException.of());
		if(key.getIsUsed()) throw SpeedKeyNotFoundException.of("This key is already used.");
		if(key.getExpireTime().before(new Date())) throw SpeedKeyNotFoundException.of("This key is expired.");
		DateFormat dateFormat = DateFormat.getDateTimeInstance();
		jda.getUserById(key.getMember().getDiscordId()).openPrivateChannel().queue((channel) -> {
            channel.sendMessage("你於 " + dateFormat.format(new Date()) + " \n嘗試使用SpeedKey登入マホロボ網頁")
            	   .addActionRow(List.of(Button.primary("speed_key_confirm:"+speedKey, "是，這是我"), Button.danger("speed_key_cancel:"+speedKey, "不是我")))
            	   .queue();
        });
	}
	
	public String login(UUID speedKey) {
		var loginDetail = spKeySvc.findById(speedKey).orElseThrow(() -> SpeedKeyNotFoundException.of());
		if(loginDetail.getIsUsed() && loginDetail.getIsVerify())
			return jwtSvc.generateToken(User4Jwt.of(loginDetail));
		else
			return "";
	}

}
