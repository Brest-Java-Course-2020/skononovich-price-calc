package epam.price;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

import static epam.price.Odds.getPricePerKilogram;
import static epam.price.Odds.getPricePerKilometer;

public class Main {

    final static Logger logger = Logger.getLogger(Main.class.getName());
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Double[] enteredValues = new Double[2];
        int i = 0;

        do {
            switch (i) {
                case 0: {
                    System.out.println("Please, enter distance or Q for exit: ");
                    break;
                }
                case 1: {
                    System.out.println("Please, enter weight or Q for exit: ");
                    break;
                }
            }
            if(scanner.hasNextDouble()) {
                enteredValues[i] = scanner.nextDouble();
                logger.info("User enter value " + enteredValues[i]);
                if(enteredValues[i] < 0){
                    logger.info("Value " + enteredValues[i] + " not accepted");
                    System.out.println("Number must be positive");
                } else i++;
            } else if(scanner.next().equalsIgnoreCase("Q")) {
                logger.info("User quit from program");
                break;
            }
            if (i == 2) {
                try {
                    System.out.println("Price: $" + (Double) (enteredValues[0] * getPricePerKilometer(enteredValues[0]) +
                            enteredValues[1] * getPricePerKilogram(enteredValues[1])));

                    logger.info("Distance = " + enteredValues[0] + " Price per km = " + getPricePerKilometer(enteredValues[0]) +
                            " Weight = " + enteredValues[1] + " Price per kg = " + getPricePerKilogram(enteredValues[1]) +
                            " Cost of delivery = " + ((Double) (enteredValues[0] * getPricePerKilometer(enteredValues[0]) +
                            enteredValues[1] * getPricePerKilogram(enteredValues[1]))));
                    i = 0;
                } catch (IOException e) {
                    logger.error("Can't take value from file:\n" + e.getMessage());
                    i = 2;
                }
            }
        } while (true);
        System.out.println("Finish!");
        logger.info("Program completed");
    }
}