package bot.discord.maho.api.Login.Exception;

public class SpeedKeyNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 2446546875358181111L;
	private static final String DEFAULT_MESSAGE = "Speed Key Not Found";
	
	private SpeedKeyNotFoundException(String message) {
		super(message);
	}
	
	public static SpeedKeyNotFoundException of() {
		return new SpeedKeyNotFoundException(DEFAULT_MESSAGE);
	}
	
	public static SpeedKeyNotFoundException of(String message) {
		return new SpeedKeyNotFoundException(message);
	}

}
