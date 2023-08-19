package bot.discord.maho.bookkeeping.core.pojo;

import java.io.Serializable;

public class CorePojo implements Serializable{
	private static final long serialVersionUID = 8247984781330131050L;
	private String message;
	private String success;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
}
