package scam.service;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import scam.dto.comment.CommentAllPropertiesDto;
import scam.dto.comment.CommentWithUserDto;
import scam.dto.picture.PictureAllPropertiesDto;
import scam.dto.picture.PictureWithIdHashCodeDto;
import scam.dto.picture.PictureWithoutRelationDto;
import scam.dto.post.PostAllPropertiesDto;
import scam.dto.post.PostWithoutRelationDto;
import scam.dto.thumbnail.ThumbNailAllPropertiesDto;
import scam.dto.user.UserAllPropertiesDto;
import scam.entity.PictureEntity;
import scam.entity.PostEntity;
import scam.entity.ThumbNailEntity;
import scam.entity.UserEntity;
import scam.exception.AlreadyExistingResourceException;
import scam.exception.ConflictException;
import scam.exception.PostNotFoundException;
import scam.model.Category;
import scam.repository.IPostRepository;
import scam.service.common.RandomAuthorNameGenerator;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.apache.commons.collections4.CollectionUtils.*;
import static scam.utils.Constants.*;

public class PostService implements IPostService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostService.class);
    private final IPostRepository postRepository;
    private final IUserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    private RandomAuthorNameGenerator randomAuthorNameGenerator;

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
    public Set<PostAllPropertiesDto> findAll() {
        LOGGER.info(GET_ALL_POSTS_MESSAGE);

        try {
            return postRepository
                    .findAll()
                    .stream()
                    .map(post -> modelMapper.map(post, PostAllPropertiesDto.class))
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
            Optional<PostEntity> postEntity  = postRepository.findById(id);

            postEntity.ifPresent(post -> {
                post.getPictures().forEach(pic->{
                    pictureService.delete(pic.getId());
                });
            });

            postRepository.findById(id).ifPresent(postRepository::delete);
            postEntity.ifPresent(s->thumbNailService.delete(s.getThumbNailPic().getId()));
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

        post.setPictures(new HashSet<>(emptyIfNull(post.getPictures())));

        post.setViews(new Random().nextInt(500-100)+100);

        PostEntity postToBeCreated = modelMapper.map(post, PostEntity.class);

        UserAllPropertiesDto userInDb = userService.findOne(post.getUser().getUsername());

        ThumbNailAllPropertiesDto thumbNailToBeCreated = modelMapper.map(post.getThumbNailPic(), ThumbNailAllPropertiesDto.class);
        thumbNailToBeCreated.setId(null);
        ThumbNailAllPropertiesDto createdThumbNail = thumbNailService.create(thumbNailToBeCreated);

        postToBeCreated.setThumbNailPic(modelMapper.map(createdThumbNail, ThumbNailEntity.class));
        postToBeCreated.setUser(modelMapper.map(userInDb, UserEntity.class));
        postToBeCreated.setComments(new HashSet<>());
        postToBeCreated.setPostedOn(LocalDateTime.now());
        postToBeCreated.setAuthorName(randomAuthorNameGenerator.getRandomAuthor());

        PostEntity postEntity = createPost(postToBeCreated);

        Set<PictureEntity> picturesToBeAdded = new HashSet<>();

        post.getPictures().forEach(pic->{
           PictureAllPropertiesDto picToBeCreated =  modelMapper.map(pic,PictureAllPropertiesDto.class);
           picToBeCreated.setPost(new PostWithoutRelationDto());
           picToBeCreated.getPost().setId(postEntity.getId());
           picToBeCreated.setId(null);
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

        postToBeUpdated.setId(postInDb.getId());
        postToBeUpdated.setPictures(postInDb.getPictures());

        ThumbNailAllPropertiesDto thumbNailToBeCreated = modelMapper.map(post.getThumbNailPic(), ThumbNailAllPropertiesDto.class);
        ThumbNailAllPropertiesDto updatedThumbNail = thumbNailService.update(thumbNailToBeCreated,thumbNailToBeCreated.getId());
        postToBeUpdated.setThumbNailPic(modelMapper.map(updatedThumbNail,ThumbNailEntity.class));

        PostEntity updatedPost = createPost(postToBeUpdated);

        updatedPost.getPictures().forEach(picInDb->{
            PictureWithoutRelationDto pictureWithoutRelationDto = modelMapper.map(picInDb,PictureWithoutRelationDto.class);

            Set<PictureWithIdHashCodeDto> test = post.getPictures().stream().map(p->modelMapper.map(p,PictureWithIdHashCodeDto.class)).collect(Collectors.toSet());

            if(!test.contains(modelMapper.map(pictureWithoutRelationDto,PictureWithIdHashCodeDto.class))){
                pictureService.delete(picInDb.getId());
                post.getPictures().remove(pictureWithoutRelationDto);
            }
        });

        Set<PictureEntity> newPictures = new HashSet<>();

        post.getPictures().forEach(pic->{
            if(pic.getId()==null){
                PictureAllPropertiesDto picToBeCreated =  modelMapper.map(pic,PictureAllPropertiesDto.class);
                picToBeCreated.setPost(new PostWithoutRelationDto());
                picToBeCreated.getPost().setId(updatedPost.getId());
                newPictures.add(modelMapper.map(pictureService.create(picToBeCreated),PictureEntity.class));
            }else {
                PictureAllPropertiesDto picture = pictureService.findOne(pic.getId());
                picture.setPictureUrl(pic.getPictureUrl());
                pictureService.update(picture,pic.getId());
                newPictures.add(modelMapper.map(pic,PictureEntity.class));
            }
        });

        updatedPost.setPictures(newPictures);

        return modelMapper.map(updatedPost,PostAllPropertiesDto.class);
    }

    @Override
    public Set<PostAllPropertiesDto> findAllWithCategory(Category category) {
        LOGGER.info(format(GET_ALL_POSTS_WITH_CATEGORY_MESSAGE,category));

        try {
            return postRepository
                    .findAllByCategory(category)
                    .stream()
                    .map(post -> modelMapper.map(post, PostAllPropertiesDto.class))
                    .collect(Collectors.toSet());
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }

    @Override
    public Set<CommentAllPropertiesDto> getPostComments(String id) {
        LOGGER.info(format(GET_POST_COMMENTS_MESSAGE,id));

        return findPost(id).getComments()
                .stream()
                .map(comment->modelMapper.map(comment, CommentAllPropertiesDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public PostAllPropertiesDto incrementView(String id) {
        LOGGER.info(format("INCREMENTING VIEWS ON POST WITH ID [%s]",id));

        PostEntity postInDb = findPost(id);
        postInDb.setViews(postInDb.getViews()+1);
        return modelMapper.map(createPost(postInDb),PostAllPropertiesDto.class);
    }

    @Override
    public Set<PostAllPropertiesDto> getRandomPosts() {
        LOGGER.info("GETTING RANDOM NUMBERS");

        Random random = new Random();

        List<PostAllPropertiesDto> posts =
                postRepository
                        .findAll()
                        .stream().map(post->modelMapper.map(post,PostAllPropertiesDto.class))
                .collect(Collectors.toList());

        List<PostAllPropertiesDto> randomPosts = new ArrayList<>();

        int listSize = posts.size();

        if(listSize==0) return new HashSet<>();

        int randomNumber = random.nextInt(listSize);

        List<Integer> existingNumber = new ArrayList<>();
        existingNumber.add(randomNumber);

        randomPosts.add(posts.get(randomNumber));

        for(int i=0;i<posts.size()-1;i++){
            if(i==6){
                break;
            }
            randomNumber = random.nextInt(listSize);
            while(existingNumber.contains(randomNumber)){
                randomNumber = random.nextInt(listSize);
            }
            randomPosts.add(posts.get(randomNumber));
        }


        return new HashSet<>(randomPosts);
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
