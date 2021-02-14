package scam.service;

import scam.dto.comment.CommentAllPropertiesDto;
import scam.dto.comment.CommentWithoutRelationDto;
import scam.dto.picture.PictureAllPropertiesDto;
import scam.dto.picture.PictureWithoutRelationDto;

import java.util.Set;

public interface ICommentService {
    Set<CommentWithoutRelationDto> findAll();

    CommentAllPropertiesDto findOne(String id);

    void delete(String id);

    CommentAllPropertiesDto create(CommentAllPropertiesDto picture);

    CommentAllPropertiesDto update(CommentAllPropertiesDto picture, String id);
}
