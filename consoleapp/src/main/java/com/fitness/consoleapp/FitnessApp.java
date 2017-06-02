package com.fitness.consoleapp;

import com.fitness.RootPackageMarker;
import com.fitness.view.FitnessUI;
import com.fitness.view.JavaSpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

public class FitnessApp {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaSpringConfig.class);
        FitnessUI fitnessUI = context.getBean(FitnessUI.class);
        fitnessUI.run();
    }
}
