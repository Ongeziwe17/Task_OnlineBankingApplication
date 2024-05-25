package com.myproject.codealpha.service;

import com.myproject.codealpha.dto.AccountHolderDTO;

import java.util.Set;

public interface AccountHolderService extends IService<AccountHolderDTO, Long> {
    Set<AccountHolderDTO> getAll();
}
