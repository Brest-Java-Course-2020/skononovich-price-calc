package epam.price;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import static epam.price.Calculator.shippingCostCalculator;

public class Main {

    final static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        try {
            shippingCostCalculator();
        }
        catch (FileNotFoundException fileNotFound){
            logger.info("File not found: " + fileNotFound.getMessage());
            System.out.println("File not found");
        }
        catch (IOException e) {
            logger.info("Error." + e.getMessage());
            System.out.println("Some problems with reading file");
        }
    }
}