package com.example.cheapesttransferrouteapp.model;

import java.util.ArrayList;
import java.util.List;

public class TransferResponse {
    private List<Transfer> selectedTransfers;
    private int totalCost;
    private int totalWeight;

    private List<List<Transfer> > boxedTransfers;

    public TransferResponse(List<Transfer> selectedTransfers, int totalCost, int totalWeight) {
        this.selectedTransfers = selectedTransfers;
        this.totalCost = totalCost;
        this.totalWeight = totalWeight;
        this.boxedTransfers = new ArrayList<>();
    }

    public TransferResponse(int totalCost, int totalWeight, List<List<Transfer>> boxedTransfers) {
        this.totalCost = totalCost;
        this.totalWeight = totalWeight;
        this.boxedTransfers = boxedTransfers;
        this.selectedTransfers = new ArrayList<>();
    }

    public List<Transfer> getSelectedTransfers() {
        return new ArrayList<>(selectedTransfers);
    }

    public void setSelectedTransfers(List<Transfer> selectedTransfers) {
        this.selectedTransfers = selectedTransfers;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public List<List<Transfer>> getBoxedTransfers() {
        return boxedTransfers;
    }

    public void setBoxedTransfers(List<List<Transfer>> boxedTransfers) {
        this.boxedTransfers = boxedTransfers;
    }
}
