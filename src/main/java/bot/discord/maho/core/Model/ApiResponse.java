package bot.discord.maho.core.Model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ApiResponse implements Serializable{
	private static final long serialVersionUID = 8247984781330131050L;
	private Object data;
	private Integer code = 200;
	private String message = "";
	
	private ApiResponse(String message, Integer code ) {
		super();
		this.message = message;
		this.code = code;
	}
	
	private ApiResponse(Object data, String message) {
		super();
		this.message = message;
		this.data = data;
	}
	
	private ApiResponse(Object data) {
		super();
		this.data = data;
	}

	public static ApiResponse error(String message, Integer code) {
		return new ApiResponse(message, code);
	}
	
	public static ApiResponse success(Object data) {
		return new ApiResponse(data);
	}
	
	public static ApiResponse success(Object data, String message) {
		return new ApiResponse(data,message);
	}
	
	
}
