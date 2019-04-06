package com.sapient.demo.fees.service;

import com.sapient.demo.exception.FeeCalcTransException;
import com.sapient.demo.fees.data.FeeCalcDetails;
import com.sapient.demo.fees.data.TransactionDetails;
import com.sapient.demo.fees.data.TransactionPriority;
import com.sapient.demo.fees.data.TransactionType;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class FeeCalcServiceTest {

    @Autowired
    FeeCalcService feeCalcService;

    @Test(expected = FeeCalcTransException.class)
    public void process() throws FeeCalcTransException{
        TransactionDetails calc = new TransactionDetails();
        calc.setSecurityId("ICICI");
        calc.setPriority(TransactionPriority.N);
        calc.setClientId("MR");
        calc.setExternalTransactionId("123456789");
        calc.setCurrentMktVal(50);
        calc.setTransDate("03/19/2019");
        calc.setType(TransactionType.WITHDRAW);

        Assert.assertNull(feeCalcService.process(calc));

    }


}