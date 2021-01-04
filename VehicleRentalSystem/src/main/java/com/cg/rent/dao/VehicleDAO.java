package com.cg.rent.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.rent.model.Vehicle;

/**
 * VehicleDAO Interface
 * This interface have all methods managing Vehicle.
 * @author 15BW089AX
 *
 */
public interface VehicleDAO extends JpaRepository<Vehicle, Integer>{
	
	@Query("Select v from Vehicle v where v.Vbrand=:vbrand")
	List<Vehicle> findVehicleByBrand(String vbrand);
	
	@Query("Select v from Vehicle v where v.VType=:vtype")
	List<Vehicle> findVehicleByType(String vtype);
	
	
}
