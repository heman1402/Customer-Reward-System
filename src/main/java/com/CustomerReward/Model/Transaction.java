package com.CustomerReward.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int transactionID;
    private LocalDate date = LocalDate.now();
    private double amount;
    private int pointsEarned;
    @ManyToOne
   @JoinColumn(name = "customerId")
    private Customer customer;

}
