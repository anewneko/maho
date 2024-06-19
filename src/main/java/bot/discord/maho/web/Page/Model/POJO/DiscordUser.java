package bot.discord.maho.web.Page.Model.POJO;

import lombok.Data;

@Data
public class DiscordUser {
	private Long id;
	private String username;
	private String avatar;
	private String locale;
	private String email;

}
