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

package com.marceljsh.models.dto;

public class DevilFruitDTO {
	private String name;
	private String englishName;
	private Long devilFruitTypeId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public Long getDevilFruitTypeId() {
		return devilFruitTypeId;
	}

	public void setDevilFruitTypeId(Long devilFruitTypeId) {
		this.devilFruitTypeId = devilFruitTypeId;
	}
}
