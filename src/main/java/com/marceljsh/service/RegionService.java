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

import com.marceljsh.model.entity.Region;
import com.marceljsh.model.repo.RegionRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegionService {

	@Autowired
	private RegionRepo regionRepo;

	/**
	 * Saves a region.
	 *
	 * @param region the region to be saved
	 * @return the saved region
	 */
	@SuppressWarnings("null")
	public Region save(Region region) {
		return regionRepo.save(region);
	}

	/**
	 * Finds a region by its ID.
	 *
	 * @param id the ID of the region to be found
	 * @return the found region
	 */
	@SuppressWarnings("null")
	public Region findOne(Long id) {
		return regionRepo.findById(id).get();
	}

	/**
	 * Finds regions based on a keyword and pageable information.
	 *
	 * @param keyword  the keyword to search for in region names
	 * @param pageable the pageable information for pagination
	 * @return a page of regions matching the keyword
	 */
	@SuppressWarnings("null")
	public Page<Region> find(String keyword, Pageable pageable) {
		if (keyword != null && !keyword.trim().isEmpty()) {
			return regionRepo.findByNameContains(keyword, pageable);
		}
		return regionRepo.findAll(pageable);
	}

	/**
	 * Alters a region by updating its properties.
	 *
	 * @param id     the ID of the region to be altered
	 * @param region the updated region object
	 * @return the altered region
	 */
	@SuppressWarnings("null")
	public Region alter(Long id, Region region) {
		Region regionToAlter = regionRepo.findById(id).get();
		BeanUtils.copyProperties(region, regionToAlter, "id");
		return regionRepo.save(regionToAlter);
	}

	/**
	 * Removes a region by its ID.
	 *
	 * @param id the ID of the region to be removed
	 */
	@SuppressWarnings("null")
	public void remove(Long id) {
		regionRepo.deleteById(id);
	}
}
