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

package com.marceljsh.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.marceljsh.model.entities.Occupation;
import com.marceljsh.service.OccupationService;

@RestController
@RequestMapping("/api/v1/occupations")
public class OccupationController {

	@Autowired
	private OccupationService occupationService;

	/**
	 * Create a new occupation.
	 *
	 * @param occupation The occupation object to be created.
	 * @return ResponseEntity with the created occupation.
	 */
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Occupation occupation) {
		return ResponseEntity.ok(occupationService.save(occupation));
	}

	/**
	 * Get a specific occupation by its ID.
	 *
	 * @param id The ID of the occupation to retrieve.
	 * @return ResponseEntity with the requested occupation.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> readOne(@PathVariable Long id) {
		return ResponseEntity.ok(occupationService.findOne(id));
	}

	/**
	 * Get a list of occupations.
	 *
	 * @param keyword (optional) A keyword to filter the occupations.
	 * @return ResponseEntity with the list of occupations.
	 */
	@GetMapping
	public ResponseEntity<?> read(@RequestParam(required = false) String keyword) {
		return ResponseEntity.ok(occupationService.find(keyword));
	}

	/**
	 * Update an existing occupation.
	 *
	 * @param id         The ID of the occupation to update.
	 * @param occupation The updated occupation object.
	 * @return ResponseEntity with the updated occupation.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Occupation occupation) {
		return ResponseEntity.ok(occupationService.alter(id, occupation));
	}

	/**
	 * Delete an occupation by its ID.
	 *
	 * @param id The ID of the occupation to delete.
	 * @return ResponseEntity with a success message.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		occupationService.remove(id);
		return ResponseEntity.ok().body(Map.of("message", "occupation deleted successfully"));
	}
}
