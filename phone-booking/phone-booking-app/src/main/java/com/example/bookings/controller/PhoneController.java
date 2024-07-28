package com.example.bookings.controller;

import com.example.bookings.componenet.Phone;
import com.example.bookings.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Phone booking", description = "Phone testing management APIs")
@RestController
@RequestMapping(value = "/phone")
public class PhoneController {

    @Autowired
    PhoneService phoneService;

    @Operation(summary = "Retrieve all Phones", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Phone.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "There are no Phones available", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/allPhones")
    public ResponseEntity<List<Phone>> getAllPhones() {
        try {
            List<Phone> allPhones = phoneService.getPhones();
            if (allPhones.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(allPhones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Retrieve all available Phones", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = Phone.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "There are no Phones available", content = {
                    @Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/byAvailability")
    public ResponseEntity<List<Phone>> getAllPhonesByAvailability() {
        try {
            List<Phone> allPhones = phoneService.getAllPhonesByAvailability();
            if (allPhones.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(allPhones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Update a phone after booking", method = "PUT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/text")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})})
    @PutMapping("/bookPhone")
    public ResponseEntity<String> bookPhone(@RequestBody List<Phone> phone) {
        try {
            return phoneService.bookPhone(phone);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Update a phone after returning", method = "PUT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/text")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})})
    @PutMapping("/returnPhone")
    public ResponseEntity<String> returnPhone(@RequestBody List<Phone> phone) {
        try {
            return phoneService.returnPhone(phone);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
