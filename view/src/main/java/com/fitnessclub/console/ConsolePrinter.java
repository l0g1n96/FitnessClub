package com.fitnessclub.console;

public class ConsolePrinter implements ResultPrinter {

    @Override
    public void print(String output) {

        System.out.println(output);

    }

}