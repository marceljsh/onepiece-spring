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
 * along with this code.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.marceljsh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public DevilFruitType create(@RequestBody DevilFruitType devilFruitType) {
		return devilFruitTypeService.save(devilFruitType);
	}

	@GetMapping("/{id}")
	public DevilFruitType findOne(@PathVariable("id") Long id) {
		return devilFruitTypeService.findOne(id);
	}

	@GetMapping
	public Iterable<DevilFruitType> find(@RequestParam(required = false) String keyword) {
		return devilFruitTypeService.find(keyword);
	}
}
