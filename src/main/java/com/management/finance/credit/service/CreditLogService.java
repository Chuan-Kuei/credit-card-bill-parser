package com.management.finance.credit.service;

import com.management.finance.credit.dto.Bank;
import com.management.finance.credit.dto.BillDTO;
import org.springframework.stereotype.Service;

@Service
public class CreditLogService {

  public BillDTO getBillDetail() {
    Bank bank = detectBank();
    return parserBill(bank);
  }

  private BillDTO parserBill(Bank bank) {
    return null;
  }

  private Bank detectBank() {
    return null;
  }
}
