package scam.service;

import scam.dto.picture.PictureAllPropertiesDto;
import scam.dto.picture.PictureWithoutRelationDto;
import scam.dto.post.PostAllPropertiesDto;
import scam.dto.post.PostWithoutRelationDto;

import java.util.Set;

public interface IPictureService {

    Set<PictureWithoutRelationDto> findAll();

    PictureAllPropertiesDto findOne(String id);

    void delete(String id);

    PictureAllPropertiesDto create(PictureAllPropertiesDto picture);

    PictureAllPropertiesDto update(PictureAllPropertiesDto picture, String id);
}
