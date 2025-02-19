package tech_cx.adi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tech_cx.adi.controller.dto.CreateAccountDto;
import tech_cx.adi.controller.dto.CreateUserDto;
import tech_cx.adi.controller.dto.UpdateUserDto;
import tech_cx.adi.entity.Account;
import tech_cx.adi.entity.BillingAddress;
import tech_cx.adi.entity.User;
import tech_cx.adi.repository.AccountRepository;
import tech_cx.adi.repository.BillingAddressRepository;
import tech_cx.adi.repository.UserRepository;


@Service
public class UserService {


  private UserRepository userRepository;
  private AccountRepository accountRepository;
  private BillingAddressRepository billingAddressRepository;

  public UserService(
    UserRepository userRepository, 
    AccountRepository accountRepository,
    BillingAddressRepository billingAddressRepository
  ) {
    this.userRepository = userRepository;
    this.accountRepository = accountRepository;
    this.billingAddressRepository = billingAddressRepository;
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

  public void   createAccount(String userId, CreateAccountDto createAccountDto) {
    
    var user = userRepository.findById(UUID.fromString(userId))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não existe"));

    var account = new Account();
    account.setUser(user);
    account.setDescription(createAccountDto.description());
    account.setAccountStocks(new ArrayList<>());
    
    var accountCreated = accountRepository.save(account);

    var billingAddress = new BillingAddress();
    billingAddress.setAccount(accountCreated);
    billingAddress.setStreet(createAccountDto.street());
    billingAddress.setNumber(createAccountDto.number());

    billingAddress = billingAddressRepository.save(billingAddress);


  }

  public List<Account> getUserAccounts(String userId) {
    return accountRepository.findAll();
  }

}
