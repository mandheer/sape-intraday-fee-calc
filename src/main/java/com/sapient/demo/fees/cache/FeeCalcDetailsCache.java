package com.sapient.demo.fees.cache;

import com.sapient.demo.exception.FeeCalcTransException;
import com.sapient.demo.fees.data.FeeCalcDetails;
import com.sapient.demo.fees.data.TransactionDetails;
import com.sapient.demo.fees.data.TransactionType;
import com.sapient.demo.fees.util.CommonUtil;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
public final class FeeCalcDetailsCache implements EquityCacheManager<FeeCalcDetails> {

    final private List<FeeCalcDetails> data = DataSetSingleton.getInstance().getData();

    @Override
    public FeeCalcDetails cache(FeeCalcDetails feeCalcDetails) throws FeeCalcTransException{
        DataSetSingleton.getInstance().addToCache(feeCalcDetails);
        try {
            return findInCache(feeCalcDetails);
        } catch(Exception e){
            throw new FeeCalcTransException(e);
        }
    }

    public FeeCalcDetails findInCache(final FeeCalcDetails feeCalcDetails) throws Exception{
        for(FeeCalcDetails detail:this.data) {
            if(detail.getClientId().equals(feeCalcDetails.getClientId()) &&
                    detail.getSecurityId().equals(feeCalcDetails.getSecurityId()) &&
                    CommonUtil.checkDate(feeCalcDetails.getTransDate(),detail.getTransDate()) &&
                    ((TransactionType.SELL.equals(feeCalcDetails.getType()) &&
                            TransactionType.BUY.equals(detail.getType())) ||
                            (TransactionType.BUY.equals(feeCalcDetails.getType()) &&
                                    TransactionType.SELL.equals(detail.getType())))){
                return detail;
            }
        }
        return null;

        /*
        cannot use stream api as it does not contain the ref of original data.

        Optional<FeeCalcDetails> optional =
         this.data.parallelStream().filter(detail -> {
            try {
                return detail.getClientId().equals(feeCalcDetails.getClientId()) &&
                        detail.getSecurityId().equals(feeCalcDetails.getSecurityId()) &&
                        CommonUtil.checkDate(feeCalcDetails.getTransDate(),detail.getTransDate()) &&
                        ((TransactionType.SELL.equals(feeCalcDetails.getType()) &&
                                TransactionType.BUY.equals(detail.getType())) ||
                                (TransactionType.BUY.equals(feeCalcDetails.getType()) &&
                                        TransactionType.SELL.equals(detail.getType())));
            } catch (ParseException e){

            }
            return false;
        }).findAny();
        return optional.isPresent() ? optional.get() : null;*/
    }
}
