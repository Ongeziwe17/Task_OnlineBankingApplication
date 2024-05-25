package com.myproject.codealpha.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Account {
    @Id
    private long accountNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AccountHolder_Id")
    private AccountHolder accountHolder;
    private double balance;

    protected Account(){}

    public Account(Builder builder) {
        this.accountNumber = builder.accountNumber;
        this.accountHolder = builder.accountHolder;
        this.balance = builder.balance;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Account account = (Account) object;
        return accountNumber == account.accountNumber && Double.compare(balance, account.balance) == 0 && Objects.equals(accountHolder, account.accountHolder);
    }
    @Override
    public int hashCode() { return Objects.hash(accountNumber, accountHolder, balance); }
    @Override
    public String toString() {
        return "AccountFactory{" +
                "accountNumber=" + accountNumber +
                ", accountHolder=" + accountHolder +
                ", balance=" + balance +
                '}';
    }

    public static class Builder{
        private long accountNumber;
        private AccountHolder accountHolder;
        private double balance;

        public Builder setAccountNumber(long accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }
        public Builder setAccountHolder(AccountHolder accountHolder) {
            this.accountHolder = accountHolder;
            return this;
        }
        public Builder setBalance(double balance) {
            this.balance = balance;
            return this;
        }

        public Builder copy(Account account){
            this.accountNumber = account.accountNumber;
            this.accountHolder = account.accountHolder;
            this.balance = account.balance;
            return this;
        }
        public Account build(){ return new Account(this); }
    }
}
