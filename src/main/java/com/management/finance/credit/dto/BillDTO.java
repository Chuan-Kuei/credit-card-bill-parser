package com.management.finance.credit.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BillDTO {
  private List<BillDetailDTO> billDetailDTOList;
}
