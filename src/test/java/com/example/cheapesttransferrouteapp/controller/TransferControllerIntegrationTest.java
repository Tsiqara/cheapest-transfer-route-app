package com.example.cheapesttransferrouteapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.cheapesttransferrouteapp.model.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.*;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class TransferControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testValidRequest() throws Exception {
        Transfer t1 = new Transfer(5, 10);
        Transfer t2 = new Transfer(10,20);
        TransferRequest request = new TransferRequest(15, 0, Arrays.asList(t1, t2));

        mockMvc.perform(post("/api/transfers/optimal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCost").value(30))
                .andExpect(jsonPath("$.totalWeight").value(15))
                .andExpect(jsonPath("$.selectedTransfers").isArray())
                .andExpect(jsonPath("$.selectedTransfers.length()").value(2));
    }

    @Test
    public void testValidRequestWithEmptyAvailableTransfers() throws Exception {
        TransferRequest request = new TransferRequest(15, 0, new ArrayList<>());

        mockMvc.perform(post("/api/transfers/optimal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCost").value(0))
                .andExpect(jsonPath("$.totalWeight").value(0))
                .andExpect(jsonPath("$.selectedTransfers").isArray())
                .andExpect(jsonPath("$.selectedTransfers.length()").value(0));
    }

    @Test
    public void testValidRequestWhenAllTransfersExceedWeightLimit() throws Exception {
        Transfer t1 = new Transfer(5, 10);
        Transfer t2 = new Transfer(10,20);
        Transfer t3 = new Transfer(3, 5);
        Transfer t4 = new Transfer(8,15);
        TransferRequest request = new TransferRequest(2, 0, Arrays.asList(t1, t2, t3, t4));

        mockMvc.perform(post("/api/transfers/optimal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalCost").value(0))
                .andExpect(jsonPath("$.totalWeight").value(0))
                .andExpect(jsonPath("$.selectedTransfers").isArray())
                .andExpect(jsonPath("$.selectedTransfers.length()").value(0));
    }

    @Test
    void testInvalidRequest() throws Exception {
        mockMvc.perform(post("/api/transfers/optimal")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"maxWeight\": \"json\"}"))
                .andExpect(status().isBadRequest());
    }
}
