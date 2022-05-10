package com.example.backend.mapper;

import com.example.backend.config.service.AccountDetail;
import com.example.backend.dto.resquest.RegisterRequestDto;
import com.example.backend.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mappings({
            @Mapping(source = "authorityList", target = "authorities")
    })
    AccountDetail toAccountDetail(Account account, List<GrantedAuthority> authorityList);

    Account toAccount(RegisterRequestDto registerRequestDto);

}
