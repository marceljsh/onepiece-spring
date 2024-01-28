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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marceljsh.common.ErrorResponse;
import com.marceljsh.exceptions.ResourceNotFoundException;
import com.marceljsh.models.dto.DevilFruitDTO;
import com.marceljsh.models.entities.DevilFruit;
import com.marceljsh.models.entities.DevilFruitType;
import com.marceljsh.services.DevilFruitService;
import com.marceljsh.services.DevilFruitTypeService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/devil-fruits")
public class DevilFruitController {

	@Autowired
	private DevilFruitService devilFruitService;

	@Autowired
	private DevilFruitTypeService devilFruitTypeService;

	// * helper methods
	public DevilFruit convertToDevilFruit(DevilFruitDTO devilFruitDTO) {
		DevilFruit devilFruit = new DevilFruit();
		DevilFruitType devilFruitType = devilFruitTypeService.findOne(devilFruitDTO.getDevilFruitTypeId());

		devilFruit.setName(devilFruitDTO.getName());
		devilFruit.setEnglishName(devilFruitDTO.getEnglishName());
		devilFruit.setDevilFruitType(devilFruitType);

		return devilFruit;
	}

	// * API endpoints
	@PostMapping
	public ResponseEntity<?> create(@RequestBody DevilFruitDTO devilFruitDTO, HttpServletRequest request) {
		try {
			if (devilFruitDTO.getDevilFruitTypeId() == null) {
				throw new IllegalArgumentException("devil fruit type id is required");
			}

			if (devilFruitDTO.getName() == null || devilFruitDTO.getName().trim().isEmpty()) {
				throw new IllegalArgumentException("devil fruit name is required");
			}

			if (devilFruitDTO.getEnglishName() == null || devilFruitDTO.getEnglishName().trim().isEmpty()) {
				throw new IllegalArgumentException("devil fruit english name is required");
			}

			DevilFruit devilFruit = convertToDevilFruit(devilFruitDTO);

			return ResponseEntity.ok().body(devilFruitService.save(devilFruit));

		} catch (IllegalArgumentException e) {
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

	@GetMapping("/{id}")
	public ResponseEntity<?> readOne(@PathVariable("id") String id, HttpServletRequest request) {
		try {
			Long numericId = Long.parseLong(id);
			DevilFruit devilFruit = devilFruitService.findOne(numericId);

			return ResponseEntity.ok().body(devilFruit);

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
		Iterable<DevilFruit> devilFruits = devilFruitService.find(keyword);
		return ResponseEntity.ok().body(devilFruits);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody DevilFruitDTO devilFruitDTO,
			HttpServletRequest request) {
		try {
			if (devilFruitDTO.getDevilFruitTypeId() == null) {
				throw new IllegalArgumentException("devil fruit type id is required");
			}

			if (devilFruitDTO.getName() == null || devilFruitDTO.getName().trim().isEmpty()) {
				throw new IllegalArgumentException("devil fruit name is required");
			}

			if (devilFruitDTO.getEnglishName() == null || devilFruitDTO.getEnglishName().trim().isEmpty()) {
				throw new IllegalArgumentException("devil fruit english name is required");
			}

			DevilFruit devilFruit = convertToDevilFruit(devilFruitDTO);
			Long numericId = Long.parseLong(id);
			DevilFruit result = devilFruitService.alter(numericId, devilFruit);

			return ResponseEntity.ok().body(result);

		} catch (IllegalArgumentException e) {
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
			devilFruitService.remove(numericId);

			return ResponseEntity.ok().body("Devil fruit deleted successfully");

		} catch (IllegalArgumentException e) {
			ErrorResponse errorResponse = new ErrorResponse(
					LocalDateTime.now(),
					HttpStatus.NOT_FOUND.value(),
					e.getMessage(),
					request.getRequestURI());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}
}
