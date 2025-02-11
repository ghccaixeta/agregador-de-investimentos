package tech_cx.adi.entity;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
  
  @Id
  private String stockId;

  @Column(name = "description")
  private String description;

  @CreationTimestamp
  private Instant creationTimestamp;

  @UpdateTimestamp
  private Instant updateTimestamp;

}
