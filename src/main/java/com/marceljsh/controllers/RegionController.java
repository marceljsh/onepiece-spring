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

import org.springframework.beans.factory.annotation.Autowired;
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

	@PostMapping
	public Region create(@RequestBody Region region) {
		return regionService.save(region);
	}

	@GetMapping("/{id}")
	public Region readOne(@PathVariable Long id) {
		return regionService.findOne(id);
	}

	@GetMapping
	public Iterable<Region> read(@RequestParam(required = false) String keyword) {
		return regionService.find(keyword);
	}

	@PutMapping("/{id}")
	public Region update(@PathVariable Long id, @RequestBody Region region) {
		return regionService.alter(id, region);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		regionService.remove(id);
	}
}
