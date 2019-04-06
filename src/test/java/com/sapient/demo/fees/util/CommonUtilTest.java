package com.sapient.demo.fees.util;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class CommonUtilTest {

    @Test(expected= ParseException.class)
    public final void testFormatDateNegative() throws ParseException {
        Assert.assertFalse(CommonUtil.checkDate("31/12/2005","12/31/2005"));
    }

    @Test
    public final void testFormatDatePositive() throws ParseException {
        Assert.assertTrue(CommonUtil.checkDate("11/08/1990","11/08/1990"));
    }


}