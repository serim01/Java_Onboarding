package com.sparta.java_onboarding.common.exception;

import java.nio.file.AccessDeniedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sparta.java_onboarding.common.exception.dto.ExceptionResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ExceptionResponse> AccessDeniedException(HttpServletRequest request, Exception e) {
		e.printStackTrace();
		ExceptionResponse exceptionResponse = ExceptionResponse.builder()
			.msg(ErrorCode.ACCESS_DINIED.getMsg())
			.path(request.getRequestURI())
			.build();
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ExceptionResponse> defaultException(HttpServletRequest request, Exception e) {
		e.printStackTrace();
		ExceptionResponse exceptionResponse = ExceptionResponse.builder()
			.msg(ErrorCode.FAIL.getMsg())
			.path(request.getRequestURI())
			.build();
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ExceptionResponse> handleInvalidPasswordException(HttpServletRequest request,
		CustomException e) {
		ExceptionResponse exceptionResponse = ExceptionResponse.builder()
			.msg(e.getErrorCode().getMsg())
			.path(request.getRequestURI())
			.build();
		return new ResponseEntity<>(exceptionResponse, HttpStatusCode.valueOf(e.getErrorCode().getStatus()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return new ResponseEntity<>(e.getBindingResult().getFieldErrors().get(0).getDefaultMessage(),
			HttpStatus.BAD_REQUEST);
	}

}
