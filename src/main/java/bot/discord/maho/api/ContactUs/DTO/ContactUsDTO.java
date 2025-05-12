package bot.discord.maho.api.ContactUs.DTO;

import lombok.Data;
@Data
public class ContactUsDTO {
	private String name;
	private String email;
	private String content;
	
	public String toString() {
		return String.format("Name: %s\nEmail: %s\nContent: %s", name, email, content);
	}
}
