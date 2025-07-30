package cl.ey.demo.userservice.service;

import java.util.List;

import cl.ey.demo.userservice.entity.PhoneEntity;
import cl.ey.demo.userservice.entity.UserEntity;

public interface IPhoneService {
    
    void savePhone(List<PhoneEntity> phoneEntity,UserEntity user);
}
