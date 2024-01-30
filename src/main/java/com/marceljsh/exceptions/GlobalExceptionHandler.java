// **************************************************************************
// * This code was created by Marcel Joshua (https://github.com/marceljsh)
// * within the context of One Piece Spring REST API.
// * Copyright (©) 2024 by Marcel Joshua, all rights reserved.
// *
// * This file was written using Java Spring Boot
// * and follows the principles of SOLID.
// *
// * Feel free to use or modify this code for your own purposes,
// * but please include this copyright notice.
// **************************************************************************

// package com.marceljsh.exceptions;

// import java.time.LocalDateTime;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

// import com.marceljsh.common.ErrorResponse;

// import jakarta.servlet.http.HttpServletRequest;

// @ControllerAdvice
// public class GlobalExceptionHandler {

// 	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
// 	@ResponseStatus(HttpStatus.BAD_REQUEST)
// 	public ResponseEntity<?> handleTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest request,
// 			String error) {
// 		ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), error,
// 				request.getRequestURI());

// 		return ResponseEntity.badRequest().body(errorResponse);
// 	}
// }
