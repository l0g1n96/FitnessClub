package com.fitnessclub.dto;

public class FitnessInput {

    private int optionNumber;

    public FitnessInput(int optionNumber) {
        this.optionNumber = optionNumber;
    }

    public int getOptionNumber() {
        return optionNumber;
    }

    public void setOptionNumber(int optionNumber) {
        this.optionNumber = optionNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FitnessInput that = (FitnessInput) o;

        return optionNumber == that.optionNumber;
    }

    @Override
    public int hashCode() {
        return optionNumber;
    }

    @Override
    public String toString() {
        return "FitnessInput{" +
                "optionNumber=" + optionNumber +
                '}';
    }
}
