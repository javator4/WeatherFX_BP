package pl.sda.weather.model;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import pl.sda.weather.model.weather.Condition;
import pl.sda.weather.model.weather.Current;
import pl.sda.weather.model.weather.Location;
import pl.sda.weather.model.weather.Request;

import java.io.File;
import java.io.IOException;

public class JSONDataFaster extends AbstractJSONData {
    @Setter@Getter
    private String city;
    private Weather weather;
    private final Object lock = new Object();
    public JSONDataFaster(String url, String key) {
        this.url = url;
        this.key = key;
        weather = new Weather();
        city = "";
    }

    @Override
    public Weather getWeather() {
        run();
        synchronized (lock) {
            return weather;
        }
    }

    @Override
    public void run() {
        synchronized (lock) {
            ObjectMapper objectMapper = new ObjectMapper();
            double time = System.currentTimeMillis();
            try {
                System.out.println("Processing JSON data...");

                weather =  objectMapper.readValue(getJSONData(city), Weather.class);
                objectMapper.writeValue(new File("data.json"), weather);
            }
            catch (IOException e) {
                System.err.println("Processing data failed.");

                weather = new Weather(
                        new Request(),
                        new Location(
                                "Not found",
                                "",
                                "",
                                0.0f, 0.0f,
                                "",
                                0L,
                                "",
                                0.0f

                        ),
                        new Current(
                                0,
                                "",
                                0.0f,
                                "",
                                new Condition(),
                                0.0d, 0.0d,
                                0,
                                "",
                                0,
                                0.0d,
                                0,
                                0,
                                0,
                                0,
                                0.0d,
                                0.0d,
                                0,
                                0,
                                0,
                                0.0f,
                                0.0f,
                                "",
                                "",
                                "",
                                ""

                        )
                );
            }
            double delta = System.currentTimeMillis() - time;
            System.out.println("Processing done. Time " + delta + " ms");
        }
    }
}
