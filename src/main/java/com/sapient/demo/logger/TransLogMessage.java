package com.sapient.demo.logger;

public interface TransLogMessage {
    String TRANS00001E = "Input file provided is in incorrect format";
    String TRANS00002E = "Error parsing the input file";
    String TRANS00003E = "Can not read input file";
    String TRANS00004E = "Cannot write to destination";
    /**
     * date parsing log error message
     */
    String TRANS00005E = "The date provided is in incorrect format.";
    String TRANS00006E = "Error occurred while parsing. {}";


    /**
     * Strace Messages
     */
    String TRANS00001T = "Inside class method name {} : Begin";
    String TRANS00002T = "Inside class method name {} : End";

    /**
     *
     */
    String TRANS00001I = "Batch Job successfully started at : {} ";
    String TRANS00002I = "Batch Job successfully completed at : {} ";

}
