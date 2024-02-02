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

// TODO: configure constructors
@Entity
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBirthName() {
		return birthName;
	}

	public void setBirthName(String birthName) {
		this.birthName = birthName;
	}

	public String getEpithet() {
		return epithet;
	}

	public void setEpithet(String epithet) {
		this.epithet = epithet;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public Region getOrigin() {
		return origin;
	}

	public void setOrigin(Region origin) {
		this.origin = origin;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Long getBounty() {
		return bounty;
	}

	public void setBounty(Long bounty) {
		this.bounty = bounty;
	}

	public Boolean getHasBusoshoku() {
		return hasBusoshoku;
	}

	public void setHasBusoshoku(Boolean hasBusoshoku) {
		this.hasBusoshoku = hasBusoshoku;
	}

	public Boolean getHasKenbunshoku() {
		return hasKenbunshoku;
	}

	public void setHasKenbunshoku(Boolean hasKenbunshoku) {
		this.hasKenbunshoku = hasKenbunshoku;
	}

	public Boolean getHasHaoshoku() {
		return hasHaoshoku;
	}

	public void setHasHaoshoku(Boolean hasHaoshoku) {
		this.hasHaoshoku = hasHaoshoku;
	}

	public Set<DevilFruit> getDevilFruits() {
		return devilFruits;
	}

	public void setDevilFruits(Set<DevilFruit> devilFruits) {
		this.devilFruits = devilFruits;
	}

	public Set<Affiliation> getFormerAffiliations() {
		return formerAffiliations;
	}

	public void setFormerAffiliations(Set<Affiliation> formerAffiliations) {
		this.formerAffiliations = formerAffiliations;
	}

	public Set<Affiliation> getCurrentAffiliations() {
		return currentAffiliations;
	}

	public void setCurrentAffiliations(Set<Affiliation> currentAffiliations) {
		this.currentAffiliations = currentAffiliations;
	}

	public Set<Occupation> getFormerOccupations() {
		return formerOccupations;
	}

	public void setFormerOccupations(Set<Occupation> formerOccupations) {
		this.formerOccupations = formerOccupations;
	}

	public Set<Occupation> getCurrentOccupations() {
		return currentOccupations;
	}

	public void setCurrentOccupations(Set<Occupation> currentOccupations) {
		this.currentOccupations = currentOccupations;
	}

	public Set<Region> getFormerResidences() {
		return formerResidences;
	}

	public void setFormerResidences(Set<Region> formerResidences) {
		this.formerResidences = formerResidences;
	}

	public Set<Region> getCurrentResidences() {
		return currentResidences;
	}

	public void setCurrentResidences(Set<Region> currentResidences) {
		this.currentResidences = currentResidences;
	}

	
}
