package tech_cx.adi.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tech_cx.adi.controller.CreateUserDto;
import tech_cx.adi.entity.User;
import tech_cx.adi.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  //Create a mock of the UserRepository
  @Mock
  private UserRepository userRepository;

  // Inject the mock into the UserService
  @InjectMocks
  private UserService userService;
  
  @Nested
  class createUser {
    @Test
    @DisplayName("Should create a user with success")
    void shouldCreateAUser() {
      //Arrange

      var user = new User(
        UUID.randomUUID(),
        "lsccaixeta", 
        "luisa@gmail.com", 
        "1234567",
        Instant.now(),
        null
      );

      // Retun the user when save is called
      doReturn(user).when(userRepository).save(any());

      var input = new CreateUserDto(
        "lsccaixeta", 
        "luisa@gmail.com", 
        "1234567"
      );
  
      //Act
      var output = userService.createUser(input);
  
      //Assert
      assertNotNull(output);
    }

    @Test
    @DisplayName("Should throw exception when error occurs")
    void shouldThrowExceptionWhenErrorOccurs() {
      
      //Arrange

      var user = new User(
        UUID.randomUUID(),
        "lsccaixeta", 
        "luisa@gmail.com", 
        "1234567",
        Instant.now(),
        null
      );

      // Retun the user when save is called
      doThrow(new RuntimeException()).when(userRepository).save(any());

      var input = new CreateUserDto(
        "lsccaixeta", 
        "luisa@gmail.com", 
        "1234567"
      );
  
      assertThrows(RuntimeException.class, () -> userService.createUser(input));
    }

  }

}
