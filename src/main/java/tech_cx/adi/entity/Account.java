package tech_cx.adi.entity;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

  @Id
  @Column(name = "account_id")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID accountId;

  @Column(name = "description")
  private String description;  

  @OneToOne(cascade = CascadeType.ALL,mappedBy = "account")
  @PrimaryKeyJoinColumn
  private BillingAddress billingAddress;

  @OneToMany(mappedBy = "account")
  private List<AccountStock> accountStocks;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;


}
