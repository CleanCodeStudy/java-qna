package com.ccstudy.qna.service.account;

import com.ccstudy.qna.domain.account.Account;
import com.ccstudy.qna.domain.account.AccountRepository;
import com.ccstudy.qna.dto.account.*;
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
    public AccountSessionDto loginAccount(AccountLoginRequestDto dto){
        //TODO 예외처리 하는거 다시 생각해보기
        Account findAccount = accountRepository.findAccountByEmail(dto.getEmail())
                .orElseThrow(IllegalStateException::new);

        if(findAccount.isNotEqualPassword(dto.getPassword())){
            throw new IllegalStateException("현재 비밀번호가 틀렸습니다.");
        }
        AccountSessionDto accountSessionDto = AccountSessionDto.createBuilder()
                .email(findAccount.getEmail())
                .firstName(findAccount.getFirstName())
                .lastName(findAccount.getLastName())
                .build();
        return accountSessionDto;
    }

    @Transactional
    public Long saveAccount(AccountSaveRequestDto accountSaveRequestDto){
        return accountRepository.save(accountSaveRequestDto.toEntity()).getId();
    }

    @Transactional
    public List<AccountListResponseDto> findAll(){
        return accountRepository.findAll().stream()
                .map(AccountListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);
    }

    @Transactional
    public Long updateAccount(AccountUpdateRequestDto dto, Long id) {
        Account findAccount = accountRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);

        if (findAccount.isNotEqualPassword(dto.getCurrentPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 잘못되었습니다");
        }
        findAccount.setFirstName(dto.getFirstName());
        findAccount.setLastName(dto.getLastName());
        findAccount.setPassword(dto.getAfterPassword());
        return findAccount.getId();
    }


}


/*
transaction option => Propagation.REQUIRES_NEW
새로운 트랜잭션탐
 */