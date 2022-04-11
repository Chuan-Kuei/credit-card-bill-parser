package com.management.finance.util;

public class StringUtils {

  public static Float parserMoney(String money) {
    return Float.parseFloat(money.replaceAll(",", ""));
  }
}
