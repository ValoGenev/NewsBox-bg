package scam.service;

import scam.dto.post.PostAllPropertiesDto;
import scam.dto.post.PostWithoutRelationDto;
import scam.dto.user.UserAllPropertiesDto;

import java.util.Set;

public interface IPostService {

    Set<PostWithoutRelationDto> findAll();

    PostAllPropertiesDto findOne(String id);

    void delete(String id);

    PostAllPropertiesDto create(PostAllPropertiesDto post);

    PostAllPropertiesDto update(PostAllPropertiesDto post, String id);
}
