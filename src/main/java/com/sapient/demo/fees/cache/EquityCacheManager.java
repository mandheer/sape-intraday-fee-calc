package com.sapient.demo.fees.cache;

import com.sapient.demo.exception.FeeCalcTransException;

public interface EquityCacheManager <T>{

    public T cache (T data)  throws FeeCalcTransException;

}
