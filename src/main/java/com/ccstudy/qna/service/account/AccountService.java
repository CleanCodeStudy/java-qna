package com.ccstudy.qna.service.account;

import com.ccstudy.qna.domain.account.Account;
import com.ccstudy.qna.domain.account.AccountRepository;
import com.ccstudy.qna.dto.account.AccountListResponseDto;
import com.ccstudy.qna.dto.account.AccountModifyRequestDto;
import com.ccstudy.qna.dto.account.AccountSaveRequestDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public Long saveAccount(AccountSaveRequestDto accountSaveRequestDto){
        return accountRepository.save(accountSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public Account findById(Long id){
        return accountRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);
    }

    @Transactional
    public List<AccountListResponseDto> findAll(){
        return accountRepository.findAll().stream()
                .map(AccountListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long updateAccount(AccountModifyRequestDto dto){
        Account findAccount = findById(dto.getId());
        findAccount.setFirstName(dto.getFirstName());
        findAccount.setLastName(dto.getLastName());
        findAccount.setPassword(dto.getAfterPassword());
        return findAccount.getId();
    }


}
