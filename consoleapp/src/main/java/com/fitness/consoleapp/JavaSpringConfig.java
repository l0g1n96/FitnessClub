package com.fitness.consoleapp;

import core.service.DefaultFitnessClubService;
import core.service.FitnessClubService;
import datamanage.FitnessScheduler;
import dataprocess.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

@Configuration
@ComponentScan
public class JavaSpringConfig {

    @Bean
    public PrintStream printStream(PrintStream printStream) {
        return new PrintStream(printStream);
    }

    @Bean
    public ResultPrinter print(PrintStream printStream) {
        return new ConsolePrinter(printStream);
    }

    @Bean
    public InputStream inputStream() {
        return System.in;
    }

    @Bean
    public PrintStream outputStream() {
        return System.out;
    }

    @Bean
    public InputDataReader dataReader(InputStream inputStream, PrintStream printStream) {
        return new ConsoleReader(inputStream, printStream);
    }

    @Bean
    public FitnessClubService fitness() {
        return new DefaultFitnessClubService();
    }

    @Bean
    public Scheduler scheduler() {
        return new FitnessScheduler();
    }

}
