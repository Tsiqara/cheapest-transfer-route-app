package com.example.cheapesttransferrouteapp.controller;

import com.example.cheapesttransferrouteapp.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.cheapesttransferrouteapp.service.TransferService;


@RestController
@RequestMapping("/api/transfers")
public class TransferController {
    private final TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/optimal")
    public ResponseEntity<TransferResponse> findOptimalRoute(@RequestBody TransferRequest request) {
        TransferResponse response = transferService.findOptimalRoute(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/boxes")
    public ResponseEntity<TransferResponse> findOptimalRouteWithBoxes(@RequestBody TransferRequest request){
        TransferResponse response = transferService.findOptimalTransfersForBoxes(request);
        return ResponseEntity.ok(response);
    }

}
