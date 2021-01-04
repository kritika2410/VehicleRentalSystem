package com.cg.rent.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.cg.rent.model.Booking;
/**
 * BookingDAO interface
 * This class handles all the booking related database operations.
 * @author 15BW089AX
 *
 */
public interface BookingDAO extends JpaRepository<Booking, Integer>{

	
	@Query("Select b from Booking b where b.BId =:bid ")
	List<Booking> fetchBookingById(int bid);
}
