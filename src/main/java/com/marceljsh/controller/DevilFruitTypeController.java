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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.marceljsh.helper.Bundler;
import com.marceljsh.model.entity.DevilFruitType;
import com.marceljsh.service.DevilFruitTypeService;

@RestController
@RequestMapping("/api/v1/df-types")
public class DevilFruitTypeController {

	@Autowired
	private DevilFruitTypeService devilFruitTypeService;

	/**
	 * Create a new DevilFruitType.
	 *
	 * @param devilFruitType The DevilFruitType object to be created.
	 * @return The ResponseEntity with the created DevilFruitType.
	 */
	@PostMapping
	public ResponseEntity<?> create(@RequestBody DevilFruitType devilFruitType) {
		return ResponseEntity.ok().body(devilFruitTypeService.save(devilFruitType));
	}

	/**
	 * Read a specific DevilFruitType by its ID.
	 *
	 * @param id The ID of the DevilFruitType to be read.
	 * @return The ResponseEntity with the requested DevilFruitType.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> readOne(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(devilFruitTypeService.findOne(id));
	}

	/**
	 * Read DevilFruitTypes with optional filtering and pagination.
	 *
	 * @param keyword The optional keyword for filtering DevilFruitTypes.
	 * @param page    The page number for pagination (default: 1).
	 * @param size    The page size for pagination (default: 10).
	 * @return The ResponseEntity with the paginated DevilFruitTypes.
	 */
	@GetMapping
	public ResponseEntity<?> read(@RequestParam(required = false) String keyword,
								  @RequestParam(defaultValue = "1") int page,
								  @RequestParam(defaultValue = "10") int size) {
		Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
		Page<DevilFruitType> pageResult = devilFruitTypeService.find(keyword, pageable);

		return ResponseEntity.ok().body(Bundler.pack(
				"devil_fruit_types", pageResult.getContent(),
				"page_size", pageResult.getSize(),
				"current_page", pageResult.getNumber() + 1,
				"total_pages", pageResult.getTotalPages(),
				"length", pageResult.getNumberOfElements(),
				"total_elements", pageResult.getTotalElements()
		));
	}

	/**
	 * Update a specific DevilFruitType by its ID.
	 *
	 * @param id              The ID of the DevilFruitType to be updated.
	 * @param devilFruitType  The updated DevilFruitType object.
	 * @return The ResponseEntity with the updated DevilFruitType.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody DevilFruitType devilFruitType) {
		return ResponseEntity.ok().body(devilFruitTypeService.alter(id, devilFruitType));
	}

	/**
	 * Delete a specific DevilFruitType by its ID.
	 *
	 * @param id The ID of the DevilFruitType to be deleted.
	 * @return The ResponseEntity with a success message.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		devilFruitTypeService.remove(id);
		return ResponseEntity.ok().body(Map.of("message", "devil fruit type deleted successfully"));
	}
}
