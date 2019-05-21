package com.atfarm.fcs.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.atfarm.fcs.model.FCStatistics;
import com.atfarm.fcs.model.Vegetation;

/**
 * @author udayakumar.rajan
 *
 */
@Repository
public interface VegetationRepository extends JpaRepository<Vegetation, Long> {

	@Query(name = "statistics", value = "select new com.atfarm.fcs.model.FCStatistics (min(vegetation0_.vegetation) as min, max(vegetation0_.vegetation) as max,"
			+ " avg(vegetation0_.vegetation) as avg) "
			+ "from Vegetation vegetation0_ where vegetation0_.occurrenceAt between ?1 and ?2")
	Optional<FCStatistics> findStatistics(Date from, Date to);

}