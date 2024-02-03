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
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.marceljsh.models.entities.Figure;
import com.marceljsh.models.repos.FigureRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FigureService {

	@Autowired
	private FigureRepo figureRepo;

	/**
	 * Saves a given entity. Use the returned instance for further operations as the
	 * save operation might have changed the
	 * entity instance completely.
	 *
	 * @param entity must not be {@literal null}.
	 * 
	 * @return the saved entity; will never be {@literal null}.
	 * 
	 * @throws IllegalArgumentException          in case the given {@literal entity}
	 *                                           is {@literal null}.
	 * 
	 * @throws OptimisticLockingFailureException when the entity uses optimistic
	 *                                           locking and has a version attribute
	 *                                           with
	 *                                           a different value from that found
	 *                                           in the persistence store. Also
	 *                                           thrown if the entity is assumed to
	 *                                           be
	 *                                           present but does not exist in the
	 *                                           database.
	 */
	public Figure save(Figure figure) {
		return figureRepo.save(figure);
	}

	/**
	 * Returns whether an entity with the given id exists.
	 *
	 * @param id must not be {@literal null}.
	 * 
	 * @return {@literal true} if an entity with the given id exists,
	 *         {@literal false} otherwise.
	 * 
	 * @throws IllegalArgumentException  if {@literal id} is {@literal null}.
	 * 
	 * @throws ResourceNotFoundException in case the given {@link target entity} is
	 *                                   {@literal null} a.k.a not found.
	 */
	public Figure findOne(Long id) {
		return figureRepo.findById(id).get();
	}

	public Iterable<Figure> find(String keyword) {
		if (keyword != null) {
			return figureRepo.findByKeyword(keyword);
		}
		return figureRepo.findAll();
	}
}
