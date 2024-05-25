package com.myproject.codealpha.dto;

import com.myproject.codealpha.domain.AccountHolder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private long accountNumber;
    private AccountHolder accountHolder;
    private double balance;
}
