package scam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import scam.entity.PictureEntity;

@Repository
public interface IPictureRepository extends JpaRepository<PictureEntity,String> {
}
