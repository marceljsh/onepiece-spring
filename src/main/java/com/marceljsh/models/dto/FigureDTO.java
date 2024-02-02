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

package com.marceljsh.models.dto;

import java.util.Set;

// TODO: configure constructors
public class FigureDTO {
	private String birthName;
	private String epithet;
	private String birthDay;
	private Long originId;
	private Set<Long> formerResidenceIds;
	private Set<Long> currentResidenceIds;
	private String status;
	private Integer age;
	private Integer height;
	private Long bounty;
	private Boolean hasBusoshoku;
	private Boolean hasKenbunshoku;
	private Boolean hasHaoshoku;
	private Set<Long> devilFruits;
	private Set<Long> formerAffiliations;
	private Set<Long> currentAffiliations;
	private Set<Long> formerOccupations;
	private Set<Long> currentOccupations;
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
	public Long getOriginId() {
		return originId;
	}
	public void setOriginId(Long originId) {
		this.originId = originId;
	}
	public Set<Long> getFormerResidenceIds() {
		return formerResidenceIds;
	}
	public void setFormerResidenceIds(Set<Long> formerResidenceIds) {
		this.formerResidenceIds = formerResidenceIds;
	}
	public Set<Long> getCurrentResidenceIds() {
		return currentResidenceIds;
	}
	public void setCurrentResidenceIds(Set<Long> currentResidenceIds) {
		this.currentResidenceIds = currentResidenceIds;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
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
	public Set<Long> getDevilFruits() {
		return devilFruits;
	}
	public void setDevilFruits(Set<Long> devilFruits) {
		this.devilFruits = devilFruits;
	}
	public Set<Long> getFormerAffiliations() {
		return formerAffiliations;
	}
	public void setFormerAffiliations(Set<Long> formerAffiliations) {
		this.formerAffiliations = formerAffiliations;
	}
	public Set<Long> getCurrentAffiliations() {
		return currentAffiliations;
	}
	public void setCurrentAffiliations(Set<Long> currentAffiliations) {
		this.currentAffiliations = currentAffiliations;
	}
	public Set<Long> getFormerOccupations() {
		return formerOccupations;
	}
	public void setFormerOccupations(Set<Long> formerOccupations) {
		this.formerOccupations = formerOccupations;
	}
	public Set<Long> getCurrentOccupations() {
		return currentOccupations;
	}
	public void setCurrentOccupations(Set<Long> currentOccupations) {
		this.currentOccupations = currentOccupations;
	}

	
}
