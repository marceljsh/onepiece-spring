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

import com.marceljsh.exception.ResourceNotFoundException;
import com.marceljsh.model.entity.Affiliation;
import com.marceljsh.model.repo.AffiliationRepo;

import jakarta.transaction.Transactional;

/**
 * Service class for managing Affiliation entities.
 */
@Service
@Transactional
public class AffiliationService {

	@Autowired
	private AffiliationRepo affiliationRepo;

	/**
	 * Saves an Affiliation entity.
	 *
	 * @param affiliation the Affiliation entity to save
	 * @return the saved Affiliation entity
	 */
	@SuppressWarnings("null")
	public Affiliation save(Affiliation affiliation) {
		return affiliationRepo.save(affiliation);
	}

	/**
	 * Retrieves an Affiliation entity by its id.
	 *
	 * @param id the id of the Affiliation entity
	 * @return the Affiliation entity with the given id, or Optional.empty() if none
	 *         found
	 * @throws IllegalArgumentException  if id is null
	 * @throws ResourceNotFoundException if the Affiliation entity is not found
	 */
	@SuppressWarnings("null")
	public Affiliation findOne(Long id) {
		return affiliationRepo.findById(id).get();
	}

	/**
	 * Returns a Page of Affiliation entities based on the given keyword and
	 * pageable.
	 *
	 * @param keyword  the keyword to search for in the Affiliation name
	 * @param pageable the pageable object for pagination
	 * @return a Page of Affiliation entities
	 * @throws IllegalArgumentException if keyword or pageable is null
	 */
	@SuppressWarnings("null")
	public Page<Affiliation> find(String keyword, Pageable pageable) {
		if (keyword != null && !keyword.trim().isEmpty()) {
			return affiliationRepo.findByNameContains(keyword, pageable);
		}
		return affiliationRepo.findAll(pageable);
	}

	/**
	 * Updates an existing Affiliation entity with the given id.
	 *
	 * @param id          the id of the Affiliation entity to update
	 * @param affiliation the updated Affiliation entity
	 * @return the updated Affiliation entity
	 */
	@SuppressWarnings("null")
	public Affiliation alter(Long id, Affiliation affiliation) {
		Affiliation affiliationToAlter = affiliationRepo.findById(id).get();
		BeanUtils.copyProperties(affiliation, affiliationToAlter, "id");
		return affiliationRepo.save(affiliationToAlter);
	}

	/**
	 * Removes an Affiliation entity by its id.
	 *
	 * @param id the id of the Affiliation entity to remove
	 */
	public void remove(Long id) {
		if (id != null) {
			affiliationRepo.deleteById(id);
		}
	}
}
