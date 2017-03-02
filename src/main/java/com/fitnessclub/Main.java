package com.fitnessclub;

import com.fitnessclub.dto.FC;

public class Main {

    public static void main(String[] args) throws Exception {



        InputDataReader consoleReader = new ConsoleReader();
        DataHandler dataHandler = new FC();
        ResultPrinter resultPrinter = new ConsoleResultPrinter();

        FitnessClub fitnessClub = new FitnessClub(consoleReader, dataHandler, resultPrinter);
        fitnessClub.doWork();

    }

}
