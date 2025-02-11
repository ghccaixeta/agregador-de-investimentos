package tech_cx.adi.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountStockId {

  @Column(name = "account_id")
  private UUID accountId;

  @Column(name = "stock_id")
  private String stockId;

}
