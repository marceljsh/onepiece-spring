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

package com.marceljsh.models.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.marceljsh.models.entities.DevilFruit;

public interface DevilFruitRepo extends CrudRepository<DevilFruit, Long> {
	List<DevilFruit> findByNameContains(String name);

	@Query("SELECT df FROM DevilFruit df WHERE df.name LIKE %:keyword% OR df.englishName LIKE %:keyword% OR df.devilFruitType.name LIKE %:keyword%")
	List<DevilFruit> findByKeyword(@Param("keyword") String keyword);
}
