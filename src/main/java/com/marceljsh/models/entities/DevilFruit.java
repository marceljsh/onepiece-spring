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

package com.marceljsh.models.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "devil_fruits")
public class DevilFruit implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 64, name = "name")
	private String name;

	@Column(length = 64, name = "english_name")
	private String englishName;

	@ManyToOne
	@JoinColumn(name = "devil_fruit_type_id", nullable = false)
	private DevilFruitType devilFruitType;

	public DevilFruit() {
		// 
	}

	public DevilFruit(Long id, String name, String englishName, DevilFruitType devilFruitType) {
		this.id = id;
		this.name = name;
		this.englishName = englishName;
		this.devilFruitType = devilFruitType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public DevilFruitType getDevilFruitType() {
		return devilFruitType;
	}

	public void setDevilFruitType(DevilFruitType devilFruitType) {
		this.devilFruitType = devilFruitType;
	}	
}
