package com.example.trade.controller;

import com.example.trade.exception.NotFoundException;
import com.example.trade.model.Box;
import com.example.trade.model.Customer;
import com.example.trade.model.Transaction;
import com.example.trade.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @PostMapping(value = "/addNewBox")
    public ResponseEntity<Object> addNewBox(@RequestBody Box box) {
        try {
            Box savedBox = tradeService.addNewBox(box);
            return ResponseEntity.ok(savedBox);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while saving the box");
        }
    }

    @PutMapping(value = "/updateBoxDetails")
    public ResponseEntity<Object> updateBoxDetails(@RequestBody Box box) {
        try {
            Box updatedBox = tradeService.updateBoxDetails(box);
            return ResponseEntity.ok(updatedBox);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while saving the box");
        }

    }

    @GetMapping(value = "/fetchPendingTransferDetails")
    public ResponseEntity<List<Transaction>> fetchPendingTransferDetails() {
        return ResponseEntity.status(HttpStatus.OK).body(tradeService.fetchPendingTransferDetails());
    }

    @PostMapping(value = "/addNewCustomer")
    public ResponseEntity<Object> addNewCustomer(@RequestBody Customer customer) {
        try {
            Customer savedCustomer = tradeService.addNewCustomer(customer);
            return ResponseEntity.ok(savedCustomer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while saving the customer");
        }
    }

    @GetMapping(value = "/fetchCustomerDetails")
    public ResponseEntity<List<Customer>> fetchCustomerDetails() {
        return ResponseEntity.status(HttpStatus.OK).body(tradeService.fetchCustomerDetails());
    }

    @PostMapping(value = "/transferAsset")
    public ResponseEntity<String> transferAsset(@RequestBody Transaction transaction) {
        return ResponseEntity.status(HttpStatus.OK).body(tradeService.transferAsset(transaction));
    }
}
