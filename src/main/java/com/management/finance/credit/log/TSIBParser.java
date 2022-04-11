package com.management.finance.credit.log;

import com.management.finance.credit.dto.Bank;
import com.management.finance.credit.dto.BillDTO;
import com.management.finance.credit.dto.BillDetailDTO;
import com.management.finance.util.LocalDateUtils;
import com.management.finance.util.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TSIBParser implements BillParser {
  @Override
  public Bank getBank() {
    return Bank.TSIB;
  }

  @Override
  public BillDTO getBillData(InputStream is) {
    try {
      PDDocument doc = PDDocument.load(is);

      PDFTextStripper stripper = new PDFTextStripper();
      String text = stripper.getText(doc);
      String[] details = text.split("\n");

      return BillDTO.builder().billDetailDTOList(createDetail(details)).build();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return BillDTO.builder().build();
  }

  private List<BillDetailDTO> createDetail(String[] datas) {
    List<BillDetailDTO> result = new ArrayList<>();

    Pattern pattern = getConsumptionPattern();
    boolean ignoreFirstMatch = true;
    for (String data : datas) {
      Matcher matcher = pattern.matcher(data);
      while (matcher.find()) {
        if (ignoreFirstMatch) {
          ignoreFirstMatch = false;
          continue;
        }
        String confirmDate = matcher.group("confirmDate");
        String spent = matcher.group("spent");
        int confirmDateIdx = data.indexOf(confirmDate) + confirmDate.length();
        int spentIdx = data.lastIndexOf(spent);
        String shop = data.substring(confirmDateIdx, spentIdx);
        String consumptionDate = matcher.group("consumptionDate");
        String currency = matcher.group("currency");

        BillDetailDTO billDetailDTO =
            BillDetailDTO.builder()
                .consumptionDate(LocalDateUtils.parseOfTWFormat(consumptionDate))
                .confirmDate(LocalDateUtils.parseOfTWFormat(confirmDate))
                .spent(BigDecimal.valueOf(StringUtils.parserMoney(spent)))
                .shop(shop.trim())
                .currency(currency)
                .build();

        result.add(billDetailDTO);
      }
    }
    return result;
  }

  @Override
  public Bank detect(File file) {

    return null;
  }

  private Pattern getConsumptionPattern() {
    return Pattern.compile(
        "(?<consumptionDate>(\\d{3}/\\d{2}/\\d{2}))\\s(?<confirmDate>(\\d{3}/\\d{2}/\\d{2})).*\\s(?<spent>-?(\\d{1,3}(,\\d{3})+|\\d{1,3}))\\s{2}(?<currency>\\w{0,3})");
  }
}
