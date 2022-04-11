package com.management.finance.credit.log;

import com.management.finance.credit.dto.Bank;
import com.management.finance.credit.dto.BillDTO;

import java.io.File;
import java.io.InputStream;

public interface BillParser {

  Bank getBank();

  BillDTO getBillData(InputStream is);

  Bank detect(File file);
}
