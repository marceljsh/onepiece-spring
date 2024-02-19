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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marceljsh.helper.Bundler;
import com.marceljsh.model.dto.DevilFruitDTO;
import com.marceljsh.model.entity.DevilFruit;
import com.marceljsh.model.entity.DevilFruitType;
import com.marceljsh.service.DevilFruitService;
import com.marceljsh.service.DevilFruitTypeService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/devil-fruits")
public class DevilFruitController {

	@Autowired
	private DevilFruitService devilFruitService;

	@Autowired
	private DevilFruitTypeService devilFruitTypeService;

	/**
	 * Create a new Devil Fruit.
	 *
	 * @param devilFruitDTO The DevilFruitDTO object containing the details of the Devil Fruit.
	 * @return The ResponseEntity with the created Devil Fruit.
	 */
	@PostMapping
	public ResponseEntity<?> create(@RequestBody DevilFruitDTO devilFruitDTO) {
		DevilFruit devilFruit = convertToDevilFruit(devilFruitDTO);
		return ResponseEntity.ok().body(devilFruitService.save(devilFruit));
	}

	/**
	 * Get a Devil Fruit by its ID.
	 *
	 * @param id The ID of the Devil Fruit.
	 * @return The ResponseEntity with the Devil Fruit.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> readOne(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(devilFruitService.findOne(id));
	}

	/**
	 * Get a list of Devil Fruits.
	 *
	 * @param keyword The keyword to search for Devil Fruits (optional).
	 * @param page The page number (default: 1).
	 * @param size The page size (default: 10).
	 * @return The ResponseEntity with the list of Devil Fruits.
	 */
	@GetMapping
	public ResponseEntity<?> read(@RequestParam(required = false) String keyword, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
		Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
		Page<DevilFruit> pageResult = devilFruitService.find(keyword, pageable);

		return ResponseEntity.ok(Bundler.pack(
			"devil_fruits", pageResult.getContent(),
			"page_size", pageResult.getSize(),
			"current_page", pageResult.getNumber() + 1,
			"total_pages", pageResult.getTotalPages(),
			"length", pageResult.getNumberOfElements(),
			"total_elements", pageResult.getTotalElements()
		));
	}

	/**
	 * Update a Devil Fruit by its ID.
	 *
	 * @param id The ID of the Devil Fruit.
	 * @param devilFruitDTO The DevilFruitDTO object containing the updated details of the Devil Fruit.
	 * @return The ResponseEntity with the updated Devil Fruit.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody DevilFruitDTO devilFruitDTO) {
		DevilFruit devilFruit = convertToDevilFruit(devilFruitDTO);
		return ResponseEntity.ok().body(devilFruitService.alter(id, devilFruit));
	}

	/**
	 * Delete a Devil Fruit by its ID.
	 *
	 * @param id The ID of the Devil Fruit.
	 * @return The ResponseEntity with the success message.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		devilFruitService.remove(id);
		return ResponseEntity.ok().body(Map.of("message", "devil fruit deleted successfully"));
	}

	/**
	 * Convert a DevilFruitDTO object to a DevilFruit object.
	 *
	 * @param devilFruitDTO The DevilFruitDTO object to convert.
	 * @return The converted DevilFruit object.
	 */
	public DevilFruit convertToDevilFruit(DevilFruitDTO devilFruitDTO) {
		DevilFruit devilFruit = new DevilFruit();
		DevilFruitType devilFruitType = devilFruitTypeService.findOne(devilFruitDTO.getDevilFruitTypeId());

		devilFruit.setName(devilFruitDTO.getName());
		devilFruit.setEnglishName(devilFruitDTO.getEnglishName());
		devilFruit.setDevilFruitType(devilFruitType);

		return devilFruit;
	}
}
