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

import com.marceljsh.models.entities.Region;
import com.marceljsh.models.repos.RegionRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegionService {

	@Autowired
	private RegionRepo regionRepo;

	public Region save(Region region) {
		return regionRepo.save(region);
	}

	public Region findOne(Long id) {
		return regionRepo.findById(id).get();
	}

	public Iterable<Region> find(String keyword) {
		if (keyword != null) {
			return regionRepo.findByNameContains(keyword);
		} else {
			return regionRepo.findAll();
		}
	}

	public Region alter(Long id, Region region) {
		Region existingRegion = regionRepo.findById(id).get();

		existingRegion.setName(region.getName());

		return regionRepo.save(existingRegion);
	}

	public void remove(Long id) {
		regionRepo.deleteById(id);
	}
}
