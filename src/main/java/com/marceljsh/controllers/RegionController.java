/*
 * Copyright (©) 2024 Marcel Joshua (https://marceljsh.vercel.app)
 *
 * This code is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this code. If not, see <http://www.gnu.org/licenses/>.
 */

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
	public Region findOne(@PathVariable Long id) {
		return regionService.findOne(id);
	}

	@GetMapping
	public Iterable<Region> find(@RequestParam(required = false) String keyword) {
		return regionService.find(keyword);
	}

	@PutMapping("/{id}")
	public Region alter(@PathVariable Long id, @RequestBody Region region) {
		return regionService.alter(id, region);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		regionService.delete(id);
	}
}
