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

package com.marceljsh.models.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.marceljsh.models.entities.DevilFruitType;

public interface DevilFruitTypeRepo extends CrudRepository<DevilFruitType, Long> {
	List<DevilFruitType> findByNameContains(String keyword);
}
