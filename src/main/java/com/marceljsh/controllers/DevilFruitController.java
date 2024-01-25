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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marceljsh.models.entities.DevilFruit;
import com.marceljsh.services.DevilFruitService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/devil-fruits")
public class DevilFruitController {

	@Autowired
	private DevilFruitService devilFruitService;

	@PostMapping
	public DevilFruit create(@RequestBody DevilFruit devilFruit) {
		return devilFruitService.save(devilFruit);
	}

	@GetMapping("/{id}")
	public DevilFruit findOne(@PathVariable("id") Long id) {
		return devilFruitService.findOne(id);
	}

	@GetMapping
	public Iterable<DevilFruit> find(@RequestParam(required = false) String keyword) {
		return devilFruitService.find(keyword);
	}
}
