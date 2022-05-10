package com.example.backend.service;

import com.example.backend.config.service.AccountDetail;
import com.example.backend.dto.resquest.ChangePasswordRequestDto;
import com.example.backend.dto.resquest.RegisterRequestDto;
import com.example.backend.exception.HrmException;
import com.example.backend.mapper.AccountMapper;
import com.example.backend.model.Account;
import com.example.backend.repository.AccountRepo;
import com.example.backend.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    public void registerAccount(RegisterRequestDto request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new HrmException(HrmException.NOT_PASSWORD_MATCH);
        }
        accountRepo.findByUsernameIgnoreCase(request.getUsername())
                .ifPresent(tmp -> {
                    throw new HrmException(HrmException.USERNAME_EXISTS);
                });
        Account account = AccountMapper.INSTANCE.toAccount(request);

        account.setPassword(passwordEncoder.encode(account.getPassword()));

        accountRepo.save(account);
    }
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    public void changePassword(ChangePasswordRequestDto request) {
        if (!request.getNewPassword().equals(request.getConfirmNewPassword())) {
            throw new HrmException(HrmException.NOT_PASSWORD_MATCH);
        }
        AccountDetail accountDetail = SecurityUtils.getCurrentAccountOrElseThrow();
        Account account = accountRepo.findByUsernameIgnoreCase(accountDetail.getUsername()).get();

        if (!passwordEncoder.matches(request.getOldPassword(), account.getPassword())) {
            throw new HrmException(HrmException.INCORRECT_PASSWORD);
        }
        account.setPassword(passwordEncoder.encode(request.getNewPassword()));
    }
}
