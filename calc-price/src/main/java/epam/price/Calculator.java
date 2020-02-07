package epam.price;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.logging.Logger;

import static epam.price.ShippingCoefficients.getPricePerKilogram;
import static epam.price.ShippingCoefficients.getPricePerKilometer;

public class Calculator {
    final static String QUIT_FROM_PROGRAM = "Q";

    final static Logger logger = Logger.getLogger(Calculator.class.getName());

    static Scanner scanner = new Scanner(System.in);

    public static void shippingCostCalculator() throws IOException {
        BigDecimal distance = null;
        BigDecimal weight = null;
        int i = 0;

        do {
            switch (i) {
                case 0: {
                    System.out.println("Please, enter distance or Q for exit: ");
                    distance = BigDecimal.valueOf(enteredDoubleValue());
                    i++;
                    break;
                }
                case 1: {
                    System.out.println("Please, enter weight or Q for exit: ");
                    weight = BigDecimal.valueOf(enteredDoubleValue());
                    i++;
                    break;
                }
                case 2: {
                    System.out.println(calculatePrice(distance, weight));
                    i = 0;
                    break;
                }
            }
        } while (true);
    }

    private static BigDecimal calculatePrice(BigDecimal distance, BigDecimal weight) throws IOException {
        BigDecimal costOfDistanceDelivery;
        BigDecimal costOfWightDelivery;
        BigDecimal costOfDelivery;

        costOfDistanceDelivery = distance.multiply(getPricePerKilometer(distance.doubleValue()));
        costOfWightDelivery = weight.multiply(getPricePerKilogram(distance.doubleValue()));
        costOfDelivery = costOfDistanceDelivery.add(costOfWightDelivery).setScale(2, BigDecimal.ROUND_HALF_UP);

        logger.info("Distance = " + distance + " Price per km = " + getPricePerKilometer(distance.doubleValue()) +
                " Weight = " + weight + " Price per kg = " + getPricePerKilogram(weight.doubleValue()) +
                " Cost of delivery = " + costOfDelivery);

        return costOfDelivery;
    }

    private static Double enteredDoubleValue(){
        Double readValue;
        do {
            if(enteredValueDouble()){
                readValue = readDoubleValue();
                if(doubleValueCorrect(readValue)) {
                    return readValue;
                }
            } else tryExitProgram(readStringFromConsole());
        } while (true);
    }

    private static String readStringFromConsole(){
        String stringFromConsole = scanner.next();
        logger.info("Прочитанна строка:" + stringFromConsole);
        return stringFromConsole;
    }

    private static boolean enteredValueDouble(){
        boolean enteredValueIsNumber = scanner.hasNextDouble();
        logger.info(enteredValueIsNumber ? "Было введено число" : "Введена строка, которую нельзя преобразовать к числу");
        return enteredValueIsNumber;
    }

    private static Double readDoubleValue(){
        Double valueFromConsole = Double.parseDouble(readStringFromConsole());
        logger.info("Строка была преобразована в число: " + valueFromConsole);
        return valueFromConsole;
    }

    private static boolean doubleValueCorrect(Double enteredValue){
        boolean valueIsCorrect = enteredValue >= 0;
        logger.info(valueIsCorrect ? "Введено корректное значение" : "Введено некорректное значение. Число должно быть больше либо равно 0");
        return valueIsCorrect;
    }

    private static void tryExitProgram(String enteredString){
        if(enteredString.equalsIgnoreCase(QUIT_FROM_PROGRAM)){
            logger.info("User quit from program");
            scanner.close();
            System.exit(0);
        }
    }
}
