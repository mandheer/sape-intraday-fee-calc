package com.sapient.demo.fees.service;

import com.sapient.demo.exception.FeeCalcTransException;
import com.sapient.demo.fees.data.FeeCalcDetails;
import com.sapient.demo.fees.data.TransactionDetails;

public interface FeeCalcServiceProvider {
    public FeeCalcDetails process(TransactionDetails detail) throws FeeCalcTransException;
}
