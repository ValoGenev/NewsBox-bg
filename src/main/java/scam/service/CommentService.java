package scam.service;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import scam.dto.comment.CommentAllPropertiesDto;
import scam.dto.comment.CommentWithoutRelationDto;
import scam.dto.picture.PictureAllPropertiesDto;
import scam.dto.picture.PictureWithoutRelationDto;
import scam.dto.post.PostAllPropertiesDto;
import scam.dto.user.UserAllPropertiesDto;
import scam.dto.user.UserWithoutRelationDto;
import scam.entity.CommentEntity;
import scam.entity.PictureEntity;
import scam.entity.PostEntity;
import scam.entity.UserEntity;
import scam.exception.AlreadyExistingResourceException;
import scam.exception.CommentNotFoundException;
import scam.exception.ConflictException;
import scam.exception.PictureNotFoundException;
import scam.exception.handlers.PostExceptionHandler;
import scam.repository.ICommentRepository;
import scam.service.common.RandomAvatarColorGenerator;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Objects.isNull;
import static scam.utils.Constants.*;

public class CommentService implements ICommentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentService.class);

    private final ICommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final IUserService userService;
    private final IPostService postService;
    private final RandomAvatarColorGenerator randomAvatarColorGenerator;

    @Autowired
    public CommentService(ICommentRepository commentRepository, ModelMapper modelMapper, IUserService userService, IPostService postService,RandomAvatarColorGenerator randomAvatarColorGenerator) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.postService = postService;
        this.randomAvatarColorGenerator=randomAvatarColorGenerator;
    }

    @Override
    public Set<CommentWithoutRelationDto> findAll() {
        LOGGER.info(GET_ALL_COMMENTS_MESSAGE);

        try {
            return commentRepository
                    .findAll()
                    .stream()
                    .map(pic -> modelMapper.map(pic, CommentWithoutRelationDto.class))
                    .collect(Collectors.toSet());
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }

    @Override
    public CommentAllPropertiesDto findOne(String id) {
        LOGGER.info(format(FIND_COMMENT_BY_ID_MESSAGE, id));

        return modelMapper.map(findComment(id), CommentAllPropertiesDto.class);
    }

    @Override
    public void delete(String id) {
        LOGGER.info(format(DELETE_COMMENT_BY_ID_MESSAGE, id));

        try {
            commentRepository.findById(id).ifPresent(commentRepository::delete);
        } catch (DataIntegrityViolationException e) {
            LOGGER.error(CONFLICT_DELETE_MESSAGE);
            throw new ConflictException(CONFLICT_DELETE_MESSAGE);
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }

    @Override
    public CommentAllPropertiesDto create(CommentAllPropertiesDto comment) {

        UserAllPropertiesDto userInDb;

        if(!isNull(comment.getUser())){
            LOGGER.info(format(CREATE_COMMENT_MESSAGE, comment.getUser().getUsername()));

            userInDb = userService.findOne(comment.getUser().getUsername());
            comment.setUser(modelMapper.map(userInDb, UserWithoutRelationDto.class));
            comment.setAuthorName(userInDb.getUsername());
            comment.setAvatarColor(userInDb.getAvatarColor());
        }else{
            LOGGER.info(format(CREATE_COMMENT_MESSAGE, comment.getAuthorName()));
            comment.setAvatarColor(randomAvatarColorGenerator.getRandomColor());
        }

        PostAllPropertiesDto postInDb = postService.findOne(comment.getPost().getId());

        CommentEntity commentToBeCreated = modelMapper.map(comment,CommentEntity.class);

        commentToBeCreated.setPostedOn(LocalDateTime.now());

       // commentToBeCreated.setUser(modelMapper.map(userInDb, UserEntity.class));
        commentToBeCreated.setPost(modelMapper.map(postInDb, PostEntity.class));

        return modelMapper.map(createComment(commentToBeCreated),CommentAllPropertiesDto.class);
    }

    @Override
    public CommentAllPropertiesDto update(CommentAllPropertiesDto comment, String id) {
        LOGGER.info(format(UPDATE_COMMENT_BY_ID_MESSAGE, comment.getId()));

        CommentEntity commentInDb = findComment(id);

        comment.setId(commentInDb.getId());

      //  UserAllPropertiesDto userInDb = userService.findOne(comment.getUser().getUsername());
        PostAllPropertiesDto postInDb = postService.findOne(comment.getPost().getId());

        CommentEntity commentToBeCreated = modelMapper.map(comment,CommentEntity.class);

       // commentToBeCreated.setUser(modelMapper.map(userInDb, UserEntity.class));
        commentToBeCreated.setPost(modelMapper.map(postInDb, PostEntity.class));

        return modelMapper.map(createComment(commentToBeCreated),CommentAllPropertiesDto.class);
    }

    private CommentEntity createComment(CommentEntity comment) {

        try {
            return commentRepository.save(comment);

        } catch (DataIntegrityViolationException e) {

            LOGGER.error(CONFLICT_CREATE_MESSAGE);
            throw new AlreadyExistingResourceException(EXISTING_RESOURCE_MESSAGE);
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE, e);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }


    private CommentEntity findComment(String id) {
        try {
            return commentRepository
                    .findById(id)
                    .orElseThrow(() -> new CommentNotFoundException(format(COMMENT_NOT_FOUND_EXCEPTION, id)));
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }
}
