package epam.price;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static epam.price.Odds.*;

public class Main {

    //TODO Добавить возможность указать коэффициенты, и изменить их при необходимости
    public static void main(String[] args) throws IOException {

//        Map<Double, Double> pricePerKilogram = new HashMap<Double, Double>();
//        pricePerKilogram.put(0d, 20d);
//        pricePerKilogram.put(500d, 17d);
//        pricePerKilogram.put(1000d, 15d);
//
//        Map<Double, Double> pricePerKilometer = new HashMap<Double, Double>();
//        pricePerKilometer.put(0d, 10d);
//        pricePerKilometer.put(500d, 7.3d);
//        pricePerKilometer.put(1000d, 6.6d);
//
//        setPricePerKilogram(pricePerKilogram);
//        setPricePerKilometer(pricePerKilometer);
//
//        Path currentRelativePath = Paths.get("");
//        String s = currentRelativePath.toAbsolutePath().toString();
//        System.out.println("Current relative path is: " + s);

        Double[] enteredValues = new Double[2];

        Scanner scanner = new Scanner(System.in);

        setPricesPerKilogram();
        setPricesPerKilometer();
        int i = 0;

        do {
            switch (i){

                case 0 : {
                    System.out.println("Please enter distance or Q for exit: ");
                    break;
                }
                case 1 : {
                    System.out.println("Please, enter weight or Q for exit: ");
                    break;
                }
            }


            if(scanner.hasNextDouble()) {
                enteredValues[i] = scanner.nextDouble();
                i++;
            } else if(scanner.next().equalsIgnoreCase("Q")) break;

            if (i == 2){
                System.out.println("Price: $" + (Double) (enteredValues[0]*getPricePerKilometer(enteredValues[0]) +
                        enteredValues[1]*getPricePerKilogram(enteredValues[1])));
                i = 0;
            }
        } while (true);

        System.out.println("Finish!");

    }
}