package com.CustomerReward.Controller;

import com.CustomerReward.Model.Customer;
import com.CustomerReward.Model.Reward;
import com.CustomerReward.Model.Transaction;
import com.CustomerReward.Service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class RewardController {
    @Autowired
    private RewardService rewardService;


    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction){
        return rewardService.createTransaction(transaction);
    }

    @GetMapping("{customerId}")
    public Reward calculateRewardsByCustomer(@PathVariable Integer customerId){
        return rewardService.calculateRewardsByCustomer(customerId);
    }
}
