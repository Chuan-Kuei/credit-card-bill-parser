package com.management.finance.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class LocalDateUtilsTest {

  @Test
  void testParseOfTWFormat() {
    // GIVEN
    String twDate = "111/01/11";

    // WHEN
    LocalDate date = LocalDateUtils.parseOfTWFormat(twDate);

    // THEN
    assertThat(date).isEqualTo(LocalDate.of(2022, 1, 11));
  }
}
