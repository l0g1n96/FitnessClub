package com.fitness.view;

import com.fitness.common.service.FitnessClubService;
import com.fitness.RootPackageMarker;
import com.fitness.service.JavaSpringFitnessConfig;
import org.springframework.context.annotation.*;

import java.io.InputStream;
import java.io.PrintStream;

@Configuration
//@Import(JavaSpringFitnessConfig.class)
@ComponentScan(basePackageClasses = RootPackageMarker.class)
public class JavaSpringConfig {

    @Bean
    public InputStream inputStream() {
        return System.in;
    }

    @Bean
    public PrintStream printStream() {
        return System.out;
    }

    @Bean
    public ResultPrinter resultPrinter(PrintStream printStream) {
        return new ConsolePrinter(printStream);
    }

    @Bean
    public InputDataReader inputDataReader(InputStream inputStream, PrintStream printStream) {
        return new ConsoleReader(inputStream, printStream);
    }

    @Bean
    public FitnessUI fitnessUI(FitnessClubService fitnessClub, InputDataReader input, ResultPrinter print) {
        return new FitnessUI(fitnessClub, input, print);
    }
}
