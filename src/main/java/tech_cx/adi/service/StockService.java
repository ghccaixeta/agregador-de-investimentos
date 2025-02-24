package tech_cx.adi.service;

import org.springframework.stereotype.Service;

import tech_cx.adi.controller.CreateUserDto;
import tech_cx.adi.controller.dto.CreateStockDto;
import tech_cx.adi.entity.Stock;
import tech_cx.adi.repository.StockRepository;

@Service
public class StockService {
  private StockRepository stockRepository;

  public StockService(StockRepository stockRepository) {
    this.stockRepository = stockRepository;
  }

  public void createStock(CreateStockDto createStockDto) {

    var stock = new Stock();

    stock.setStockId(createStockDto.stockId());
    stock.setDescription(createStockDto.description());

    stockRepository.save(stock);
  }
}
