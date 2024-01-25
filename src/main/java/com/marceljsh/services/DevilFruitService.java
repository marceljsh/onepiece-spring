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
		return devilFruitRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("devil fruit not found"));
	}

	public Iterable<DevilFruit> find(String keyword) {
		if (keyword != null) {
			return devilFruitRepo.findByNameContains(keyword);
		} else {
			return devilFruitRepo.findAll();
		}
	}

	public DevilFruit alter(Long id, DevilFruit devilFruit) {
		DevilFruit existingDevilFruit = devilFruitRepo.findById(id).get();

		existingDevilFruit.setName(devilFruit.getName());
		existingDevilFruit.setEnglishName(devilFruit.getEnglishName());
		existingDevilFruit.setDevilFruitType(devilFruit.getDevilFruitType());

		return devilFruitRepo.save(existingDevilFruit);
	}

	public void remove(Long id) {
		devilFruitRepo.deleteById(id);
	}
}
