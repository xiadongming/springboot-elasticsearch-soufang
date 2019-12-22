package com.example.demo.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.config.WebMvcConfig;

public class ApiResponse {

	private final  Logger logger = LoggerFactory.getLogger(ApiResponse.class);
	private Integer code;

	private String message;

	private Object data;

	private boolean more;

	
	
	
	
	public ApiResponse(Integer code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public ApiResponse() {
		this.code = Status.SUCCESS.getCode();
		this.message = Status.SUCCESS.getStandardMessage();
		
	}

	
	public static ApiResponse ofMessage(Integer code,String message) {
		return new ApiResponse(code,message,null);
	}
	
	public static ApiResponse ofSuccess(Object data) {
		return new ApiResponse(Status.SUCCESS.getCode(),Status.SUCCESS.getStandardMessage(),data);
	}
	
	public static ApiResponse ofStatus(Status status) {
		return new ApiResponse(status.getCode(),status.getStandardMessage(),null);
	}
	
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isMore() {
		return more;
	}

	public void setMore(boolean more) {
		this.more = more;
	}
	
	//枚举值
	public enum Status {
		SUCCESS(200,"OK"),
		BAD_REQUEST(400,"Bad Request"),
		INTERNAL_SERVER_ERROR(500,"Unknown Internal Error"),
		NOT_VALID_PARAM(40005,"Not valid Params"),
		NOT_SUPPORTED_OPREATION(40006,"Operation not supported"),
		NOT_LOGIN(50000,"Not Login");
		
		private Integer code;
		private String standardMessage;
		Status(Integer code,String standardMessage){
			this.code = code;
			this.standardMessage = standardMessage;
		}
		public Integer getCode() {
			return code;
		}
		public void setCode(Integer code) {
			this.code = code;
		}
		public String getStandardMessage() {
			return standardMessage;
		}
		public void setStandardMessage(String standardMessage) {
			this.standardMessage = standardMessage;
		}
		
	}
	
	

}
