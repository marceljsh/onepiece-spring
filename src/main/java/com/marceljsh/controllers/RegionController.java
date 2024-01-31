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

import com.marceljsh.models.entities.Region;
import com.marceljsh.services.RegionService;

@RestController
@RequestMapping("/api/v1/regions")
public class RegionController {

	@Autowired
	private RegionService regionService;

	/**
	 * Create a new region.
	 *
	 * @param region The region object to be created.
	 * @return ResponseEntity with the created region.
	 */
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Region region) {
		return ResponseEntity.ok(regionService.save(region));
	}

	/**
	 * Get a region by its ID.
	 *
	 * @param id The ID of the region to retrieve.
	 * @return ResponseEntity with the retrieved region.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> readOne(@PathVariable Long id) {
		return ResponseEntity.ok(regionService.findOne(id));
	}

	/**
	 * Get all regions or filter regions by keyword.
	 *
	 * @param keyword (optional) The keyword to filter regions by.
	 * @return ResponseEntity with the list of regions.
	 */
	@GetMapping
	public ResponseEntity<?> read(@RequestParam(required = false) String keyword) {
		return ResponseEntity.ok(regionService.find(keyword));
	}

	/**
	 * Update a region by its ID.
	 *
	 * @param id     The ID of the region to update.
	 * @param region The updated region object.
	 * @return ResponseEntity with the updated region.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Region region) {
		return ResponseEntity.ok().body(regionService.alter(id, region));
	}

	/**
	 * Delete a region by its ID.
	 *
	 * @param id The ID of the region to delete.
	 * @return ResponseEntity with a success message.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		regionService.remove(id);
		return ResponseEntity.ok().body(Map.of("message", "region deleted successfully"));
	}
}
