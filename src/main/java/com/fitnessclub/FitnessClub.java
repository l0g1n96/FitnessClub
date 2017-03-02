package com.fitnessclub;

import com.fitnessclub.dto.FitnessInput;
import com.fitnessclub.dto.FitnessOutput;

public class FitnessClub {

    private InputDataReader input;
    private DataHandler dataHandler;
    private ResultPrinter printer;

    public FitnessClub(InputDataReader input, DataHandler dataHandler, ResultPrinter printer) {
        this.input = input;
        this.dataHandler = dataHandler;
        this.printer = printer;
    }

    public void doWork() throws Exception {

        try {

            FitnessInput inputData = input.read();
            FitnessOutput output = dataHandler.doWork(inputData);
            printer.print(output);

        } catch (Exception e) {
            throw e;
        }
    }


}
