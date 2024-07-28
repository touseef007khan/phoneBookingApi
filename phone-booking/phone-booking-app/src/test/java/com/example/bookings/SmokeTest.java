package com.example.bookings;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.bookings.controller.PhoneController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmokeTest {

    @Autowired
    private PhoneController controller;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}