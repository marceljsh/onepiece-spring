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

	@PostMapping
	public ResponseEntity<?> create(@RequestBody FigureDTO figureDTO) {
		Figure figure = convertToFigure(figureDTO);
		return ResponseEntity.ok(figureService.save(figure));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> readOne(@PathVariable("id") Long id) {
		return ResponseEntity.ok(figureService.findOne(id));
	}

	@GetMapping
	public ResponseEntity<?> read(@RequestParam(required = false) String keyword) {
		return ResponseEntity.ok(figureService.find(keyword));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody FigureDTO figureDTO) {
		Figure figure = convertToFigure(figureDTO);
		return ResponseEntity.ok(figureService.alter(id, figure));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		figureService.remove(id);
		return ResponseEntity.ok(Map.of("message", "figure deleted successfully"));
	}

	// * helper methods
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

	private <T> Set<T> convertIdSetToEntitySet(Set<Long> ids, Function<Long, T> fetchFunction) {
		return ids == null ? null : ids.stream().map(fetchFunction).collect(Collectors.toSet());
	}
}
