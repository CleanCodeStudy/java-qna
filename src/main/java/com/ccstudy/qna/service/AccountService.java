package com.ccstudy.qna.service;

import com.ccstudy.qna.domain.Account;
import com.ccstudy.qna.dto.Account.AccountResDto;
import com.ccstudy.qna.dto.Account.AccountSaveReqDto;
import com.ccstudy.qna.dto.Account.AccountUpdateReqDto;
import com.ccstudy.qna.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private static void alreadyExistedAccount(Account x) {
        throw new RuntimeException("Already Existed User Id" + x.getUserId());
    }

    public List<AccountResDto> getAllAccounts(){
        return accountRepository.findAll().stream()
                .map(AccountResDto::new)
                .collect(Collectors.toList());
    }

    public Long saveAccount(AccountSaveReqDto saveReqDto) {
        accountRepository.findByUserId(saveReqDto.getUserId())
                .ifPresent(AccountService::alreadyExistedAccount);
        Account account = saveReqDto.toEntity();
        account = accountRepository.save(account);
        return account.getId();
    }

    public Account findAccountById(Long id){
        return accountRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public Long updateAccount(AccountUpdateReqDto updateReqDto) {
        Account account = accountRepository.findById(updateReqDto.getId())
                .orElseThrow(NoSuchElementException::new);
        update(account,updateReqDto);
        return account.getId();
    }

    private void update(Account account,AccountUpdateReqDto reqDto){
        account.setEmail(reqDto.getEmail());
        account.setName(reqDto.getName());
        account.setPassword(reqDto.getPassword());
    }


}
