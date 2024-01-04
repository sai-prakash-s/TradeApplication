package com.example.trade.service;

import com.example.trade.exception.NotFoundException;
import com.example.trade.model.*;
import com.example.trade.repository.BoxAuditRepository;
import com.example.trade.repository.BoxRepository;
import com.example.trade.repository.CustomerRepository;
import com.example.trade.repository.TransactionRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class TradeService {

    @Autowired
    private BoxRepository boxRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BoxAuditRepository boxAuditRepository;

    @PostConstruct
    public void initializeData() {

        if (customerRepository.count() == 0) {
            Customer initialCustomer = new Customer();
            initialCustomer.setCustomerName("Sanjeev");
            initialCustomer.setCustomerId(1L);
            customerRepository.save(initialCustomer);
        }
    }


    public Box addNewBox(Box box) {
        Customer fromCustomer = getExistingCustomer(box.getCustomerId());

        // Audit Box Creation details
        boxAuditRepository.save(new BoxAudit(box.getId(), box.getBoxName(), box.getBoxWeight(), box.getCustomerId(), BoxStatus.CREATED));

        return boxRepository.save(box);
    }

    public Box updateBoxDetails(Box updatedBox) throws NotFoundException {
        Long boxId = updatedBox.getId();

        Box existingBox = getExistingBox(boxId);
        updatedBox.setCustomerId(existingBox.getCustomerId());

        // Audit Box Update Details
        boxAuditRepository.save(new BoxAudit(updatedBox.getId(), updatedBox.getBoxName(), updatedBox.getBoxWeight(), updatedBox.getCustomerId(), BoxStatus.UPDATED));

        // Save the updated box
        return boxRepository.save(updatedBox);
    }

    private Box getExistingBox(Long boxId) throws NotFoundException {
        return boxRepository.findById(boxId)
                .orElseThrow(() -> new NotFoundException("Box with ID " + boxId + " not found"));
    }

    private Customer getExistingCustomer(Long customerId) throws NotFoundException {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer with ID " + customerId + " not found"));
    }

    public List<Transaction> fetchPendingTransferDetails() {
        return transactionRepository.findByTransactionType(TransactionType.PARTIAL_TRANSACTION);
    }

    public List<Customer> fetchCustomerDetails() {
        return customerRepository.findAll();
    }

    @Transactional
    public String transferAsset(Transaction transaction) {
        try {
            Box existingBox = getExistingBox(transaction.getBoxId());
            Customer toCustomer = getExistingCustomer(transaction.getToCustomerId());


            // Updating Transaction details
            transaction.setFromCustomerId(existingBox.getCustomerId());
            BigDecimal transferredQuantity = transaction.getTransferredQuantity();
            BigDecimal remainingQuantity = existingBox.getBoxWeight().subtract(transferredQuantity);

            if (remainingQuantity.compareTo(BigDecimal.ZERO) == 0) {
                transaction.setTransactionType(TransactionType.FULL_TRANSACTION);
                transaction.setComment("No comment");
            } else {
                transaction.setTransactionType(TransactionType.PARTIAL_TRANSACTION);
                transaction.setRemainingQuantity(remainingQuantity);

                // Updating Box details
                existingBox.setBoxWeight(transferredQuantity);
            }

            existingBox.setCustomerId(transaction.getToCustomerId());

            boxRepository.save(existingBox);
            transactionRepository.save(transaction);

            return "Asset transfer successful";
        } catch (NotFoundException e) {
            log.error("Box not found with ID: {}", transaction.getBoxId(), e);
            return "Asset transfer failed - " + e.getMessage();
        } catch (Exception e) {
            log.error("Exception occurred while transferring asset", e);
            return "Asset transfer failed";
        }
    }


    public Customer addNewCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
