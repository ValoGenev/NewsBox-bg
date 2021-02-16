package scam.service;

import scam.dto.user.UserAllPropertiesDto;
import scam.dto.user.UserCreateDto;
import scam.dto.user.UserWithoutRelationDto;

import java.util.Set;

public interface IUserService {

    Set<UserWithoutRelationDto> findAll();

    UserAllPropertiesDto findOne(String userName);

    void delete(String userName);

    UserAllPropertiesDto create(UserCreateDto user);

    UserAllPropertiesDto update(UserCreateDto user, String userName);
}
