package com.fitnessclub.console;

import com.fitnessclub.fitnessMain.ResultPrinter;

public class ConsolePrinter implements ResultPrinter {

    @Override
    public void print(String output) {

        System.out.println(output);

    }

}
