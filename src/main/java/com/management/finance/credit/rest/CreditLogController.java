package com.management.finance.credit.rest;

import com.management.finance.credit.dto.Bank;
import com.management.finance.credit.dto.BillDTO;
import com.management.finance.credit.log.BillParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/credit-log")
public class CreditLogController {

  private final Map<Bank, BillParser> billParserMap;

  @Autowired
  public CreditLogController(Map<Bank, BillParser> billParserMap) {
    this.billParserMap = billParserMap;
  }

  @PostMapping("/{bank}")
  public ResponseEntity<BillDTO> getCreditLog(
      @PathVariable Bank bank, @RequestPart MultipartFile file) throws IOException {
    BillParser billParser = billParserMap.get(bank);
    if (ObjectUtils.isEmpty(billParser)) {
      throw new UnsupportedOperationException("unknown bank " + bank);
    }
    return ResponseEntity.ok(billParser.getBillData(file.getInputStream()));
  }
}
