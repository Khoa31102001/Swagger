package com.example.backend.config.service;


import com.example.backend.exception.HrmException;
import com.example.backend.mapper.AccountMapper;
import com.example.backend.model.Account;
import com.example.backend.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepo accountRepo;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findByUsername(username).orElseThrow(() -> new HrmException(HrmException.USER_NOT_FOUND));
        if (!account.getIs_activate()) {
            throw new UsernameNotFoundException("qua ngu");
        }
        List<GrantedAuthority> authorityList = account.getAccountRoles()
                .stream().map(tmp->new SimpleGrantedAuthority(tmp.getRole().getName()))
                .collect(Collectors.toList());
        return AccountMapper.INSTANCE.toAccountDetail(account,authorityList);
    }
}
