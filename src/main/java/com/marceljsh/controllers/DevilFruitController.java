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

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marceljsh.common.ErrorResponse;
import com.marceljsh.exceptions.ResourceNotFoundException;
import com.marceljsh.models.dto.DevilFruitDTO;
import com.marceljsh.models.entities.DevilFruit;
import com.marceljsh.models.entities.DevilFruitType;
import com.marceljsh.services.DevilFruitService;
import com.marceljsh.services.DevilFruitTypeService;

import jakarta.servlet.http.HttpServletRequest;

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

	// * API endpoints
	@PostMapping
	public DevilFruit create(@RequestBody DevilFruitDTO devilFruitDTO) {
		DevilFruit devilFruit = convertToDevilFruit(devilFruitDTO);
		return devilFruitService.save(devilFruit);
	}

	// @GetMapping("/{id}")
	// public DevilFruit findOne(@PathVariable("id") Long id) {
	// return devilFruitService.findOne(id);
	// }

	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable("id") String id, HttpServletRequest request) {
		try {
			if (!id.matches("\\d+")) {
				throw new IllegalArgumentException("id provided must be numeric");
			}

			Long numericId = Long.parseLong(id);
			DevilFruit devilFruit = devilFruitService.findOne(numericId);

			return ResponseEntity.ok().body(devilFruit);

		} catch (IllegalArgumentException e) {
			ErrorResponse errorResponse = new ErrorResponse(
					LocalDateTime.now(),
					HttpStatus.BAD_REQUEST.value(),
					e.getMessage(),
					request.getRequestURI());

			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		} catch (ResourceNotFoundException e) {
			ErrorResponse errorResponse = new ErrorResponse(
					LocalDateTime.now(),
					HttpStatus.NOT_FOUND.value(),
					e.getMessage(),
					request.getRequestURI());

			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public Iterable<DevilFruit> find(@RequestParam(required = false) String keyword) {
		return devilFruitService.find(keyword);
	}

	@PutMapping("/{id}")
	public DevilFruit alter(@PathVariable("id") Long id, @RequestBody DevilFruitDTO devilFruitDTO) {
		DevilFruit devilFruit = convertToDevilFruit(devilFruitDTO);
		return devilFruitService.alter(id, devilFruit);
	}
}
