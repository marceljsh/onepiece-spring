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

import com.marceljsh.model.entity.Affiliation;
import com.marceljsh.model.repo.AffiliationRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AffiliationService {

	@Autowired
	private AffiliationRepo affiliationRepo;

	/**
	 * Saves an affiliation.
	 *
	 * @param affiliation The affiliation to be saved.
	 * @return The saved affiliation.
	 */
	@SuppressWarnings("null")
	public Affiliation save(Affiliation affiliation) {
		return affiliationRepo.save(affiliation);
	}

	/**
	 * Finds an affiliation by its ID.
	 *
	 * @param id The ID of the affiliation to be found.
	 * @return The found affiliation.
	 */
	@SuppressWarnings("null")
	public Affiliation findOne(Long id) {
		return affiliationRepo.findById(id).get();
	}

	/**
	 * Finds affiliations based on a keyword and pageable information.
	 *
	 * @param keyword  The keyword to search for in affiliation names.
	 * @param pageable The pageable information for pagination.
	 * @return A page of affiliations matching the keyword.
	 */
	@SuppressWarnings("null")
	public Page<Affiliation> find(String keyword, Pageable pageable) {
		if (keyword != null && !keyword.trim().isEmpty()) {
			return affiliationRepo.findByNameContains(keyword, pageable);
		}
		return affiliationRepo.findAll(pageable);
	}

	/**
	 * Alters an existing affiliation by updating its properties.
	 *
	 * @param id          The ID of the affiliation to be altered.
	 * @param affiliation The updated affiliation object.
	 * @return The altered affiliation.
	 */
	@SuppressWarnings("null")
	public Affiliation alter(Long id, Affiliation affiliation) {
		Affiliation affiliationToAlter = affiliationRepo.findById(id).get();
		BeanUtils.copyProperties(affiliation, affiliationToAlter, "id");
		return affiliationRepo.save(affiliationToAlter);
	}

	/**
	 * Removes an affiliation by its ID.
	 *
	 * @param id The ID of the affiliation to be removed.
	 */
	public void remove(Long id) {
		if (id != null) {
			affiliationRepo.deleteById(id);
		}
	}
}
