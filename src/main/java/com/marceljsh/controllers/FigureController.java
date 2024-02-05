package com.marceljsh.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marceljsh.models.dto.FigureDTO;
import com.marceljsh.models.entities.Figure;
import com.marceljsh.services.AffiliationService;
import com.marceljsh.services.DevilFruitService;
import com.marceljsh.services.FigureService;
import com.marceljsh.services.OccupationService;
import com.marceljsh.services.RegionService;

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

	// * helper methods
	public Figure convertToFigure(FigureDTO figureDTO) {
		Figure figure = new Figure();

		BeanUtils.copyProperties(figureDTO, figure, "formerResidenceIds", "currentResidenceIds", "devilFruitIds",
				"formerAffiliationIds", "currentAffiliationIds", "formerOccupationIds",
				"currentOccupationIds");

		if (figureDTO.getOriginId() != null) {
			figure.setOrigin(regionService.findOne(figureDTO.getOriginId()));
		}

		if (figureDTO.getFormerResidenceIds() != null) {
			figureDTO.getFormerResidenceIds().forEach(id -> figure.getFormerResidences().add(regionService.findOne(id)));
		}

		if (figureDTO.getCurrentResidenceIds() != null) {
			figureDTO.getCurrentResidenceIds().forEach(id -> figure.getCurrentResidences().add(regionService.findOne(id)));
		}

		if (figureDTO.getDevilFruitIds() != null) {
			figureDTO.getDevilFruitIds().forEach(id -> figure.getDevilFruits().add(devilFruitService.findOne(id)));
		}

		if (figureDTO.getFormerAffiliationIds() != null) {
			figureDTO.getFormerAffiliationIds()
					.forEach(id -> figure.getFormerAffiliations().add(affiliationService.findOne(id)));
		}

		if (figureDTO.getCurrentAffiliationIds() != null) {
			figureDTO.getCurrentAffiliationIds()
					.forEach(id -> figure.getCurrentAffiliations().add(affiliationService.findOne(id)));
		}

		if (figureDTO.getFormerOccupationIds() != null) {
			figureDTO.getFormerOccupationIds()
					.forEach(id -> figure.getFormerOccupations().add(occupationService.findOne(id)));
		}

		if (figureDTO.getCurrentOccupationIds() != null) {
			figureDTO.getCurrentOccupationIds()
					.forEach(id -> figure.getCurrentOccupations().add(occupationService.findOne(id)));
		}

		return figure;
	}
}
