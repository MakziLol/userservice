package cl.ey.demo.userservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.ey.demo.userservice.entity.UserEntity;

@Repository
public interface UserServiceRepository extends JpaRepository<UserEntity, UUID>{

    Optional<UserEntity> findById(UUID id);
    void deleteById(UUID id);
    Optional<UserEntity> findByEmail(String email);
    UserEntity findByIsActive(Boolean isActive);
}