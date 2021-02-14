package scam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import scam.entity.ThumbNailEntity;

@Repository
public interface IThumbNailRepository extends JpaRepository<ThumbNailEntity,String> {
}
