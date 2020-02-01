package epam.price;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Odds {
    private static Map<Double, Double> pricesPerKilogram = new HashMap<>();
    private static Map<Double, Double> pricesPerKilometer = new HashMap<>();

    private static TypeReference<Map<Double, Double>> typeReference = new TypeReference<Map<Double, Double>>(){};

//TODO Подключение к файлам и их создание при отсутствии. Нейминг функций


    private static Map<Double,Double> getPricesFromFile(String filePath) throws IOException {
        return JSON.parseObject(filePath, new TypeReference<Map<Double, Double>>(){});
    }


    public static void setPricesPerKilogram() throws IOException {
        pricesPerKilogram = getPricesFromFile(
                "{0.0:20.0,500.0:17.0,1000.0:15.0}");
    }

    public static void setPricesPerKilometer() throws IOException {
        pricesPerKilometer = getPricesFromFile(
                "{0.0:10.0,500.0:7.3,1000.0:6.6}");
    }


    private static void writePriceToFile(Map<Double, Double> writingMap, String fileName) throws IOException {
        FileWriter jsonFileWithPrice = new FileWriter(fileName, false);
        jsonFileWithPrice.write(JSON.toJSONString(writingMap));
        jsonFileWithPrice.flush();
    }

    public static void setPricePerKilogram(Map<Double,Double> price) throws IOException {
        pricesPerKilogram = price;
        writePriceToFile(pricesPerKilogram,
                "/home/sergeykon/Desktop/Work/epam/practice/skononovich-price-calc/calc-price/src/main/resources/pricePerKilogram");
    }

    public static void setPricePerKilometer(Map<Double, Double> price) throws IOException {
        pricesPerKilometer = price;
        writePriceToFile(pricesPerKilometer,
                "/home/sergeykon/Desktop/Work/epam/practice/skononovich-price-calc/calc-price/src/main/resources/pricePerKilometer");
    }

    public static Double getPricePerKilogram(Double kilograms){
        Double price = 0d;

        for(Map.Entry<Double, Double>  costPerKilogram : pricesPerKilogram.entrySet()) {
            if(costPerKilogram.getKey() < kilograms)
                price = costPerKilogram.getValue();
        }
        return price;
    }

    public static Double getPricePerKilometer(Double kilometers){
        Double price = 0d;

        for(Map.Entry<Double, Double> costPerKilometer : pricesPerKilometer.entrySet()) {
            if(costPerKilometer.getKey() < kilometers)
                price = costPerKilometer.getValue();
        }
        return price;
    }
}
