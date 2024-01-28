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

package com.marceljsh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marceljsh.exceptions.ResourceNotFoundException;
import com.marceljsh.models.entities.DevilFruitType;
import com.marceljsh.models.repos.DevilFruitTypeRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DevilFruitTypeService {

	@Autowired
	private DevilFruitTypeRepo devilFruitTypeRepo;

	public DevilFruitType save(DevilFruitType devilFruitType) {
		return devilFruitTypeRepo.save(devilFruitType);
	}

	public DevilFruitType findOne(Long id) {
		return devilFruitTypeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("devil fruit type not found"));
	}

	public Iterable<DevilFruitType> find(String keyword) {
		if (keyword != null) {
			return devilFruitTypeRepo.findByNameContains(keyword);
		} else {
			return devilFruitTypeRepo.findAll();
		}
	}

	public DevilFruitType alter(Long id, DevilFruitType devilFruitType) {
		DevilFruitType existingDevilFruitType = devilFruitTypeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("devil fruit type not found"));

		existingDevilFruitType.setName(devilFruitType.getName());

		return devilFruitTypeRepo.save(existingDevilFruitType);
	}

	public void remove(Long id) {
		devilFruitTypeRepo.deleteById(id);
	}
}
