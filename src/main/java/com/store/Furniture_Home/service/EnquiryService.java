package com.store.Furniture_Home.Service;

import com.store.Furniture_Home.Entity.Enquiry;
import com.store.Furniture_Home.Repository.EnquiryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnquiryService {

    private final EnquiryRepository enquiryRepository;

    public EnquiryService(EnquiryRepository enquiryRepository) {
        this.enquiryRepository = enquiryRepository;
    }

    public List<Enquiry> getAllEnquiries() {
        return enquiryRepository.findAll();
    }

    public Enquiry saveEnquiry(Enquiry enquiry) {
        return enquiryRepository.save(enquiry);
    }
}
