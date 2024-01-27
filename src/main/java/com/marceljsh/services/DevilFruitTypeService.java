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
 * along with this code. If not, see <http://www.gnu.org/licenses/>.
 */

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
		DevilFruitType existingDevilFruitType = devilFruitTypeRepo.findById(id).get();

		existingDevilFruitType.setName(devilFruitType.getName());

		return devilFruitTypeRepo.save(existingDevilFruitType);
	}

	public void remove(Long id) {
		devilFruitTypeRepo.deleteById(id);
	}
}
