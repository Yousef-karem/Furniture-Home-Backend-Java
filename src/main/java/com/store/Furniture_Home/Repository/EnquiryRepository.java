package com.store.Furniture_Home.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.store.Furniture_Home.Entity.Enquiry;

public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {
}
