package com.skilldistillery.atlas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.atlas.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer>{

}
