package com.example.bookings;

import com.example.bookings.componenet.Phone;
import com.example.bookings.controller.PhoneController;
import com.example.bookings.service.PhoneService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class PhoneControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PhoneService phoneService;

    @InjectMocks
    private PhoneController phoneController;

    Phone PHONE_1 = new Phone(1, "Samsung Galaxy S9", "no", "John", LocalDateTime.now());
    Phone PHONE_2 = new Phone(2, "Motorola Nexus 6", "yes", null, null);
    Phone PHONE_3 = new Phone(7, "Apple iPhone 12", "yes", null, null);
    Phone PHONE_4 = new Phone(8, "Nokia 3310", "no", "Alice", LocalDateTime.now());


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(phoneController).build();
    }

    @Test
    public void getAllPhones_success() throws Exception {
        List<Phone> phones = new ArrayList<>(Arrays.asList(PHONE_1, PHONE_2));
        Mockito.when(phoneService.getPhones()).thenReturn(phones);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/phone/allPhones")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].model", is("Samsung Galaxy S9")))
                .andExpect(jsonPath("$[0].bookedBy", is("John")))
                .andExpect(jsonPath("$[1].availability", is("yes")))
                .andExpect(jsonPath("$[1].model", is("Motorola Nexus 6")));
    }

    @Test
    public void getPhonesByAvailability_success() throws Exception {
        List<Phone> phones = new ArrayList<>(Arrays.asList(PHONE_1, PHONE_2, PHONE_3, PHONE_4));
        Mockito.when(phoneService.getAllPhonesByAvailability()).thenReturn(phones);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/phone/byAvailability")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[1].model", is("Motorola Nexus 6")))
                .andExpect(jsonPath("$[2].availability", is("yes")))
                .andExpect(jsonPath("$[1].availability", is("yes")))
                .andExpect(jsonPath("$[2].bookedAt", is(nullValue())));
    }

    @Test
    public void bookPhone_success() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/phone/bookPhone")
                        .content("  [{\n" +
                                "    \"id\": 1,\n" +
                                "    \"model\": \"Samsung Galaxy S9\",\n" +
                                "    \"availability\": \"yes\",\n" +
                                "    \"bookedBy\": \"John\",\n" +
                                "    \"bookedAt\": \"2024-07-28T11:00:02.144Z\"\n" +
                                "  }]")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void returnPhone_success() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/phone/returnPhone")
                        .content("  [{\n" +
                                "    \"id\": 1,\n" +
                                "    \"model\": \"Samsung Galaxy S9\",\n" +
                                "    \"availability\": \"yes\",\n" +
                                "    \"bookedBy\": \"John\",\n" +
                                "    \"bookedAt\": \"2024-07-28T11:00:02.144Z\"\n" +
                                "  }]")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
