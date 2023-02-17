package com.CustomerReward.Service;

import com.CustomerReward.Model.Customer;
import com.CustomerReward.Model.Reward;
import com.CustomerReward.Model.Transaction;
import com.CustomerReward.Repository.CustomerRepository;
import com.CustomerReward.Repository.TransactionRepository;

import java.util.List;
import java.util.Map;

public interface RewardService {
    Transaction createTransaction(Transaction transaction);

        Reward calculateRewardsByCustomer(Integer customerId);

}
