package com.bootcamp.demo.bc_yahoo_finance.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "quotes")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuoteEntity implements Serializable{
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY) 
  private Long id;
  private String symbol;
  private BigDecimal regularMarketPrice;
  private BigDecimal regularMarketDayHigh;
  private BigDecimal regularMarketDayLow;
  private BigDecimal regularMarketOpen;
  private Instant regularMarketTime;
}
