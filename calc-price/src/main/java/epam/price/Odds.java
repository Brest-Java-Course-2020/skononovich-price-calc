package epam.price;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Odds {

    private static Map<Double, Double> pricesPerKilogram = new HashMap<>();
    private static Map<Double, Double> pricesPerKilometer = new HashMap<>();
    private final static Logger logger = Logger.getLogger(Odds.class);

    private static String filePathPricePerKilogram ="src/main/resources/pricePerKilogram";
    private static String filePathPricePerKilometer ="src/main/resources/pricePerKilometer";

    private static Map<Double,Double> takePricesFromFile(String filePath) throws IOException {
        InputStream inputStream = new FileInputStream(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String jsonLine = bufferedReader.readLine();
        logger.info("JSON successfully taken from " + filePath + " file");
        return JSON.parseObject(jsonLine, new TypeReference<Map<Double, Double>>(){});
    }

    public static Double getPricePerKilogram(Double kilograms) throws IOException {
        if(pricesPerKilogram.isEmpty())
            setPricesPerKilogram();
        return getPrice(kilograms, pricesPerKilogram);
    }

    public static Double getPricePerKilometer(Double kilometers) throws IOException {
        if(pricesPerKilogram.isEmpty())
            setPricesPerKilometer();
        return getPrice(kilometers, pricesPerKilometer);
    }

    private static Double getPrice(Double value, Map<Double, Double> mapForSearching){
        Double price = 0d;
        for(Map.Entry<Double, Double> itemFromMap : mapForSearching.entrySet()) {
            if(itemFromMap.getKey() <= value)
                price = itemFromMap.getValue();
        }
        return price;
    }

    private static void setPricesPerKilogram() throws IOException {
        pricesPerKilogram = takePricesFromFile(filePathPricePerKilogram);
    }

    private static void setPricesPerKilometer() throws IOException {
        pricesPerKilometer = takePricesFromFile(filePathPricePerKilometer);
    }
}
