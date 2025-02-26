package tech_cx.adi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech_cx.adi.controller.dto.AccountStockDto;
import tech_cx.adi.service.AccountService;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

  private AccountService accountService;

  private AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @PostMapping("/{accountId}/stocks")
  public ResponseEntity<Void> createAccountStock(
    @PathVariable String accountId, 
    @RequestBody AccountStockDto accountStockDto
    ){
    accountService.createAccountStock(accountId, accountStockDto);
    return ResponseEntity.ok().build();
  }

}
