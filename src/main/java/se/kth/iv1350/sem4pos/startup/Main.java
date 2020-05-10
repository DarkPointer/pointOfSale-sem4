package se.kth.iv1350.sem4pos.startup;

import se.kth.iv1350.sem4pos.controller.Controller;
import se.kth.iv1350.sem4pos.integration.Printer;
import se.kth.iv1350.sem4pos.integration.SystemCreator;
import se.kth.iv1350.sem4pos.loghandlers.*;
import se.kth.iv1350.sem4pos.view.View;

/**
 * The main class which is responsible upon initializing the program.
 */
public class Main {
        /**
         * Starts the program and performs a sample sale process.
         * @param args No command-line parameters are expected.
         */
        public static void main(String[] args){
                SystemCreator systemCreator = new SystemCreator();
                Printer printer = new Printer();
                ExceptionLogger devLogger = new DeveloperLogger();
                ExceptionLogger userLogger = new UserLogger();
                Controller cont = new Controller(systemCreator, devLogger, userLogger, printer);
                View view = new View(cont);
                view.simulateSaleProcess();
                view.simulateSaleProcess();
        }
}
