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

package com.marceljsh.model.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.marceljsh.model.entity.DevilFruit;

public interface DevilFruitRepo extends JpaRepository<DevilFruit, Long> {
	// List<DevilFruit> findByNameContains(String keyword);

	@Query("SELECT df FROM DevilFruit df WHERE df.name LIKE %:keyword% OR df.englishName LIKE %:keyword% OR df.devilFruitType.name LIKE %:keyword%")
	Page<DevilFruit> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
