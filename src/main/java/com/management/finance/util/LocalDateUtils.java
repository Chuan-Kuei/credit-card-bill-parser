package com.management.finance.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateUtils {

  public static DateTimeFormatter TW_FORMAT = DateTimeFormatter.ofPattern("yyy/MM/dd");

  public static LocalDate parseOfTWFormat(String dateStr) {
    LocalDate date = LocalDate.parse(dateStr, TW_FORMAT);
    return date.plusYears(1911);
  }
}
