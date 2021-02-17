package scam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import scam.dto.post.PostAllPropertiesDto;
import scam.entity.PostEntity;
import scam.model.Category;

import javax.persistence.PostRemove;
import java.util.Set;

@Repository
public interface IPostRepository extends JpaRepository<PostEntity,String> {

    @Query("select p from PostEntity as p where p.category = :category")
    Set<PostEntity> findAllByCategory(@Param("category") Category category);

}
