package scam.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import scam.dto.post.PostAllPropertiesDto;
import scam.entity.PostEntity;
import scam.model.Category;

import javax.persistence.PostRemove;
import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface IPostRepository extends JpaRepository<PostEntity,String> {

    @Query("select p from PostEntity as p inner join p.categories as c where c= :category")
    Set<PostEntity> findAllByCategory(@Param("category") Category category);


    Set<PostEntity> getAllByPostedOnAfterOrderByPostedOnAsc(LocalDateTime twoDaysAgo);

    @Query("select p from PostEntity as p where p.title like %:name%")
    Page<PostEntity> findAllPageable(String name, Pageable pageable);



}
