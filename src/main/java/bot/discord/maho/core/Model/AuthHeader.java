package bot.discord.maho.core.Model;

public class AuthHeader {
	private String content = "";

	private AuthHeader(String content) {
		this.content = content == null ? this.content : content;
	}
	
	public Boolean isJwt() {
		return !content.isEmpty() && content.startsWith("Bearer ");
	}
	
	public String getJwt() {
		return isJwt() ? content.substring(7) : "";
	}
	
	public String getContent() {
		return content;
	}
	
	public static AuthHeader of(String content) {
		return new AuthHeader(content);
	}
}
