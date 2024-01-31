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
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marceljsh.models.dto.DevilFruitDTO;
import com.marceljsh.models.entities.DevilFruit;
import com.marceljsh.models.entities.DevilFruitType;
import com.marceljsh.services.DevilFruitService;
import com.marceljsh.services.DevilFruitTypeService;

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

	// * helper methods
	public DevilFruit convertToDevilFruit(DevilFruitDTO devilFruitDTO) {
		DevilFruit devilFruit = new DevilFruit();
		DevilFruitType devilFruitType = devilFruitTypeService.findOne(devilFruitDTO.getDevilFruitTypeId());

		devilFruit.setName(devilFruitDTO.getName());
		devilFruit.setEnglishName(devilFruitDTO.getEnglishName());
		devilFruit.setDevilFruitType(devilFruitType);

		return devilFruit;
	}

	/**
	 * Saves a given entity. Use the returned instance for further operations as the
	 * save operation might have changed the
	 * entity instance completely.
	 *
	 * @param entity must not be {@literal null}.
	 * @return the saved entity; will never be {@literal null}.
	 * @throws IllegalArgumentException          in case the given {@literal entity}
	 *                                           is {@literal null}.
	 * @throws OptimisticLockingFailureException when the entity uses optimistic
	 *                                           locking and has a version attribute
	 *                                           with
	 *                                           a different value from that found
	 *                                           in the persistence store. Also
	 *                                           thrown if the entity is assumed to
	 *                                           be
	 *                                           present but does not exist in the
	 *                                           database.
	 */
	@PostMapping
	public ResponseEntity<?> create(@RequestBody DevilFruitDTO devilFruitDTO) {
		DevilFruit devilFruit = convertToDevilFruit(devilFruitDTO);
		return ResponseEntity.ok().body(devilFruitService.save(devilFruit));
	}

	/**
	 * Creates a new DevilFruit.
	 *
	 * @param devilFruitDTO The DevilFruitDTO object containing the details of the
	 *                      DevilFruit to create.
	 * @param request       The HttpServletRequest object.
	 * 
	 * @return The ResponseEntity containing the created DevilFruit or an error
	 *         response.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> readOne(@PathVariable("id") Long id) {
		DevilFruit devilFruit = devilFruitService.findOne(id);
		return ResponseEntity.ok().body(devilFruit);
	}

	@GetMapping
	public ResponseEntity<?> read(@RequestParam(required = false) String keyword) {
		return ResponseEntity.ok().body(devilFruitService.find(keyword));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody DevilFruitDTO devilFruitDTO) {
		DevilFruit devilFruit = convertToDevilFruit(devilFruitDTO);
		DevilFruit result = devilFruitService.alter(id, devilFruit);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		devilFruitService.remove(id);
		return ResponseEntity.ok().body(Map.of("message", "devil fruit deleted successfully"));
	}
}
