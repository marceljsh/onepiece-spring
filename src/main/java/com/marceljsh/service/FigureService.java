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

import com.marceljsh.model.entity.Figure;
import com.marceljsh.model.repo.FigureRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FigureService {

	@Autowired
	private FigureRepo figureRepo;

	/**
	 * Saves a Figure object.
	 *
	 * @param figure The Figure object to be saved.
	 * @return The saved Figure object.
	 */
	@SuppressWarnings("null")
	public Figure save(Figure figure) {
		return figureRepo.save(figure);
	}

	/**
	 * Retrieves a Figure object by its ID.
	 *
	 * @param id The ID of the Figure object to be retrieved.
	 * @return The retrieved Figure object.
	 */
	@SuppressWarnings("null")
	public Figure findOne(Long id) {
		return figureRepo.findById(id).get();
	}

	/**
	 * Finds a list of Figure objects based on a keyword and pageable information.
	 *
	 * @param keyword  The keyword to search for in the Figure objects.
	 * @param pageable The pageable information for pagination.
	 * @return A Page object containing the list of Figure objects.
	 */
	@SuppressWarnings("null")
	public Page<Figure> find(String keyword, Pageable pageable) {
		if (keyword != null) {
			return figureRepo.findByKeyword(keyword, pageable);
		}
		return figureRepo.findAll(pageable);
	}

	/**
	 * Alters a Figure object by updating its properties.
	 *
	 * @param id     The ID of the Figure object to be altered.
	 * @param figure The updated Figure object.
	 * @return The altered Figure object.
	 */
	@SuppressWarnings("null")
	public Figure alter(Long id, Figure figure) {
		Figure figureToAlter = figureRepo.findById(id).get();
		BeanUtils.copyProperties(figure, figureToAlter, "id");
		return figureRepo.save(figureToAlter);
	}

	/**
	 * Removes a Figure object by its ID.
	 *
	 * @param id The ID of the Figure object to be removed.
	 */
	@SuppressWarnings("null")
	public void remove(Long id) {
		figureRepo.deleteById(id);
	}
}
