package scam.service;

import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import scam.dto.picture.PictureAllPropertiesDto;
import scam.dto.picture.PictureWithoutRelationDto;
import scam.dto.user.UserAllPropertiesDto;
import scam.dto.user.UserWithoutRelationDto;
import scam.entity.PictureEntity;
import scam.entity.UserEntity;
import scam.exception.*;
import scam.repository.IPictureRepository;
import scam.repository.IUserRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static scam.utils.Constants.*;

public class PictureService implements IPictureService{

    private static final Logger LOGGER = LoggerFactory.getLogger(PictureService.class);

    private final IPictureRepository pictureRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PictureService(IPictureRepository pictureRepository,ModelMapper modelMapper) {
        this.pictureRepository=pictureRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<PictureWithoutRelationDto> findAll() {
        LOGGER.info(GET_ALL_PIC_MESSAGE);

        try {
            return pictureRepository
                    .findAll()
                    .stream()
                    .map(pic -> modelMapper.map(pic, PictureWithoutRelationDto.class))
                    .collect(Collectors.toSet());
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }

    @Override
    public PictureAllPropertiesDto findOne(String id) {
        LOGGER.info(format(FIND_PIC_BY_ID_MESSAGE, id));

        return modelMapper.map(findPicture(id), PictureAllPropertiesDto.class);
    }

    @Override
    public void delete(String id) {
        LOGGER.info(format(DELETE_PIC_BY_ID_MESSAGE, id));

        try {
            pictureRepository.findById(id).ifPresent(pictureRepository::delete);
        } catch (DataIntegrityViolationException e) {
            LOGGER.error(CONFLICT_DELETE_MESSAGE);
            throw new ConflictException(CONFLICT_DELETE_MESSAGE);
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }

    @Override
    public PictureAllPropertiesDto create(PictureAllPropertiesDto pic) {
        LOGGER.info(CREATE_PIC_MESSAGE);

        PictureEntity pictureToBeCreated = modelMapper.map(pic,PictureEntity.class);

        return modelMapper.map(createPicture(pictureToBeCreated), PictureAllPropertiesDto.class);
    }

    @Override
    public PictureAllPropertiesDto update(PictureAllPropertiesDto pic, String id) {
        LOGGER.info(format(UPDATE_PIC_BY_ID_MESSAGE, pic.getId()));

        PictureEntity picInDb = findPicture(id);

        pic.setId(picInDb.getId());

        PictureEntity picToBeUpdated = modelMapper.map(pic,PictureEntity.class);

        return modelMapper.map(createPicture(picToBeUpdated), PictureAllPropertiesDto.class);
    }


    private PictureEntity createPicture(PictureEntity picture) {

        try {
            return pictureRepository.save(picture);

        } catch (DataIntegrityViolationException e) {

            LOGGER.error(CONFLICT_CREATE_MESSAGE);
            throw new AlreadyExistingResourceException(EXISTING_RESOURCE_MESSAGE);
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE, e);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }


    private PictureEntity findPicture(String id) {
        try {
            return pictureRepository
                    .findById(id)
                    .orElseThrow(() -> new PictureNotFoundException(format(PIC_NOT_FOUND_MESSAGE, id)));
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }

}
