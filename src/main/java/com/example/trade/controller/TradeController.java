package com.example.trade.controller;

import com.example.trade.model.Box;
import com.example.trade.model.Transaction;
import com.example.trade.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @PostMapping(value = "/addNewBox")
    public ResponseEntity<Box> addNewBox(@RequestBody Box box) {
        Box savedBoxDetails = tradeService.addBox(box);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBoxDetails);
    }

    @PutMapping(value = "/updateBoxDetails")
    public ResponseEntity<Box> updateBoxDetails(Box box) {
        Box updatedBoxDetails = tradeService.addBox(box);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedBoxDetails);
    }

    @GetMapping(value = "/fetchPendingTransferDetails")
    public ResponseEntity<List<Transaction>> fetchPendingTransferDetails() {
        return ResponseEntity.status(HttpStatus.OK).body(tradeService.fetchPendingTransferDetails());
    }
}
