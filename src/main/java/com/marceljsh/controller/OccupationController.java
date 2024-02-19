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
import com.marceljsh.model.entity.Occupation;
import com.marceljsh.service.OccupationService;

@RestController
@RequestMapping("/api/v1/occupations")
public class OccupationController {

	@Autowired
	private OccupationService occupationService;

	/**
	 * Creates a new occupation.
	 *
	 * @param occupation The occupation to be created.
	 * @return The ResponseEntity with the created occupation.
	 */
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Occupation occupation) {
		return ResponseEntity.ok(occupationService.save(occupation));
	}

	/**
	 * Retrieves a specific occupation by its ID.
	 *
	 * @param id The ID of the occupation to be retrieved.
	 * @return The ResponseEntity with the retrieved occupation.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> readOne(@PathVariable Long id) {
		return ResponseEntity.ok(occupationService.findOne(id));
	}

	/**
	 * Retrieves a list of occupations based on the provided keyword, page, and
	 * size.
	 *
	 * @param keyword The keyword to search for in occupation names.
	 * @param page    The page number of the results.
	 * @param size    The number of results per page.
	 * @return The ResponseEntity with the list of occupations and pagination
	 *         information.
	 */
	@GetMapping
	public ResponseEntity<?> read(@RequestParam(required = false) String keyword,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
		Page<Occupation> pageResult = occupationService.find(keyword, pageable);

		return ResponseEntity.ok(Bundler.pack(
				"occupations", pageResult.getContent(),
				"page_size", pageResult.getSize(),
				"current_page", pageResult.getNumber(),
				"total_pages", pageResult.getTotalPages(),
				"length", pageResult.getNumberOfElements(),
				"total_elements", pageResult.getTotalElements()));
	}

	/**
	 * Updates an existing occupation.
	 *
	 * @param id         The ID of the occupation to be updated.
	 * @param occupation The updated occupation.
	 * @return The ResponseEntity with the updated occupation.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Occupation occupation) {
		return ResponseEntity.ok(occupationService.alter(id, occupation));
	}

	/**
	 * Deletes an occupation by its ID.
	 *
	 * @param id The ID of the occupation to be deleted.
	 * @return The ResponseEntity with a success message.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		occupationService.remove(id);
		return ResponseEntity.ok().body(Map.of("message", "occupation deleted successfully"));
	}
}
