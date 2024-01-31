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
import com.marceljsh.models.entities.Region;
import com.marceljsh.services.RegionService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/regions")
public class RegionController {

	@Autowired
	private RegionService regionService;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Region region, HttpServletRequest request) {
		try {
			if (region.getName() == null || region.getName().trim().isEmpty()) {
				throw new IllegalArgumentException("region name cannot be empty");
			}

			return ResponseEntity.ok(regionService.save(region));

		} catch (IllegalArgumentException e) {
			ErrorResponse errorResponse = new ErrorResponse(
					HttpStatus.BAD_REQUEST.value(),
					e.getMessage(),
					LocalDateTime.now(),
					request.getRequestURI());

			return ResponseEntity.badRequest().body(errorResponse);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> readOne(@PathVariable String id, HttpServletRequest request) {
		try {
			Long numericId = Long.parseLong(id);
			return ResponseEntity.ok(regionService.findOne(numericId));

		} catch (NumberFormatException e) {
			ErrorResponse errorResponse = new ErrorResponse(
					HttpStatus.BAD_REQUEST.value(),
					"region id must be numeric",
					LocalDateTime.now(),
					request.getRequestURI());

			return ResponseEntity.badRequest().body(errorResponse);

		} catch (ResourceNotFoundException e) {
			ErrorResponse errorResponse = new ErrorResponse(
					HttpStatus.NOT_FOUND.value(),
					e.getMessage(),
					LocalDateTime.now(),
					request.getRequestURI());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}

	@GetMapping
	public ResponseEntity<?> read(@RequestParam(required = false) String keyword, HttpServletRequest request) {
		try {
			return ResponseEntity.ok(regionService.find(keyword));
		} catch (IllegalArgumentException e) {
			ErrorResponse errorResponse = new ErrorResponse(
					HttpStatus.BAD_REQUEST.value(),
					e.getMessage(),
					LocalDateTime.now(),
					request.getRequestURI());

			return ResponseEntity.badRequest().body(errorResponse);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable String id, @RequestBody Region region, HttpServletRequest request) {
		try {
			Long numericId = Long.parseLong(id);
			regionService.alter(numericId, region);

			return ResponseEntity.ok().body(regionService.findOne(numericId));

		} catch (NumberFormatException e) {
			ErrorResponse errorResponse = new ErrorResponse(
					HttpStatus.BAD_REQUEST.value(),
					"region id must be numeric",
					LocalDateTime.now(),
					request.getRequestURI());

			return ResponseEntity.badRequest().body(errorResponse);

		} catch (ResourceNotFoundException e) {
			ErrorResponse errorResponse = new ErrorResponse(
					HttpStatus.NOT_FOUND.value(),
					e.getMessage(),
					LocalDateTime.now(),
					request.getRequestURI());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable String id, HttpServletRequest request) {
		try {
			Long numericId = Long.parseLong(id);
			regionService.remove(numericId);

			return ResponseEntity.ok().body("region deleted successfully");

		} catch (NumberFormatException e) {
			ErrorResponse errorResponse = new ErrorResponse(
					HttpStatus.BAD_REQUEST.value(),
					"region id must be numeric",
					LocalDateTime.now(),
					request.getRequestURI());

			return ResponseEntity.badRequest().body(errorResponse);

		} catch (ResourceNotFoundException e) {
			ErrorResponse errorResponse = new ErrorResponse(
					HttpStatus.NOT_FOUND.value(),
					e.getMessage(),
					LocalDateTime.now(),
					request.getRequestURI());

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}
}
