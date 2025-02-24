package tech_cx.adi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech_cx.adi.controller.dto.CreateStockDto;
import tech_cx.adi.service.StockService;

@RestController
@RequestMapping("/v1/stocks")
public class StockController {

  private StockService stockService;

  public StockController(StockService stockService) {
    this.stockService = stockService;
  }

  @PostMapping
  public ResponseEntity<Void> createStock(@RequestBody CreateStockDto createStockDto){
    stockService.createStock(createStockDto);

    return ResponseEntity.ok().build();
  }

}
