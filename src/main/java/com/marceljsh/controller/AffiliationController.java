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
import com.marceljsh.model.entity.Affiliation;
import com.marceljsh.service.AffiliationService;

@RestController
@RequestMapping("/api/v1/affiliations")
public class AffiliationController {

	@Autowired
	private AffiliationService affiliationService;

	/**
	 * Create a new affiliation.
	 *
	 * @param affiliation The affiliation to create.
	 * @return The created affiliation.
	 */
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Affiliation affiliation) {
		return ResponseEntity.ok(affiliationService.save(affiliation));
	}

	/**
	 * Get an affiliation by its ID.
	 *
	 * @param id The ID of the affiliation.
	 * @return The affiliation with the specified ID.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> readOne(@PathVariable Long id) {
		return ResponseEntity.ok(affiliationService.findOne(id));
	}

	/**
	 * Get a list of affiliations.
	 *
	 * @param keyword The keyword to search for in the affiliations.
	 * @param page    The page number.
	 * @param size    The number of affiliations per page.
	 * @return A paginated list of affiliations.
	 */
	@GetMapping
	public ResponseEntity<?> read(@RequestParam(required = false) String keyword,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
		Page<Affiliation> pageResult = affiliationService.find(keyword, pageable);

		return ResponseEntity.ok(Bundler.pack(
				"affiliations", pageResult.getContent(),
				"page_size", pageResult.getSize(),
				"current_page", pageResult.getNumber() + 1,
				"total_pages", pageResult.getTotalPages(),
				"length", pageResult.getNumberOfElements(),
				"total_elements", pageResult.getTotalElements()));
	}

	/**
	 * Update an affiliation by its ID.
	 *
	 * @param id          The ID of the affiliation to update.
	 * @param affiliation The updated affiliation.
	 * @return The updated affiliation.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Affiliation affiliation) {
		return ResponseEntity.ok(affiliationService.alter(id, affiliation));
	}

	/**
	 * Delete an affiliation by its ID.
	 *
	 * @param id The ID of the affiliation to delete.
	 * @return A message indicating the deletion status.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		affiliationService.remove(id);
		return ResponseEntity.ok(Map.of("message", "affiliation deleted successfully"));
	}
}
