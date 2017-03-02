package com.fitnessclub;

import com.fitnessclub.dto.FitnessInput;
import com.fitnessclub.dto.FitnessOutput;

public interface DataHandler {

    FitnessOutput doWork(FitnessInput fitnessInput);

}
