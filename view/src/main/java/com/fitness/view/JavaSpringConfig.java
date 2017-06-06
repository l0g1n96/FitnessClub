package com.fitness.view;

import com.fitness.RootPackageMarker;
import com.fitness.common.service.FitnessClubService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
    public InputDataReader inputDataReader(InputStream input, PrintStream print) {
        return new ConsoleReader(input, print);
    }

    @Bean
    public FitnessUI fitnessUI(FitnessClubService fitnessClub, InputDataReader input, ResultPrinter printer) {
        return new FitnessUI(fitnessClub, input, printer);
    }


}
