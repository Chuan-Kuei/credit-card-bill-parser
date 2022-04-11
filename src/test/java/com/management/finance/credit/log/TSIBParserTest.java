package com.management.finance.credit.log;

import com.management.finance.credit.dto.BillDTO;
import com.management.finance.credit.dto.BillDetailDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TSIBParserTest {
  private static String TSIB_FILE = "classpath:static/TSB_Creditcard_Estatement_202201.pdf";

  @InjectMocks private TSIBParser tsibParser;

  @Test
  void getBillData() throws IOException {
    // GIVEN
    File file = ResourceUtils.getFile(TSIB_FILE);
    InputStream targetStream = new FileInputStream(file);

    // WHEN
    BillDTO billDTO = tsibParser.getBillData(targetStream);

    // THEN
    assertThat(billDTO.getBillDetailDTOList()).hasSize(49);
    BigDecimal totalSpent =
        billDTO.getBillDetailDTOList().stream()
            .map(BillDetailDTO::getSpent)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    assertThat(totalSpent).isEqualByComparingTo(BigDecimal.valueOf(24081));
  }

  @Test
  void detect() {}
}
