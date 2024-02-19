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

import com.marceljsh.model.entity.DevilFruit;
import com.marceljsh.model.repo.DevilFruitRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DevilFruitService {

	@Autowired
	private DevilFruitRepo devilFruitRepo;

	/**
	 * Saves a DevilFruit object.
	 *
	 * @param devilFruit the DevilFruit object to be saved
	 * @return the saved DevilFruit object
	 * @throws IllegalArgumentException if devilFruit is null
	 */
	public DevilFruit save(DevilFruit devilFruit) {
		if (devilFruit == null) {
			throw new IllegalArgumentException("devilFruit cannot be null");
		}
		return devilFruitRepo.save(devilFruit);
	}

	/**
	 * Retrieves a DevilFruit object by its ID.
	 *
	 * @param id the ID of the DevilFruit object to be retrieved
	 * @return the DevilFruit object with the specified ID
	 */
	@SuppressWarnings("null")
	public DevilFruit findOne(Long id) {
		return devilFruitRepo.findById(id).get();
	}

	/**
	 * Searches for DevilFruit objects based on a keyword and pageable information.
	 *
	 * @param keyword  the keyword to search for in DevilFruit objects
	 * @param pageable the pageable information for pagination
	 * @return a Page object containing the DevilFruit objects that match the search
	 *         criteria
	 */
	@SuppressWarnings("null")
	public Page<DevilFruit> find(String keyword, Pageable pageable) {
		if (keyword != null && !keyword.trim().isEmpty()) {
			return devilFruitRepo.findByKeyword(keyword, pageable);
		}
		return devilFruitRepo.findAll(pageable);
	}

	/**
	 * Updates a DevilFruit object with new values.
	 *
	 * @param id         the ID of the DevilFruit object to be updated
	 * @param devilFruit the DevilFruit object containing the new values
	 * @return the updated DevilFruit object
	 */
	@SuppressWarnings("null")
	public DevilFruit alter(Long id, DevilFruit devilFruit) {
		DevilFruit devilFruitToAlter = devilFruitRepo.findById(id).get();
		BeanUtils.copyProperties(devilFruit, devilFruitToAlter, "id");
		return devilFruitRepo.save(devilFruitToAlter);
	}

	/**
	 * Removes a DevilFruit object by its ID.
	 *
	 * @param id the ID of the DevilFruit object to be removed
	 */
	@SuppressWarnings("null")
	public void remove(Long id) {
		devilFruitRepo.deleteById(id);
	}
}
