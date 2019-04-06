package com.sapient.demo.fees.cache;

import com.sapient.demo.fees.data.TransactionDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

final public class DataSetSingleton <T> {
    public static final Logger LOG = LoggerFactory.getLogger(DataSetSingleton.class);

    private static class SavedDataClass {
        private static final DataSetSingleton SINGLETON = new DataSetSingleton();
    }

    private final List<T> data = new ArrayList<T>();

    private DataSetSingleton(){

    }

    public static DataSetSingleton getInstance(){
        return SavedDataClass.SINGLETON;
    }

    public List<T> getData(){
        return data;
    }

    void addToCache(T item){
        this.data.add(item);
    }

    public void invalidate(){
        this.data.clear();
    }

}
