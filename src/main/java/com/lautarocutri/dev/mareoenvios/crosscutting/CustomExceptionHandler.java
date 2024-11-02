package com.lautarocutri.dev.mareoenvios.crosscutting;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	private boolean isAdmin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth != null && auth.getAuthorities().stream()
				.anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ADMIN"));
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex,
			@Nullable Object body,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request) {

		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("status", status.value());
		responseBody.put("error", status.getReasonPhrase());

		// Conditionally include stack trace for admins
		if (isAdmin()) {
			responseBody.put("stackTrace", ex.getStackTrace());
			responseBody.put("message", ex.getMessage());
		}

		return new ResponseEntity<>(responseBody, headers, status);
	}

	// Catch-all handler for other exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
}
