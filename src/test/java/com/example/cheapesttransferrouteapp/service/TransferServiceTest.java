package com.example.cheapesttransferrouteapp.service;

import com.example.cheapesttransferrouteapp.model.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransferServiceTest {
    private TransferService transferService;

    @BeforeEach
    public void setUp() {
        transferService = new TransferService();
    }

    @Test
    public void testChooseAll() {
        Transfer t1 = new Transfer(5, 10);
        Transfer t2 = new Transfer(10,20);
        TransferRequest request = new TransferRequest(15, Arrays.asList(t1, t2));

        TransferResponse response = transferService.findOptimalRoute(request);

        assertNotNull(response);
        assertEquals(30, response.getTotalCost());
        assertEquals(15, response.getTotalWeight());
        assertEquals(2, response.getSelectedTransfers().size());
    }

    @Test
    public void testValidRequest() {
        Transfer t1 = new Transfer(5, 10);
        Transfer t2 = new Transfer(10,20);
        Transfer t3 = new Transfer(3, 5);
        Transfer t4 = new Transfer(8,15);
        TransferRequest request = new TransferRequest(15, Arrays.asList(t1, t2, t3, t4));

        TransferResponse response = transferService.findOptimalRoute(request);

        assertNotNull(response);
        assertEquals(30, response.getTotalCost());
        assertTrue(response.getTotalWeight() <= 15);
        assertEquals(15, response.getTotalWeight());
        assertEquals(2, response.getSelectedTransfers().size());
    }

    @Test
    public void testEmptyAvailableTransfers() {
        TransferRequest request = new TransferRequest(20, new ArrayList<>());

        TransferResponse response = transferService.findOptimalRoute(request);

        assertNotNull(response);
        assertEquals(0, response.getTotalCost());
        assertEquals(0, response.getTotalWeight());
        assertTrue(response.getSelectedTransfers().isEmpty());
    }

    @Test
    public void testAllTransfersExceedWeightLimit() {
        Transfer t1 = new Transfer(5, 10);
        Transfer t2 = new Transfer(10,20);
        Transfer t3 = new Transfer(3, 5);
        Transfer t4 = new Transfer(8,15);
        TransferRequest request = new TransferRequest(2, Arrays.asList(t1, t2, t3, t4));

        TransferResponse response = transferService.findOptimalRoute(request);
        assertNotNull(response);
        assertEquals(0, response.getTotalCost());
        assertEquals(0, response.getTotalWeight());
        assertTrue(response.getSelectedTransfers().isEmpty());
    }

    @Test
    void whenNullRequest_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            transferService.findOptimalRoute(null);
        });
    }

}
