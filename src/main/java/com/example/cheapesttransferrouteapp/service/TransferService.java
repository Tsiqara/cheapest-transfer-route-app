package com.example.cheapesttransferrouteapp.service;

import com.example.cheapesttransferrouteapp.model.*;
import org.springframework.stereotype.Service;
import java.util.*;

import static java.lang.Math.max;

@Service
public class TransferService {
    public TransferResponse findOptimalRoute(TransferRequest request) {
        if (request == null || request.getAvailableTransfers() == null) {
            throw new IllegalArgumentException("Invalid request parameters");
        }

        int maxWeight = request.getMaxWeight();
        List<Transfer> availableTransfers = request.getAvailableTransfers();

        List<Transfer> selectedTransfers = new ArrayList<>();
        int totalCost = 0;
        int totalWeight = 0;

        int n = availableTransfers.size();
        int[][] dp = new int[n + 1][maxWeight + 1];

        for (int i = 1; i <= n; i++) {
            Transfer current = availableTransfers.get(i - 1);
            for (int w = 0; w <= maxWeight; w++) {
                if (current.getWeight() > w) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    dp[i][w] = max(dp[i - 1][w],  current.getCost() + dp[i - 1][w - current.getWeight()]);
                }
            }
        }

        int w = maxWeight;

        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i-1][w]) {
                Transfer transfer = availableTransfers.get(i - 1);
                selectedTransfers.add(transfer);
                totalWeight += transfer.getWeight();
                totalCost += transfer.getCost();
                w -= transfer.getWeight();
            }
        }
        return new TransferResponse(selectedTransfers, totalCost, totalWeight);
    }


    public TransferResponse findOptimalTransfersForBoxes(TransferRequest request){
        if (request == null || request.getAvailableTransfers() == null) {
            throw new IllegalArgumentException("Invalid request parameters");
        }

        int maxWeight = request.getMaxWeight();
        int maxBoxWeight = request.getMaxBoxWeight();
        List<Transfer> availableTransfers = request.getAvailableTransfers();

        int totalWeight = 0;
        int totalCost = 0;

        List<List<Transfer> > boxedTransfers = new ArrayList<>();

        int maxWeightLastBox = maxWeight % maxBoxWeight;

        int n = maxWeight / maxBoxWeight;

        if(maxWeightLastBox != 0){
            n += 1;
        }

        for(int i = 0; i < n; i ++) {
            TransferRequest request1 = new TransferRequest(maxBoxWeight, 0, availableTransfers);

            if(i == n - 1){
                request1.setMaxWeight(maxWeightLastBox);
            }

            TransferResponse response = findOptimalRoute(request1);
            totalWeight += response.getTotalWeight();
            totalCost += response.getTotalCost();

            List<Transfer> chosenTransfers = response.getSelectedTransfers();
            boxedTransfers.add(chosenTransfers);

            availableTransfers.removeAll(chosenTransfers);
        }


        return new TransferResponse(totalCost, totalWeight, boxedTransfers);
    }
}
