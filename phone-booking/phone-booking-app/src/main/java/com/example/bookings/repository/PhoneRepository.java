package com.example.bookings.repository;

import com.example.bookings.componenet.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PhoneRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //Get all phones
    public List<Phone> getPhones() {
        String query_allPhones = "select * from phone";
        return jdbcTemplate.query(query_allPhones, new BeanPropertyRowMapper<>(Phone.class));
    }

    //Get all phones by availability
    public List<Phone> getAllPhonesByAvailability() {
        String query_phoneByAvailability = "select * from phone where availability='yes'";
        return jdbcTemplate.query(query_phoneByAvailability, new BeanPropertyRowMapper<>(Phone.class));
    }

    //Book phones for testing
    public ResponseEntity<String> bookPhone(List<Phone> phone) {

        String queryToCheck = "select * from phone where availability='yes' and id=%s";
        String queryToUpdate = "update phone set booked_by=?, booked_at=?, availability='no' where id=?";

        List<Integer> bookedPhones = new ArrayList<>();
        List<Integer> UnavailablePhones = new ArrayList<>();

        for (Phone p : phone) {
            try {
                jdbcTemplate.queryForObject(String.format(queryToCheck, p.getId()), new BeanPropertyRowMapper<>(Phone.class));
                jdbcTemplate.update(queryToUpdate, p.getBookedBy(), LocalDateTime.now(), p.getId());
                bookedPhones.add(p.getId());
            } catch (EmptyResultDataAccessException e) {
                UnavailablePhones.add(p.getId());
            }
        }
        return new ResponseEntity<>("These phones booked successfully:" + bookedPhones + "\nThese phones are unavailable:" + UnavailablePhones, HttpStatus.OK);
    }

    //Return tested phones
    public ResponseEntity<String> returnPhone(List<Phone> phone) {

        String queryToCheck = "select * from phone where availability='no' and id=%s";
        String queryToUpdate = "update phone set booked_by=null, booked_at=null, availability='yes' where id=?";

        List<Integer> returnedPhones = new ArrayList<>();
        List<Integer> UnavailablePhones = new ArrayList<>();

        for (Phone p : phone) {
            try {
                jdbcTemplate.queryForObject(String.format(queryToCheck, p.getId()), new BeanPropertyRowMapper<>(Phone.class));
                jdbcTemplate.update(queryToUpdate, p.getId());
                returnedPhones.add(p.getId());
            } catch (EmptyResultDataAccessException e) {
                UnavailablePhones.add(p.getId());
            }
        }
        return new ResponseEntity<>("These phones returned successfully:" + returnedPhones + "\nThese phones are unavailable:" + UnavailablePhones, HttpStatus.OK);
    }

}