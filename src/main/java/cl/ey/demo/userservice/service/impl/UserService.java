package cl.ey.demo.userservice.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;

import cl.ey.demo.userservice.dto.password.PasswordUpdateDto;
import cl.ey.demo.userservice.dto.phone.Phone;
import cl.ey.demo.userservice.entity.PhoneEntity;
import cl.ey.demo.userservice.entity.UserEntity;
import cl.ey.demo.userservice.repository.UserServiceRepository;
import cl.ey.demo.userservice.service.IUserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
   private final UserServiceRepository userServiceRepository;

   @Override
   public UserEntity save(UserEntity user, List<PhoneEntity> phones) {
      user.setPhones(phones);
      return userServiceRepository.save(user);
   }

   @Override
   public UserEntity get(UUID id) {
      return userServiceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
   }

   @Override
   public void delete(UUID id) {
      get(id);
      userServiceRepository.deleteById(id);
   }

   @Override
   public UserEntity addPhone(UUID id, List<Phone> phones) {
      UserEntity user = get(id);
      List<PhoneEntity> phoneEntities = phones.stream()
            .map(phoneDto -> PhoneEntity.builder()
                  .citycode(phoneDto.getCitycode())
                  .contrycode(phoneDto.getContrycode())
                  .number(phoneDto.getNumber())
                  .build())
            .toList();
      user.setPhones(phoneEntities);
      return userServiceRepository.save(user);
   }

   @Override
   public UserEntity updatePassword(UUID id, PasswordUpdateDto paswordDto) {
      UserEntity user = get(id);
      if (Objects.equals(user.getPassword(), paswordDto.getOldPassword())) {
         user.setPassword(paswordDto.getNewPassword());
      }
      return userServiceRepository.save(user);
   }

   @Override
   public UserEntity setUserInactive(UUID id) {
      UserEntity user = get(id);
      if (Objects.equals(false, user.isInactive())) {
         user.setInactive(true);
      }
      return userServiceRepository.save(user);
   }

}
