package com.example.cheapesttransferrouteapp.model;

import java.util.*;

public class TransferRequest {
    private int maxWeight;
    private int maxBoxWeight = 0;
    private List<Transfer> availableTransfers;

    public TransferRequest(int maxWeight, int maxBoxWeight, List<Transfer> availableTransfers) {
        this.maxWeight = maxWeight;
        this.maxBoxWeight = maxBoxWeight;
        this.availableTransfers = availableTransfers;
    }

//    public TransferRequest(int maxWeight, List<Transfer> availableTransfers) {
//        this.maxWeight = maxWeight;
//        this.availableTransfers = availableTransfers;
//    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getMaxBoxWeight() {
        return maxBoxWeight;
    }

    public void setMaxBoxWeight(int maxBoxWeight) {
        this.maxBoxWeight = maxBoxWeight;
    }

    public List<Transfer> getAvailableTransfers() {
        return new ArrayList<>(availableTransfers);
    }

    public void setAvailableTransfers(List<Transfer> availableTransfers) {
        this.availableTransfers = availableTransfers;
    }
}
