package com.skilldistillery.atlas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.atlas.entities.Photos;

public interface PhotoRepository extends JpaRepository<Photos, Integer>{
	public Photos queryById(int id);
	public List<Photos> findByVisitId(int id);
}
