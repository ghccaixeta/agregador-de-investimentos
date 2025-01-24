package tech_cx.adi.repository;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import tech_cx.adi.controller.CreateUserDto;
import tech_cx.adi.entity.User;


@Service
public class UserService {


  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UUID createUser(CreateUserDto createUserDto) {
    var entity = new User();
    
    entity.setUsername(createUserDto.username());
    entity.setEmail(createUserDto.email());
    entity.setPassword(createUserDto.password());

    var user = userRepository.save(entity);

    return user.getUserId();
  }

}
