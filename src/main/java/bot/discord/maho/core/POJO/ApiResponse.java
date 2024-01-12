package bot.discord.maho.core.POJO;

import java.io.Serializable;

public class ApiResponse implements Serializable{
	private static final long serialVersionUID = 8247984781330131050L;
	private Object data;
	private Integer code = 200;
	private String message = "";
	
	public ApiResponse() {
		super();
	}
	
	public ApiResponse( Object data ,String message, Integer code ) {
		super();
		this.message = message;
		this.code = code;
		this.data = data;
	}
	
	public ApiResponse(Object data, String message) {
		super();
		this.message = message;
		this.data = data;
	}
	
	public ApiResponse(Object data) {
		super();
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public ApiResponse setData(Object data) {
		this.data = data;
		return this;
	}

	public Integer getCode() {
		return code;
	}

	public ApiResponse setCode(Integer code) {
		this.code = code;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public ApiResponse setMessage(String message) {
		this.message = message;
		return this;
	}
	
	
}
