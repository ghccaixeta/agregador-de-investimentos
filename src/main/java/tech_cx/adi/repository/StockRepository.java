package tech_cx.adi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech_cx.adi.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {

}
