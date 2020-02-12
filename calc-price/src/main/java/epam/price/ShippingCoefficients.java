package epam.price;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.*;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

public class ShippingCoefficients {

    private static final String FILE_PATH_PRICE_PER_KILOGRAM ="src/main/resources/pricePerKilogram";
    private static final String FILE_PATH_PRICE_PER_KILOMETER ="src/main/resources/pricePerKilometer";

    public static Map<Double, BigDecimal> pricesPerKilogram = new TreeMap<>();
    public static Map<Double, BigDecimal> pricesPerKilometer = new TreeMap<>();
    private final static Logger logger = Logger.getLogger(ShippingCoefficients.class.getName());

    private Map<Double,BigDecimal> takePricesFromFile(String filePath) throws IOException {
        InputStream inputStream = new FileInputStream(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String jsonLine = bufferedReader.readLine();

        logger.info("JSON successfully taken from " + filePath + " file");
        return JSON.parseObject(jsonLine, new TypeReference<Map<Double, BigDecimal>>(){});
    }

    public BigDecimal getPricePerKilogram(Double kilograms) throws IOException {
        if(pricesPerKilogram.isEmpty())
            setPricesPerKilogram();
        return getPrice(kilograms, pricesPerKilogram);
    }

    public BigDecimal getPricePerKilometer(Double kilometers) throws IOException {
        if(pricesPerKilogram.isEmpty())
            setPricesPerKilometer();
        return getPrice(kilometers, pricesPerKilometer);
    }

    private BigDecimal getPrice(Double value, Map<Double, BigDecimal> mapForSearching){
        BigDecimal price = null;
        for(Map.Entry<Double, BigDecimal> itemFromMap : mapForSearching.entrySet()) {
            if(itemFromMap.getKey() <= value)
                price = itemFromMap.getValue();
        }
        return price;
    }

    private void setPricesPerKilogram() throws IOException {
        pricesPerKilogram = takePricesFromFile(FILE_PATH_PRICE_PER_KILOGRAM);
    }

    private void setPricesPerKilometer() throws IOException {
        pricesPerKilometer = takePricesFromFile(FILE_PATH_PRICE_PER_KILOMETER);
    }
}
