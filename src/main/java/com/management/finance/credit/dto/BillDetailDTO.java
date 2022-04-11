package com.management.finance.credit.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class BillDetailDTO {
  private LocalDate consumptionDate;
  private String shop;
  private LocalDate confirmDate;
  private BigDecimal spent;
  private String currency;
}
