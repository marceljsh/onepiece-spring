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

package com.marceljsh.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marceljsh.common.ErrorResponse;
import com.marceljsh.exceptions.ResourceNotFoundException;
import com.marceljsh.models.entities.Occupation;
import com.marceljsh.services.OccupationService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/occupations")
public class OccupationController {

	@Autowired
	private OccupationService occupationService;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Occupation occupation, HttpServletRequest request) {
		try {
			if (occupation.getName() == null || occupation.getName().trim().isEmpty()) {
				throw new IllegalArgumentException("occupation name cannot be empty");
			}

			return ResponseEntity.ok(occupationService.save(occupation));

		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> readOne(@PathVariable String id, HttpServletRequest request) {
		try {
			Long numericId = Long.parseLong(id);
			return ResponseEntity.ok(occupationService.findOne(numericId));

		} catch (NumberFormatException e) {
			ErrorResponse errorResponse = new ErrorResponse(
					LocalDateTime.now(),
					HttpStatus.BAD_REQUEST.value(),
					e.getMessage(),
					request.getRequestURI());

			return ResponseEntity.badRequest().body(errorResponse);
		} catch (ResourceNotFoundException e) {
			ErrorResponse errorResponse = new ErrorResponse(
					LocalDateTime.now(),
					HttpStatus.NOT_FOUND.value(),
					e.getMessage(),
					request.getRequestURI());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}

	@GetMapping
	public ResponseEntity<?> read(@RequestParam(required = false) String keyword, HttpServletRequest request) {
		try {
			return ResponseEntity.ok(occupationService.find(keyword));

		} catch (IllegalArgumentException e) {
			ErrorResponse errorResponse = new ErrorResponse(
					LocalDateTime.now(),
					HttpStatus.BAD_REQUEST.value(),
					e.getMessage(),
					request.getRequestURI());

			return ResponseEntity.badRequest().body(errorResponse);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable String id, @RequestBody Occupation occupation,
			HttpServletRequest request) {
		try {
			Long numericId = Long.parseLong(id);
			occupationService.alter(numericId, occupation);
			return ResponseEntity.ok().build();

		} catch (NumberFormatException e) {
			ErrorResponse errorResponse = new ErrorResponse(
					LocalDateTime.now(),
					HttpStatus.BAD_REQUEST.value(),
					"occupation id must be numeric",
					request.getRequestURI());

			return ResponseEntity.badRequest().body(errorResponse);
		} catch (ResourceNotFoundException e) {
			ErrorResponse errorResponse = new ErrorResponse(
					LocalDateTime.now(),
					HttpStatus.NOT_FOUND.value(),
					e.getMessage(),
					request.getRequestURI());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id, HttpServletRequest request) {
		try {
			Long numericId = Long.parseLong(id);
			occupationService.remove(numericId);
			return ResponseEntity.ok().build();

		} catch (NumberFormatException e) {
			ErrorResponse errorResponse = new ErrorResponse(
					LocalDateTime.now(),
					HttpStatus.BAD_REQUEST.value(),
					"occupation id must be numeric",
					request.getRequestURI());

			return ResponseEntity.badRequest().body(errorResponse);
		} catch (ResourceNotFoundException e) {
			ErrorResponse errorResponse = new ErrorResponse(
					LocalDateTime.now(),
					HttpStatus.NOT_FOUND.value(),
					e.getMessage(),
					request.getRequestURI());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}
}
