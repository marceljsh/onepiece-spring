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
import com.marceljsh.models.entities.DevilFruitType;
import com.marceljsh.services.DevilFruitTypeService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/df-types")
public class DevilFruitTypeController {

	@Autowired
	private DevilFruitTypeService devilFruitTypeService;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody DevilFruitType devilFruitType, HttpServletRequest request) {
		try {
			if (devilFruitType.getName() == null || devilFruitType.getName().trim().isEmpty()) {
				throw new IllegalArgumentException("devil fruit type name cannot be empty");
			}

			return ResponseEntity.ok().body(devilFruitTypeService.save(devilFruitType));
		} catch (IllegalArgumentException e) {
			ErrorResponse errorResponse = new ErrorResponse(
					LocalDateTime.now(),
					HttpStatus.BAD_REQUEST.value(),
					e.getMessage(),
					request.getRequestURI());

			return ResponseEntity.badRequest().body(errorResponse);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> readOne(@PathVariable("id") String id, HttpServletRequest request) {
		try {
			Long numericId = Long.parseLong(id);
			DevilFruitType devilFruitType = devilFruitTypeService.findOne(numericId);

			return ResponseEntity.ok().body(devilFruitType);
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
	public ResponseEntity<?> read(@RequestParam(required = false) String keyword) {
		Iterable<DevilFruitType> devilFruitTypes = devilFruitTypeService.find(keyword);
		return ResponseEntity.ok().body(devilFruitTypes);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody DevilFruitType devilFruitType,
			HttpServletRequest request) {
		try {
			Long numericId = Long.parseLong(id);

			if (devilFruitType.getName() == null || devilFruitType.getName().trim().isEmpty()) {
				throw new IllegalArgumentException("devil fruit type name cannot be empty");
			}

			return ResponseEntity.ok().body(devilFruitTypeService.alter(numericId, devilFruitType));
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

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id, HttpServletRequest request) {
		try {
			Long numericId = Long.parseLong(id);
			devilFruitTypeService.remove(numericId);

			return ResponseEntity.ok().build();
		} catch (IllegalArgumentException e) {
			ErrorResponse errorResponse = new ErrorResponse(
					LocalDateTime.now(),
					HttpStatus.BAD_REQUEST.value(),
					e.getMessage(),
					request.getRequestURI());

			return ResponseEntity.badRequest().body(errorResponse);
		}
	}
}
