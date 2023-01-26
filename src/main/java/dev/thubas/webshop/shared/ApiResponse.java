package dev.thubas.webshop.shared;

import java.time.LocalDateTime;

public class ApiResponse {
	
	private final boolean success;
	private String message;
	
	public ApiResponse(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public String getTimestamp() {
		return LocalDateTime.now().toString();
	}

}
