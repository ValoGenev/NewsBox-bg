package scam.service;

import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import scam.dto.picture.PictureAllPropertiesDto;
import scam.dto.picture.PictureWithoutRelationDto;
import scam.dto.post.PostAllPropertiesDto;
import scam.dto.post.PostWithoutRelationDto;
import scam.dto.thumbnail.ThumbNailAllPropertiesDto;
import scam.dto.user.UserAllPropertiesDto;
import scam.dto.user.UserWithoutRelationDto;
import scam.entity.PictureEntity;
import scam.entity.PostEntity;
import scam.entity.ThumbNailEntity;
import scam.entity.UserEntity;
import scam.exception.AlreadyExistingResourceException;
import scam.exception.ConflictException;
import scam.exception.PostNotFoundException;
import scam.exception.UserNotFoundException;
import scam.repository.IPostRepository;
import scam.repository.IThumbNailRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static scam.utils.Constants.*;

public class PostService implements IPostService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostService.class);
    private final IPostRepository postRepository;
    private final IUserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    private IThumbNailService thumbNailService;

    @Autowired
    private IPictureService pictureService;


    @Autowired
    public PostService(IPostRepository postRepository, ModelMapper modelMapper, IUserService userService) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public Set<PostWithoutRelationDto> findAll() {
        LOGGER.info(GET_ALL_POSTS_MESSAGE);

        try {
            return postRepository
                    .findAll()
                    .stream()
                    .map(post -> modelMapper.map(post, PostWithoutRelationDto.class))
                    .collect(Collectors.toSet());
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }

    @Override
    public PostAllPropertiesDto findOne(String id) {
        LOGGER.info(format(FIND_POST_BY_ID_MESSAGE, id));

        return modelMapper.map(findPost(id), PostAllPropertiesDto.class);
    }

    @Override
    public void delete(String id) {
        LOGGER.info(format(DELETE_POST_BY_ID_MESSAGE, id));

        try {
            postRepository.findById(id).ifPresent(postRepository::delete);
        } catch (DataIntegrityViolationException e) {
            LOGGER.error(CONFLICT_DELETE_MESSAGE);
            throw new ConflictException(CONFLICT_DELETE_MESSAGE);
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }

    @Override
    @Transactional
    public PostAllPropertiesDto create(PostAllPropertiesDto post) {
        LOGGER.info(format(CREATE_POST_MESSAGE, post.getUser().getUsername()));

        PostEntity postToBeCreated = modelMapper.map(post, PostEntity.class);

        UserAllPropertiesDto userInDb = userService.findOne(post.getUser().getUsername());

        ThumbNailAllPropertiesDto thumbNailToBeCreated = modelMapper.map(post.getThumbNailPic(), ThumbNailAllPropertiesDto.class);
        ThumbNailAllPropertiesDto createdThumbNail = thumbNailService.create(thumbNailToBeCreated);

        postToBeCreated.setThumbNailPic(modelMapper.map(createdThumbNail, ThumbNailEntity.class));
        postToBeCreated.setUser(modelMapper.map(userInDb, UserEntity.class));
        postToBeCreated.setComments(new HashSet<>());
        postToBeCreated.setPostedOn(LocalDateTime.now());

        PostEntity postEntity = createPost(postToBeCreated);

        Set<PictureEntity> picturesToBeAdded = new HashSet<>();

        post.getPictures().forEach(pic->{
           PictureAllPropertiesDto picToBeCreated =  modelMapper.map(pic,PictureAllPropertiesDto.class);
           picToBeCreated.setPost(new PostWithoutRelationDto());
           picToBeCreated.getPost().setId(postEntity.getId());
           picturesToBeAdded.add(modelMapper.map(pictureService.create(picToBeCreated),PictureEntity.class));
        });

        postEntity.setPictures(picturesToBeAdded);

        return modelMapper.map(postEntity, PostAllPropertiesDto.class);
    }

    @Override
    @Transactional
    public PostAllPropertiesDto update(PostAllPropertiesDto post, String id) {

        PostEntity postInDb = findPost(id);

        PostEntity postToBeUpdated = modelMapper.map(post,PostEntity.class);
        postToBeUpdated.setPictures(postInDb.getPictures());

        postToBeUpdated.setId(postInDb.getId());

        ThumbNailAllPropertiesDto thumbNailToBeCreated = modelMapper.map(post.getThumbNailPic(), ThumbNailAllPropertiesDto.class);
        ThumbNailAllPropertiesDto updatedPicture = thumbNailService.update(thumbNailToBeCreated,thumbNailToBeCreated.getId());
        postToBeUpdated.setThumbNailPic(modelMapper.map(updatedPicture,ThumbNailEntity.class));

        PostEntity updatedPost = createPost(postToBeUpdated);

        updatedPost.getPictures().forEach(picInDb->{
            PictureWithoutRelationDto pictureWithoutRelationDto = modelMapper.map(picInDb,PictureWithoutRelationDto.class);
            if(!post.getPictures().contains(pictureWithoutRelationDto)){
                pictureService.delete(picInDb.getId());
            }
        });

        Set<PictureEntity> newPictures = new HashSet<>();

        post.getPictures().forEach(pic->{
            if(pic.getId()==null){
                PictureAllPropertiesDto picToBeCreated =  modelMapper.map(pic,PictureAllPropertiesDto.class);
                picToBeCreated.setPost(new PostWithoutRelationDto());
                picToBeCreated.getPost().setId(updatedPost.getId());
                newPictures.add(modelMapper.map(pictureService.create(picToBeCreated),PictureEntity.class));
            }
        });

        newPictures.addAll(postInDb.getPictures());

        updatedPost.setPictures(newPictures);

        return modelMapper.map(updatedPost,PostAllPropertiesDto.class);
    }

    private PostEntity createPost(PostEntity post) {

        try {
            return postRepository.save(post);

        } catch (DataIntegrityViolationException e) {
            LOGGER.error(CONFLICT_CREATE_MESSAGE);
            throw new AlreadyExistingResourceException(EXISTING_RESOURCE_MESSAGE);
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE, e);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }


    private PostEntity findPost(String id) {
        try {
            return postRepository
                    .findById(id)
                    .orElseThrow(() -> new PostNotFoundException(format(POST_NOT_FOUND_MESSAGE, id)));
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }
}
