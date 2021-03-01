package scam.service;

import scam.dto.comment.CommentAllPropertiesDto;
import scam.dto.comment.CommentWithUserDto;
import scam.dto.post.PostAllPropertiesDto;
import scam.dto.post.PostWithoutRelationDto;
import scam.dto.user.UserAllPropertiesDto;
import scam.model.Category;

import java.util.Set;

public interface IPostService {

    Set<PostAllPropertiesDto> findAll();

    PostAllPropertiesDto findOne(String id);

    void delete(String id);

    PostAllPropertiesDto create(PostAllPropertiesDto post);

    PostAllPropertiesDto update(PostAllPropertiesDto post, String id);

    Set<PostAllPropertiesDto> findAllWithCategory(Category category);

    Set<CommentAllPropertiesDto> getPostComments(String id);

    Set<PostAllPropertiesDto> getRandomPosts();

    PostAllPropertiesDto incrementView(String id);

    Set<PostAllPropertiesDto> getHeadingPosts();

    Set<PostAllPropertiesDto> createMultiplePosts(Set<PostAllPropertiesDto> posts);
}
