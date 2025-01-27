package tech_cx.adi.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import tech_cx.adi.controller.CreateUserDto;
import tech_cx.adi.controller.UpdateUserDto;
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

  public Optional<User> getUserById(String userId) {
    var user = userRepository.findById(UUID.fromString(userId));
    return user;
  }

  public List<User> listUsers() {
    return userRepository.findAll();
  }

  public void deleteById(String userId) {

    try {
      
      var id = UUID.fromString(userId);
  
      var userExists = userRepository.existsById(id);
  
      if(userExists){
        userRepository.deleteById(id);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void updateUserById(String userId, UpdateUserDto updateUserDto) {
    var id = UUID.fromString(userId);
  
      var userEntity = userRepository.findById(id);
  
      if(userEntity.isPresent()){
        var user = userEntity.get();
  
        if(updateUserDto.username() != null){
          user.setUsername(updateUserDto.username());
        }

        if(updateUserDto.password() != null){
          user.setPassword(updateUserDto.password());
        }
  
        userRepository.save(user);
      }
  }

}
