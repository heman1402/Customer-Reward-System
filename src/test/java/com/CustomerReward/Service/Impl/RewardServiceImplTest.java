package com.CustomerReward.Service.Impl;

import com.CustomerReward.Model.Customer;
import com.CustomerReward.Model.Reward;
import com.CustomerReward.Model.Transaction;
import com.CustomerReward.Repository.CustomerRepository;
import com.CustomerReward.Repository.TransactionRepository;
import com.CustomerReward.Service.RewardService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class RewardServiceImplTest {

    private RewardService rewardService;
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void setup() {
        CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
        transactionRepository = Mockito.mock(TransactionRepository.class);
        rewardService = new RewardServiceImpl(customerRepository, transactionRepository);
    }

    @Test
    public void testCreateTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAmount(120.0);
        transaction.setDate(LocalDate.now());

        rewardService.createTransaction(transaction);

        Assertions.assertEquals(90, transaction.getPointsEarned());
        Mockito.verify(transactionRepository, Mockito.times(1)).save(transaction);
    }

    @Test
    public void testCalculateRewardsByCustomer() {
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        Transaction transaction1 = new Transaction();
        transaction1.setTransactionID(1);
        transaction1.setCustomer(customer);
        transaction1.setAmount(120.0);
        transaction1.setDate(LocalDate.of(2023, 1, 10));
        transaction1.setPointsEarned(90);
        Transaction transaction2 = new Transaction();
        transaction2.setTransactionID(2);
        transaction2.setCustomer(customer);
        transaction2.setAmount(80.0);
        transaction2.setDate(LocalDate.of(2023, 2, 5));
        transaction2.setPointsEarned(30);
        Transaction transaction3 = new Transaction();
        transaction3.setTransactionID(3);
        transaction3.setCustomer(customer);
        transaction3.setAmount(60.0);
        transaction3.setDate(LocalDate.of(2023, 2, 10));
        transaction3.setPointsEarned(10);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);

        Mockito.when(transactionRepository.findAllByCustomer_customerId(1)).thenReturn(transactions);

        Reward reward = rewardService.calculateRewardsByCustomer(1);

        Map<Integer, Integer> rewardsByMonth = reward.getRewardsByMonth();
        Assertions.assertEquals(130, reward.getTotalRewards());
        Assertions.assertEquals(90, rewardsByMonth.get(1));
        Assertions.assertEquals(40, rewardsByMonth.get(2));
        Assertions.assertNull(rewardsByMonth.get(3));
    }
}
