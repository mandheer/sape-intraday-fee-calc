package com.sapient.demo.exception;

public class FeeCalcTransException extends Exception {
    public FeeCalcTransException(){
        super();
    }

    public FeeCalcTransException(String message){
        super(message);
    }

    public FeeCalcTransException(String message,Throwable cause){
        super(message,cause);
    }

    public FeeCalcTransException(Throwable cause){
        super(cause);
    }

}
