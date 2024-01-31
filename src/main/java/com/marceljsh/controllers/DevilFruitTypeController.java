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

import com.marceljsh.models.entities.DevilFruitType;
import com.marceljsh.services.DevilFruitTypeService;

@RestController
@RequestMapping("/api/v1/df-types")
public class DevilFruitTypeController {

	@Autowired
	private DevilFruitTypeService devilFruitTypeService;

	/**
	 * Create a new Devil Fruit Type.
	 *
	 * @param devilFruitType The Devil Fruit Type to create.
	 * @return The created Devil Fruit Type.
	 */
	@PostMapping
	public ResponseEntity<?> create(@RequestBody DevilFruitType devilFruitType) {
		return ResponseEntity.ok().body(devilFruitTypeService.save(devilFruitType));
	}

	/**
	 * Get a Devil Fruit Type by ID.
	 *
	 * @param id The ID of the Devil Fruit Type.
	 * @return The Devil Fruit Type with the specified ID.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> readOne(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(devilFruitTypeService.findOne(id));
	}

	/**
	 * Get all Devil Fruit Types.
	 *
	 * @param keyword (Optional) A keyword to filter Devil Fruit Types by name.
	 * @return A list of Devil Fruit Types.
	 */
	@GetMapping
	public ResponseEntity<?> read(@RequestParam(required = false) String keyword) {
		return ResponseEntity.ok().body(devilFruitTypeService.find(keyword));
	}

	/**
	 * Update a Devil Fruit Type by ID.
	 *
	 * @param id             The ID of the Devil Fruit Type to update.
	 * @param devilFruitType The updated Devil Fruit Type.
	 * @return The updated Devil Fruit Type.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody DevilFruitType devilFruitType) {
		return ResponseEntity.ok().body(devilFruitTypeService.alter(id, devilFruitType));
	}

	/**
	 * Delete a Devil Fruit Type by ID.
	 *
	 * @param id The ID of the Devil Fruit Type to delete.
	 * @return A message indicating the deletion status.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		devilFruitTypeService.remove(id);
		return ResponseEntity.ok().body(Map.of("message", "devil fruit type deleted successfully"));
	}
}
