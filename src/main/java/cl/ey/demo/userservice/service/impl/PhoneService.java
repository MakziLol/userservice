package cl.ey.demo.userservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.ey.demo.userservice.entity.PhoneEntity;
import cl.ey.demo.userservice.entity.UserEntity;
import cl.ey.demo.userservice.repository.PhoneSerciceRepository;
import cl.ey.demo.userservice.service.IPhoneService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhoneService implements IPhoneService {
     private final PhoneSerciceRepository phoneSerciceRepository;

     @Override
     public void savePhone(List<PhoneEntity> phoneEntity,UserEntity user) {
          phoneEntity.forEach(phone -> phone.setUsuario(user));
          phoneSerciceRepository.saveAll(phoneEntity);
     }

}
