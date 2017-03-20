package com.fitnessclub.console;

import com.fitnessclub.core.dto.FitnessInput;

public interface InputDataReader {

    FitnessInput read() throws Exception;

}
