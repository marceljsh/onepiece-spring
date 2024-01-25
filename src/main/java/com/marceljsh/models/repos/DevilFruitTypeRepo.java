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

package com.marceljsh.models.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.marceljsh.models.entities.DevilFruitType;

public interface DevilFruitTypeRepo extends CrudRepository<DevilFruitType, Long> {
	List<DevilFruitType> findByNameContains(String name);
}
