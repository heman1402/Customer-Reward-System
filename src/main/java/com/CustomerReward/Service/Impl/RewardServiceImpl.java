package com.CustomerReward.Service.Impl;

import com.CustomerReward.Model.Customer;
import com.CustomerReward.Model.Reward;
import com.CustomerReward.Model.Transaction;
import com.CustomerReward.Repository.CustomerRepository;
import com.CustomerReward.Repository.TransactionRepository;
import com.CustomerReward.Service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RewardServiceImpl implements RewardService {
    private static final double OVER_100_RATE = 2.0;
    private static final double OVER_50_RATE = 1.0;

    private Customer customer;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public RewardServiceImpl(CustomerRepository customerRepository, TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        double amount = transaction.getAmount();
        int points = 0;
        if (amount > 100) {
            points += (int) ((amount - 100) * OVER_100_RATE);
            points += (int) (50 * OVER_50_RATE);
        } else if (amount > 50 ) {
            points += (int) ((amount - 50) * OVER_50_RATE);
        }
        transaction.setPointsEarned(points);
        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public Reward calculateRewardsByCustomer(Integer customerId) {
        Reward reward = new Reward();
        List<Transaction> transactions = transactionRepository.findAllByCustomer_customerId(customerId);
        int recentMonthRewards = 0;
        int currentMonth = LocalDate.now().getMonthValue();
        int currentYear = LocalDate.now().getYear();
        int totalRewards = 0;
        Map<Integer, Integer> rewardsByMonth = new HashMap<>();
        for(Transaction transaction : transactions) {
            LocalDate transactionDate = transaction.getDate();
            int transactionMonth = transactionDate.getMonthValue();
            int transactionYear = transactionDate.getYear();
            if(currentMonth == transactionMonth && currentYear == transactionYear) {
                totalRewards = updateRewardsByMonthMapAndgetTotalRewards(totalRewards, rewardsByMonth, transaction, transactionMonth);
            } else if(transactionMonth == getLastMonth(currentMonth) && transactionYear == getYearForLastMonth(currentYear, currentMonth)) {
                totalRewards = updateRewardsByMonthMapAndgetTotalRewards(totalRewards, rewardsByMonth, transaction, transactionMonth);
            } else if(transactionMonth == getLastMonth(getLastMonth(currentMonth)) && transactionYear == getYearForLastMonth(getYearForLastMonth(currentYear, currentMonth), getLastMonth(currentMonth))) {
                totalRewards = updateRewardsByMonthMapAndgetTotalRewards(totalRewards, rewardsByMonth, transaction, transactionMonth);
            }
        }

        reward.setRewardsByMonth(rewardsByMonth);
        reward.setTotalRewards(totalRewards);
        return reward;
    }


    private int updateRewardsByMonthMapAndgetTotalRewards(int totalRewards, Map<Integer, Integer> rewardsByMonth, Transaction transaction, int transactionMonth) {
        if(rewardsByMonth.get(transactionMonth) == null) {
            rewardsByMonth.put(transactionMonth, transaction.getPointsEarned());
        } else {
            rewardsByMonth.put(transactionMonth, rewardsByMonth.get(transactionMonth) + transaction.getPointsEarned());
        }
        totalRewards = totalRewards + transaction.getPointsEarned();
        return totalRewards;
    }

    public Integer getLastMonth(int month) {
        if(month == 1) {
            return 12;
        } else {
            return --month;
        }
    }

    public Integer getYearForLastMonth(int year, int month) {
        if(month == 1) {
            return --year;
        } else {
            return year;
        }
    }
}


