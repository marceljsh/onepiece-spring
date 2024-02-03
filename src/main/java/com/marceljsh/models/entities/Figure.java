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

package com.marceljsh.models.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@EqualsAndHashCode(of = "id")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "figures")
public class Figure implements Serializable {

	public enum Status {
		ALIVE, DECEASED, UNKNOWN
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 64, name = "birth_name")
	private String birthName;

	@Column(length = 32, name = "epithet")
	private String epithet;

	@Column(length = 16, name = "birth_day")
	private String birthDay;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "origin_id")
	private Region origin;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(length = 3, name = "age")
	private Integer age;

	@Column(length = 3, name = "height")
	private Integer height;

	@Column(length = 10, name = "bounty")
	private Long bounty;

	@Column(name = "has_busoshoku")
	private Boolean hasBusoshoku;

	@Column(name = "has_kenbunshoku")
	private Boolean hasKenbunshoku;

	@Column(name = "has_haoshoku")
	private Boolean hasHaoshoku;

	@ManyToMany
	@JoinTable(name = "figure_devil_fruits", joinColumns = @JoinColumn(name = "figure_id"), inverseJoinColumns = @JoinColumn(name = "devil_fruit_id"))
	private Set<DevilFruit> devilFruits = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "figure_former_affiliations", joinColumns = @JoinColumn(name = "figure_id"), inverseJoinColumns = @JoinColumn(name = "affiliation_id"))
	private Set<Affiliation> formerAffiliations = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "figure_current_affiliations", joinColumns = @JoinColumn(name = "figure_id"), inverseJoinColumns = @JoinColumn(name = "affiliation_id"))
	private Set<Affiliation> currentAffiliations = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "figure_former_occupations", joinColumns = @JoinColumn(name = "figure_id"), inverseJoinColumns = @JoinColumn(name = "occupation_id"))
	private Set<Occupation> formerOccupations = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "figure_current_occupations", joinColumns = @JoinColumn(name = "figure_id"), inverseJoinColumns = @JoinColumn(name = "occupation_id"))
	private Set<Occupation> currentOccupations = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "figure_former_residences", joinColumns = @JoinColumn(name = "figure_id"), inverseJoinColumns = @JoinColumn(name = "region_id"))
	private Set<Region> formerResidences = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "figure_current_residences", joinColumns = @JoinColumn(name = "figure_id"), inverseJoinColumns = @JoinColumn(name = "region_id"))
	private Set<Region> currentResidences = new HashSet<>();
}
