package com.myproject.codealpha.repository;

import com.myproject.codealpha.domain.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderFactory extends JpaRepository<AccountHolder, Long> {
}
