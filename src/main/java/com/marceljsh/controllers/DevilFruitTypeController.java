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

	@PostMapping
	public ResponseEntity<?> create(@RequestBody DevilFruitType devilFruitType) {
		return ResponseEntity.ok().body(devilFruitTypeService.save(devilFruitType));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> readOne(@PathVariable("id") Long id) {
		DevilFruitType devilFruitType = devilFruitTypeService.findOne(id);
		return ResponseEntity.ok().body(devilFruitType);
	}

	@GetMapping
	public ResponseEntity<?> read(@RequestParam(required = false) String keyword) {
		Iterable<DevilFruitType> devilFruitTypes = devilFruitTypeService.find(keyword);
		return ResponseEntity.ok().body(devilFruitTypes);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody DevilFruitType devilFruitType) {
		return ResponseEntity.ok().body(devilFruitTypeService.alter(id, devilFruitType));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		devilFruitTypeService.remove(id);
		return ResponseEntity.ok().body(Map.of("message", "devil fruit type deleted successfully"));
	}
}
