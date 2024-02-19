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

import com.marceljsh.model.entity.Occupation;
import com.marceljsh.model.repo.OccupationRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OccupationService {

	@Autowired
	private OccupationRepo occupationRepo;

	/**
	 * Saves the given Occupation object.
	 *
	 * @param occupation the Occupation object to be saved
	 * @return the saved Occupation object
	 */
	@SuppressWarnings("null")
	public Occupation save(Occupation occupation) {
		return occupationRepo.save(occupation);
	}

	/**
	 * Retrieves an Occupation object by its ID.
	 *
	 * @param id the ID of the Occupation object to be retrieved
	 * @return the retrieved Occupation object
	 */
	@SuppressWarnings("null")
	public Occupation findOne(Long id) {
		return occupationRepo.findById(id).get();
	}

	/**
	 * Finds Occupation objects based on the provided keyword and pageable
	 * information.
	 *
	 * @param keyword  the keyword to search for in the Occupation names
	 * @param pageable the pageable information for pagination and sorting
	 * @return a Page object containing the found Occupation objects
	 */
	@SuppressWarnings("null")
	public Page<Occupation> find(String keyword, Pageable pageable) {
		if (keyword != null && !keyword.trim().isEmpty()) {
			return occupationRepo.findByNameContains(keyword, pageable);
		}
		return occupationRepo.findAll(pageable);
	}

	/**
	 * Alters an existing Occupation object with the provided ID and new Occupation
	 * object.
	 *
	 * @param id         the ID of the Occupation object to be altered
	 * @param occupation the new Occupation object with updated information
	 * @return the altered Occupation object
	 */
	@SuppressWarnings("null")
	public Occupation alter(Long id, Occupation occupation) {
		Occupation occupationToAlter = occupationRepo.findById(id).get();
		BeanUtils.copyProperties(occupation, occupationToAlter, "id");
		return occupationRepo.save(occupationToAlter);
	}

	/**
	 * Removes an Occupation object by its ID.
	 *
	 * @param id the ID of the Occupation object to be removed
	 */
	@SuppressWarnings("null")
	public void remove(Long id) {
		occupationRepo.deleteById(id);
	}
}
