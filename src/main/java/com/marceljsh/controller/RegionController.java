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
	 * Get regions with optional filtering and pagination.
	 *
	 * @param keyword The keyword to filter regions by.
	 * @param page    The page number for pagination.
	 * @param size    The number of regions per page.
	 * @return ResponseEntity with the paginated regions.
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
		return ResponseEntity.ok().body(Bundler.pack("message", "region deleted successfully"));
	}
}
