package com.example.trade.service;

import com.example.trade.model.Box;
import com.example.trade.model.Transaction;
import com.example.trade.repository.BoxRepository;
import com.example.trade.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeService {

    @Autowired
    private BoxRepository boxRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    public Box addBox(Box box) {
        return boxRepository.save(box);
    }

    public List<Transaction> fetchPendingTransferDetails() {
        return transactionRepository.findAll();
    }

//    public void updateBoxDetails(Box box) {
//        return
//    }
}
