package com.example.bookings.service;

import com.example.bookings.componenet.Phone;
import com.example.bookings.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {

    @Autowired
    PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public List<Phone> getPhones() {
        return phoneRepository.getPhones();
    }

    public List<Phone> getAllPhonesByAvailability() {
        return phoneRepository.getAllPhonesByAvailability();
    }

    public ResponseEntity<String> bookPhone(List<Phone> phone) {
        return phoneRepository.bookPhone(phone);
    }

    public ResponseEntity<String> returnPhone(List<Phone> phone) {
        return phoneRepository.returnPhone(phone);
    }
}
