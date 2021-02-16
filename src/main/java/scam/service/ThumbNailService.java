package scam.service;

import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import scam.dto.picture.PictureAllPropertiesDto;
import scam.dto.picture.PictureWithoutRelationDto;
import scam.dto.thumbnail.ThumbNailAllPropertiesDto;
import scam.dto.thumbnail.ThumbNailWithoutPropertiesDto;
import scam.entity.PictureEntity;
import scam.entity.ThumbNailEntity;
import scam.exception.AlreadyExistingResourceException;
import scam.exception.ConflictException;
import scam.exception.PictureNotFoundException;
import scam.exception.ThumbNailNotFoundException;
import scam.repository.IPictureRepository;
import scam.repository.IThumbNailRepository;

import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static scam.utils.Constants.*;
import static scam.utils.Constants.DATABASE_ERROR_MESSAGE;

public class ThumbNailService implements IThumbNailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThumbNailService.class);

    private final IThumbNailRepository thumbNailRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ThumbNailService(IThumbNailRepository thumbNailRepository,ModelMapper modelMapper) {
        this.thumbNailRepository=thumbNailRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<ThumbNailWithoutPropertiesDto> findAll() {
        LOGGER.info(GET_ALL_THUMBNAILS_MESSAGE);

        try {
            return thumbNailRepository
                    .findAll()
                    .stream()
                    .map(pic -> modelMapper.map(pic, ThumbNailWithoutPropertiesDto.class))
                    .collect(Collectors.toSet());
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }

    @Override
    public ThumbNailAllPropertiesDto findOne(String id) {
        LOGGER.info(format(FIND_THUMBNAIL_BY_ID_MESSAGE, id));

        return modelMapper.map(findThumbNail(id), ThumbNailAllPropertiesDto.class);
    }

    @Override
    public void delete(String id) {
        LOGGER.info(format(DELETE_THUMBNAIL_BY_ID_MESSAGE, id));

        try {
            thumbNailRepository.findById(id).ifPresent(thumbNailRepository::delete);
        } catch (DataIntegrityViolationException e) {
            LOGGER.error(CONFLICT_DELETE_MESSAGE);
            throw new ConflictException(CONFLICT_DELETE_MESSAGE);
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }

    @Override
    public ThumbNailAllPropertiesDto create(ThumbNailAllPropertiesDto thumbNail) {
        LOGGER.info(CREATE_THUMBNAIL_MESSAGE);

        ThumbNailEntity thumbNailToBeCreated = modelMapper.map(thumbNail,ThumbNailEntity.class);

        return modelMapper.map(createThumbNail(thumbNailToBeCreated), ThumbNailAllPropertiesDto.class);
    }

    @Override
    public ThumbNailAllPropertiesDto update(ThumbNailAllPropertiesDto thumbNail, String id) {
        LOGGER.info(format(UPDATE_PIC_BY_ID_MESSAGE, thumbNail.getId()));

        ThumbNailEntity thumbNailInDb = findThumbNail(id);

        thumbNail.setId(thumbNailInDb.getId());

        ThumbNailEntity thumbNailToBeUpdated = modelMapper.map(thumbNail,ThumbNailEntity.class);

        thumbNailToBeUpdated.setPost(thumbNailInDb.getPost());

        return modelMapper.map(createThumbNail(thumbNailToBeUpdated), ThumbNailAllPropertiesDto.class);
    }


    private ThumbNailEntity createThumbNail(ThumbNailEntity thumbNail) {

        try {
            return thumbNailRepository.save(thumbNail);

        } catch (DataIntegrityViolationException e) {

            LOGGER.error(CONFLICT_CREATE_MESSAGE);
            throw new AlreadyExistingResourceException(EXISTING_RESOURCE_MESSAGE);
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE, e);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }


    private ThumbNailEntity findThumbNail(String id) {
        try {
            return thumbNailRepository
                    .findById(id)
                    .orElseThrow(() -> new ThumbNailNotFoundException(format(THUMBNAIL_NOT_FOUND_EXCEPTION, id)));
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }
}
