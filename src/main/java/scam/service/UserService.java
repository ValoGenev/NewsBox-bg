package scam.service;

import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import scam.dto.user.UserAllPropertiesDto;
import scam.dto.user.UserLoginRegisterDto;
import scam.dto.user.UserWithoutRelationDto;
import scam.entity.UserEntity;
import scam.exception.*;
import scam.model.UserRole;
import scam.repository.IUserRepository;
import scam.service.common.RandomAvatarColorGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static scam.model.UserRole.*;
import static scam.utils.Constants.*;

public class UserService implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RandomAvatarColorGenerator randomAvatarColorGenerator;

    @Autowired
    public UserService(IUserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder,RandomAvatarColorGenerator randomAvatarColorGenerator) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.randomAvatarColorGenerator=randomAvatarColorGenerator;
    }

    @Override
    public Set<UserWithoutRelationDto> findAll() {
        LOGGER.info(GET_ALL_USERS_MESSAGE);

        try {
            return userRepository
                    .findAll()
                    .stream()
                    .map(user -> modelMapper.map(user, UserWithoutRelationDto.class))
                    .collect(Collectors.toSet());
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }

    @Override
    public UserAllPropertiesDto findOne(String userName) {
        LOGGER.info(format(FIND_USER_BY_USERNAME_MESSAGE, userName));

        return modelMapper.map(findUser(userName), UserAllPropertiesDto.class);
    }

    @Override
    public void delete(String userName) {
        LOGGER.info(format(DELETE_USER_BY_USERNAME_MESSAGE, userName));

        try {
            userRepository.findByUsername(userName).ifPresent(userRepository::delete);
        } catch (DataIntegrityViolationException e) {
            LOGGER.error(CONFLICT_DELETE_MESSAGE);
            throw new ConflictException(CONFLICT_DELETE_MESSAGE);
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }

    @Override
    public UserAllPropertiesDto create(UserLoginRegisterDto user) {
        LOGGER.info(format(CREATE_USER_MESSAGE, user.getUsername()));

        assertNotExistingUsername(user.getUsername());

        UserEntity userToBeCreated = modelMapper.map(user,UserEntity.class);

        userToBeCreated.setAvatarColor(randomAvatarColorGenerator.getRandomColor());
        userToBeCreated.setRole(USER);
        userToBeCreated.setPassword(passwordEncoder.encode(user.getPassword()));
        userToBeCreated.setComments(new HashSet<>());
        userToBeCreated.setPosts(new HashSet<>());

        return modelMapper.map(createUser(userToBeCreated), UserAllPropertiesDto.class);
    }

    @Override
    public UserAllPropertiesDto update(UserLoginRegisterDto user, String userName) {
        LOGGER.info(format(UPDATE_USER_BY_USERNAME_MESSAGE, user.getUsername()));

        UserEntity userInDb = findUser(userName);

        assertEqualUsername(user.getUsername(), userInDb.getUsername());

        UserEntity userToBeUpdated = modelMapper.map(user,UserEntity.class);

        userToBeUpdated.setRole(userInDb.getRole());
        userToBeUpdated.setId(userInDb.getId());
        userToBeUpdated.setAvatarColor(userInDb.getAvatarColor());
        userToBeUpdated.setPassword(passwordEncoder.encode(user.getPassword()));
        userToBeUpdated.setComments(userInDb.getComments());
        userToBeUpdated.setPosts(userInDb.getPosts());

        return modelMapper.map(createUser(userToBeUpdated), UserAllPropertiesDto.class);
    }


    private UserEntity createUser(UserEntity user) {

        try {
            return userRepository.save(user);

        } catch (DataIntegrityViolationException e) {

            LOGGER.error(CONFLICT_CREATE_MESSAGE);
            throw new AlreadyExistingResourceException(EXISTING_RESOURCE_MESSAGE);
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE, e);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }


    private UserEntity findUser(String userName) {
        try {
            return userRepository
                    .findByUsername(userName)
                    .orElseThrow(() -> new UserNotFoundException(format(USER_NOT_FOUND_MESSAGE, userName)));
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }


    private void assertNotExistingUsername(String username){
        if(userRepository.checkIfUsernameExists(username)){
            throw new AlreadyExistingUserNameException(format(EXISTING_USERNAME_MESSAGE,username));
        }
    }

    private void assertEqualEmail(String email1, String email2) {
        if(!email1.equals(email2)){
            throw new EmailNotEqualException(EMAIL_NOT_EQUAL_MESSAGE);
        }
    }

    private void assertEqualUsername(String username1, String username2) {
        if(!username1.equals(username2)){
            throw new UsernameNotEqualException(USERNAME_NOT_EQUAL_MESSAGE);
        }
    }
}
