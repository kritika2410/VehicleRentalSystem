package com.cg.rent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.rent.model.Enquiry;

/**
 * EnquiryDAO Interface
 * This interface have all methods which handles Enquiry
 * @author 15BW089AX
 *
 */
public interface EnquiryDAO extends JpaRepository<Enquiry, Integer> {

}
