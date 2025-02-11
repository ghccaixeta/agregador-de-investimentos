package tech_cx.adi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_accounts_stocks")
public class AccountStock {

  @EmbeddedId
  private AccountStockId id;

  @ManyToOne
  @MapsId("accountId")
  @JoinColumn(name = "account_id")
  private Account account;

  @ManyToOne
  @MapsId("stockId")
  @JoinColumn(name = "stock_id")
  private Stock stock;

  @Column(name = "quantity")
  private Integer quantity;

}
