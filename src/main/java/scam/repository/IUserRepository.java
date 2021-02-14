package scam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import scam.entity.UserEntity;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity,String> {
    Optional<UserEntity> findByUsername(String username);

    @Query("select case when count(u)> 0 then true else false end from UserEntity as u where lower(u.username) like lower(:username)")
    boolean checkIfUsernameExists(@Param("username") String username);

}
