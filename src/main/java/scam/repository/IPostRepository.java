package scam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import scam.entity.PostEntity;

import javax.persistence.PostRemove;

@Repository
public interface IPostRepository extends JpaRepository<PostEntity,String> {
}
