/*
 * Copyright (©) 2024 Marcel Joshua (https://marceljsh.vercel.app)
 *
 * This code is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this code.  If not, see <http://www.gnu.org/licenses/>.
 */

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

	public void delete(Long id) {
		regionRepo.deleteById(id);
	}
}
