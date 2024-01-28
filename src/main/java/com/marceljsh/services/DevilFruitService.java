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
import com.marceljsh.models.entities.DevilFruit;
import com.marceljsh.models.repos.DevilFruitRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DevilFruitService {

	@Autowired
	private DevilFruitRepo devilFruitRepo;

	public DevilFruit save(DevilFruit devilFruit) {
		return devilFruitRepo.save(devilFruit);
	}

	public DevilFruit findOne(Long id) {
		return devilFruitRepo
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("devil fruit not found"));
	}

	public Iterable<DevilFruit> find(String keyword) {
		if (keyword != null) {
			return devilFruitRepo.findByKeyword(keyword);
		} else {
			return devilFruitRepo.findAll();
		}
	}

	public DevilFruit alter(Long id, DevilFruit devilFruit) {
		DevilFruit existingDevilFruit = devilFruitRepo
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("devil fruit not found"));

		existingDevilFruit.setName(devilFruit.getName());
		existingDevilFruit.setEnglishName(devilFruit.getEnglishName());
		existingDevilFruit.setDevilFruitType(devilFruit.getDevilFruitType());

		return devilFruitRepo.save(existingDevilFruit);
	}

	/**
	 * Deletes the entity with the given id.
	 * <p>
	 * If the entity is not found, it is silently ignored.
	 *
	 * @param id must not be {@literal null}.
	 * @throws IllegalArgumentException in case the given {@literal id} is
	 *                                  {@literal null}
	 */
	public void remove(Long id) {
		devilFruitRepo.deleteById(id);
	}
}
