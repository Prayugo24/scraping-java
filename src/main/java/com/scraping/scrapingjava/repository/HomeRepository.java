package com.scraping.scrapingjava.repository;


import org.springframework.stereotype.Repository;

import com.scraping.scrapingjava.model.HomeEntity;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



@Repository
public interface HomeRepository extends JpaRepository<HomeEntity, UUID>{

	@Query("Select count(*) > 0 from HomeEntity where title=?1")
	boolean existsByTitle(String title);

}
