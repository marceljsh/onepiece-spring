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

package com.marceljsh.model.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FigureDTO {
	private String birthName;
	private String epithet;
	private String birthDay;
	private Long originId;
	private String status;
	private Integer age;
	private Integer height;
	private Long bounty;
	private Boolean hasBusoshoku;
	private Boolean hasKenbunshoku;
	private Boolean hasHaoshoku;
	private Set<Long> formerResidenceIds;
	private Set<Long> currentResidenceIds;
	private Set<Long> devilFruitIds;
	private Set<Long> formerAffiliationIds;
	private Set<Long> currentAffiliationIds;
	private Set<Long> formerOccupationIds;
	private Set<Long> currentOccupationIds;
}
