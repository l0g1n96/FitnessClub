package com.fitnessclub;

public class ConsolePrinter implements ResultPrinter{

    @Override
    public void print(String output) {

        char[] c = output.toCharArray();

        for(int i = 0; i < output.length(); i++){
            char a = output.charAt(i);
            System.out.println(a);
        }

    }

}
