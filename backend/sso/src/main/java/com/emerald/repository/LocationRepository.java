package com.emerald.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emerald.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer>{

}
