package scam.service;

import scam.dto.picture.PictureAllPropertiesDto;
import scam.dto.picture.PictureWithoutRelationDto;
import scam.dto.thumbnail.ThumbNailAllPropertiesDto;
import scam.dto.thumbnail.ThumbNailWithoutPropertiesDto;

import java.util.Set;

public interface IThumbNailService {

    Set<ThumbNailWithoutPropertiesDto> findAll();

    ThumbNailAllPropertiesDto findOne(String id);

    void delete(String id);

    ThumbNailAllPropertiesDto create(ThumbNailAllPropertiesDto thumbNail);

    ThumbNailAllPropertiesDto update(ThumbNailAllPropertiesDto thumbNail, String id);
}
