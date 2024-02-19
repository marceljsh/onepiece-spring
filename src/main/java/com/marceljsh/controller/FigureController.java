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
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
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
import com.marceljsh.model.dto.FigureDTO;
import com.marceljsh.model.entity.Figure;
import com.marceljsh.model.entity.Figure.Status;
import com.marceljsh.service.AffiliationService;
import com.marceljsh.service.DevilFruitService;
import com.marceljsh.service.FigureService;
import com.marceljsh.service.OccupationService;
import com.marceljsh.service.RegionService;

@RestController
@RequestMapping("/api/v1/figures")
public class FigureController {

	@Autowired
	private FigureService figureService;

	@Autowired
	private RegionService regionService;

	@Autowired
	private DevilFruitService devilFruitService;

	@Autowired
	private AffiliationService affiliationService;

	@Autowired
	private OccupationService occupationService;

	/**
	 * Create a new figure.
	 *
	 * @param figureDTO The figure data transfer object.
	 * @return The response entity with the created figure.
	 */
	@PostMapping
	public ResponseEntity<?> create(@RequestBody FigureDTO figureDTO) {
		Figure figure = convertToFigure(figureDTO);
		return ResponseEntity.ok(figureService.save(figure));
	}

	/**
	 * Get a figure by its ID.
	 *
	 * @param id The ID of the figure.
	 * @return The response entity with the requested figure.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> readOne(@PathVariable("id") Long id) {
		return ResponseEntity.ok(figureService.findOne(id));
	}

	/**
	 * Get a list of figures.
	 *
	 * @param keyword The keyword to search for in figure names.
	 * @param page    The page number.
	 * @param size    The number of figures per page.
	 * @return The response entity with the list of figures.
	 */
	@GetMapping
	public ResponseEntity<?> read(@RequestParam(required = false) String keyword,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
		Page<Figure> pageResult = figureService.find(keyword, pageable);

		return ResponseEntity.ok(Bundler.pack(
				"figures", pageResult.getContent(),
				"page_size", pageResult.getSize(),
				"current_page", pageResult.getNumber() + 1,
				"total_pages", pageResult.getTotalPages(),
				"length", pageResult.getNumberOfElements(),
				"total_elements", pageResult.getTotalElements()));
	}

	/**
	 * Update a figure by its ID.
	 *
	 * @param id        The ID of the figure.
	 * @param figureDTO The updated figure data transfer object.
	 * @return The response entity with the updated figure.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody FigureDTO figureDTO) {
		Figure figure = convertToFigure(figureDTO);
		return ResponseEntity.ok(figureService.alter(id, figure));
	}

	/**
	 * Delete a figure by its ID.
	 *
	 * @param id The ID of the figure.
	 * @return The response entity with a success message.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		figureService.remove(id);
		return ResponseEntity.ok(Map.of("message", "figure deleted successfully"));
	}

	/**
	 * Convert a figure data transfer object to a figure entity.
	 *
	 * @param figureDTO The figure data transfer object.
	 * @return The converted figure entity.
	 */
	@SuppressWarnings("null")
	public Figure convertToFigure(FigureDTO figureDTO) {
		Figure figure = new Figure();

		String[] ignoreProperties = {
				"status", "formerResidenceIds", "currentResidenceIds",
				"devilFruitIds", "formerAffiliationIds", "currentAffiliationIds",
				"formerOccupationIds", "currentOccupationIds"
		};
		BeanUtils.copyProperties(figureDTO, figure, ignoreProperties);

		if (figureDTO.getStatus() != null) {
			figure.setStatus(Status.valueOf(figureDTO.getStatus().toUpperCase()));
		}

		figure.setOrigin(
				figureDTO.getOriginId() == null ? null : regionService.findOne(figureDTO.getOriginId()));
		figure.setFormerResidences(
				convertIdSetToEntitySet(figureDTO.getFormerResidenceIds(), regionService::findOne));
		figure.setCurrentResidences(
				convertIdSetToEntitySet(figureDTO.getCurrentResidenceIds(), regionService::findOne));
		figure.setDevilFruits(
				convertIdSetToEntitySet(figureDTO.getDevilFruitIds(), devilFruitService::findOne));
		figure.setFormerAffiliations(
				convertIdSetToEntitySet(figureDTO.getFormerAffiliationIds(), affiliationService::findOne));
		figure.setCurrentAffiliations(
				convertIdSetToEntitySet(figureDTO.getCurrentAffiliationIds(), affiliationService::findOne));
		figure.setFormerOccupations(
				convertIdSetToEntitySet(figureDTO.getFormerOccupationIds(), occupationService::findOne));
		figure.setCurrentOccupations(
				convertIdSetToEntitySet(figureDTO.getCurrentOccupationIds(), occupationService::findOne));

		return figure;
	}

	// * helper method
	/**
	 * Convert a set of IDs to a set of entities using the provided fetch function.
	 *
	 * @param ids           The set of IDs.
	 * @param fetchFunction The function to fetch an entity by its ID.
	 * @param <T>           The type of the entity.
	 * @return The set of entities.
	 */
	private <T> Set<T> convertIdSetToEntitySet(Set<Long> ids, Function<Long, T> fetchFunction) {
		return ids == null ? null : ids.stream().map(fetchFunction).collect(Collectors.toSet());
	}
}
