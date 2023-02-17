package com.CustomerReward.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long customerId;
    @Column(nullable = false)
    private String customerName;
    @Column(nullable = false)
    private String emailId;

}
