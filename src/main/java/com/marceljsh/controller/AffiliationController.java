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
	 * @param affiliation The affiliation object to be created.
	 * @return The ResponseEntity with the created affiliation.
	 */
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Affiliation affiliation) {
		return ResponseEntity.ok(affiliationService.save(affiliation));
	}

	/**
	 * Read a specific affiliation by its ID.
	 *
	 * @param id The ID of the affiliation to be read.
	 * @return The ResponseEntity with the requested affiliation.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> readOne(@PathVariable Long id) {
		return ResponseEntity.ok(affiliationService.findOne(id));
	}

	/**
	 * Read all affiliations.
	 *
	 * @param keyword (optional) A keyword to filter affiliations by.
	 * @return The ResponseEntity with the list of affiliations.
	 */
	@GetMapping
	public ResponseEntity<?> read(@RequestParam(required = false) String keyword,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
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
	 * Update an existing affiliation.
	 *
	 * @param id          The ID of the affiliation to be updated.
	 * @param affiliation The updated affiliation object.
	 * @return The ResponseEntity with the updated affiliation.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Affiliation affiliation) {
		return ResponseEntity.ok(affiliationService.alter(id, affiliation));
	}

	/**
	 * Delete an affiliation by its ID.
	 *
	 * @param id The ID of the affiliation to be deleted.
	 * @return The ResponseEntity with a success message.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		affiliationService.remove(id);
		return ResponseEntity.ok(Map.of("message", "affiliation deleted successfully"));
	}
}
