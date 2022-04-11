package com.management.finance.credit.config;

import com.management.finance.credit.dto.Bank;
import com.management.finance.credit.log.BillParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class BillParserConfig {

  private final List<BillParser> billParserList;

  @Autowired
  public BillParserConfig(List<BillParser> billParserList) {
    this.billParserList = billParserList;
  }

  @Bean
  public Map<Bank, BillParser> billParserMap() {
    return billParserList.stream()
        .collect(Collectors.toMap(BillParser::getBank, Function.identity()));
  }
}
