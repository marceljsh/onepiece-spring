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
import com.marceljsh.model.entity.Region;
import com.marceljsh.service.RegionService;

@RestController
@RequestMapping("/api/v1/regions")
public class RegionController {

	@Autowired
	private RegionService regionService;

	/**
	 * Creates a new region.
	 *
	 * @param region the region to be created
	 * @return the response entity with the created region
	 */
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Region region) {
		return ResponseEntity.ok(regionService.save(region));
	}

	/**
	 * Retrieves a specific region by its ID.
	 *
	 * @param id the ID of the region to be retrieved
	 * @return the response entity with the retrieved region
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> readOne(@PathVariable Long id) {
		return ResponseEntity.ok(regionService.findOne(id));
	}

	/**
	 * Retrieves a list of regions based on the specified parameters.
	 *
	 * @param keyword the keyword to search for in region names (optional)
	 * @param page    the page number (default: 1)
	 * @param size    the page size (default: 10)
	 * @return the response entity with the list of regions and pagination
	 *         information
	 */
	@GetMapping
	public ResponseEntity<?> read(@RequestParam(required = false) String keyword,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
		Page<Region> pageResult = regionService.find(keyword, pageable);

		return ResponseEntity.ok(Bundler.pack(
				"regions", pageResult.getContent(),
				"page_size", pageResult.getSize(),
				"current_page", pageResult.getNumber() + 1,
				"total_pages", pageResult.getTotalPages(),
				"length", pageResult.getNumberOfElements(),
				"total_elements", pageResult.getTotalElements()));
	}

	/**
	 * Updates a specific region by its ID.
	 *
	 * @param id     the ID of the region to be updated
	 * @param region the updated region
	 * @return the response entity with the updated region
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Region region) {
		return ResponseEntity.ok().body(regionService.alter(id, region));
	}

	/**
	 * Deletes a specific region by its ID.
	 *
	 * @param id the ID of the region to be deleted
	 * @return the response entity with a success message
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		regionService.remove(id);
		return ResponseEntity.ok().body(Bundler.pack("message", "region deleted successfully"));
	}
}
