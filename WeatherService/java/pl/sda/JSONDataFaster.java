package pl.sda;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sda.model.Weather;

import java.io.File;
import java.io.IOException;

public class JSONDataFaster extends AbstractJSONData {

    private String city;
    public JSONDataFaster(String url, String key) {
        this.url = url;
        this.key = key;
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
