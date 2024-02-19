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

package com.marceljsh.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.marceljsh.model.entity.DevilFruitType;
import com.marceljsh.model.repo.DevilFruitTypeRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DevilFruitTypeService {

	@Autowired
	private DevilFruitTypeRepo devilFruitTypeRepo;

	/**
	 * Saves a DevilFruitType object.
	 *
	 * @param devilFruitType The DevilFruitType object to be saved.
	 * @return The saved DevilFruitType object.
	 */
	@SuppressWarnings("null")
	public DevilFruitType save(DevilFruitType devilFruitType) {
		return devilFruitTypeRepo.save(devilFruitType);
	}

	/**
	 * Finds a DevilFruitType object by its ID.
	 *
	 * @param id The ID of the DevilFruitType object to be found.
	 * @return The found DevilFruitType object.
	 */
	@SuppressWarnings("null")
	public DevilFruitType findOne(Long id) {
		return devilFruitTypeRepo.findById(id).get();
	}

	/**
	 * Finds DevilFruitType objects based on a keyword and pageable information.
	 *
	 * @param keyword  The keyword to search for in the DevilFruitType names.
	 * @param pageable The pageable information for pagination.
	 * @return A Page object containing the found DevilFruitType objects.
	 */
	@SuppressWarnings("null")
	public Page<DevilFruitType> find(String keyword, Pageable pageable) {
		if (keyword != null && !keyword.trim().isEmpty()) {
			return devilFruitTypeRepo.findByNameContains(keyword, pageable);
		}
		return devilFruitTypeRepo.findAll(pageable);
	}

	/**
	 * Alters a DevilFruitType object by updating its properties.
	 *
	 * @param id             The ID of the DevilFruitType object to be altered.
	 * @param devilFruitType The DevilFruitType object containing the updated
	 *                       properties.
	 * @return The altered DevilFruitType object.
	 */
	@SuppressWarnings("null")
	public DevilFruitType alter(Long id, DevilFruitType devilFruitType) {
		DevilFruitType devilFruitTypeToAlter = devilFruitTypeRepo.findById(id).get();
		BeanUtils.copyProperties(devilFruitType, devilFruitTypeToAlter, "id");
		return devilFruitTypeRepo.save(devilFruitTypeToAlter);
	}

	/**
	 * Removes a DevilFruitType object by its ID.
	 *
	 * @param id The ID of the DevilFruitType object to be removed.
	 */
	@SuppressWarnings("null")
	public void remove(Long id) {
		devilFruitTypeRepo.deleteById(id);
	}
}
