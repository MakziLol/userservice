package cl.ey.demo.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.ey.demo.userservice.entity.PhoneEntity;
@Repository
public interface PhoneSerciceRepository extends JpaRepository<PhoneEntity, Long>{
    
}
