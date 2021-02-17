package scam.service;

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
}
