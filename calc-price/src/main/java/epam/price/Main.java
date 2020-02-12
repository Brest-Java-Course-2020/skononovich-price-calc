package epam.price;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;


public class Main {
    final static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("application-config.xml");

        Calculator calculator = (Calculator) applicationContext.getBean("calculator");

        ShippingCoefficients coefficients = (ShippingCoefficients) applicationContext.getBean("shippingCoefficients");


        try {
            calculator.shippingCostCalculator(coefficients);
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