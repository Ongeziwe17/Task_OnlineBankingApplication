package com.myproject.codealpha.factory;

import com.myproject.codealpha.domain.Account;
import com.myproject.codealpha.domain.AccountHolder;
import com.myproject.codealpha.util.Helper;

public class AccountFactory {
    public static Account buildAccount(long accountNumber, AccountHolder accountHolder, double balance){
        if(Helper.isNullOrEmpty(accountNumber) || Helper.isNullOrEmpty(accountHolder) || Helper.isNullOrEmpty(balance))
            return null;
        return new Account.Builder().setAccountNumber(accountNumber).setAccountHolder(accountHolder).setBalance(balance).build();
    }
}
