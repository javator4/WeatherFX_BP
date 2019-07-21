package pl.sda;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sda.model.Weather;

import java.io.File;
import java.io.IOException;

public class FasterImplementation extends JSONDataFaster {

    private String city;

    public FasterImplementation(String url, String key, String city) {
        super(url, key);
        this.city = city;
    }

    @Override
    public Weather getWeather() {
        ObjectMapper objectMapper = new ObjectMapper();
        Weather weather = null;
        try {
            weather =  objectMapper.readValue(getJSONData(city),
                    Weather.class);
            objectMapper.writeValue(new File("data.json"), weather);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weather;
    }
}