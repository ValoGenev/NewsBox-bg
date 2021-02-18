package scam.service;

import scam.dto.user.UserAllPropertiesDto;
import scam.dto.user.UserLoginRegisterDto;
import scam.dto.user.UserWithoutRelationDto;

import java.util.Set;

public interface IUserService {

    Set<UserWithoutRelationDto> findAll();

    UserAllPropertiesDto findOne(String userName);

    void delete(String userName);

    UserAllPropertiesDto create(UserLoginRegisterDto user);

    UserAllPropertiesDto update(UserLoginRegisterDto user, String userName);
}
