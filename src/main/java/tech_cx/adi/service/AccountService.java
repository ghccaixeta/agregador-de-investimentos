package tech_cx.adi.service;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tech_cx.adi.controller.dto.AccountStockDto;
import tech_cx.adi.entity.AccountStock;
import tech_cx.adi.entity.AccountStockId;
import tech_cx.adi.repository.AccountRepository;
import tech_cx.adi.repository.AccountStockRepository;
import tech_cx.adi.repository.StockRepository;

@Service
public class AccountService {
  private AccountRepository accountRepository;
  private StockRepository stockRepository;
  private AccountStockRepository accountStockRepository;

  public AccountService(AccountRepository accountRepository, StockRepository stockRepository, AccountStockRepository accountStockRepository) {
    this.accountRepository = accountRepository;
    this.stockRepository = stockRepository;
    this.accountStockRepository = accountStockRepository;
  }

  public void createAccountStock(String accountId, AccountStockDto accountStockDto) {
    var account = accountRepository.findById(UUID.fromString(accountId))
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta nao encontrada"));

    var stock = stockRepository.findById(accountStockDto.stockId())
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticker nao encontrado"));

    var accountStockId = new AccountStockId(account.getAccountId(), stock.getStockId());

    var accountStock = new AccountStock();

    accountStock.setId(accountStockId);
    accountStock.setAccount(account);
    accountStock.setStock(stock);
    accountStock.setQuantity(accountStockDto.quantity());

    accountStockRepository.save(accountStock);
    
  }
}
