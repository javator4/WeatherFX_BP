package pl.sda;

import org.json.JSONObject;
import pl.sda.model.Current;
import pl.sda.model.Location;
import pl.sda.model.Weather;

public class OrgImplementation implements WeatherForecast{

    private JSONDataFaster weatherService;
    private String city;

    public OrgImplementation(JSONDataFaster weatherService, String city) {
        this.weatherService = weatherService;
        this.city = city;
    }

    @Override
    public Weather getWeather() {

        JSONObject jsonObject = new JSONObject(this.weatherService.getJSONData(this.city));

        String temp = jsonObject.getJSONObject("current")
                .get("temp_c").toString();
        String lat = jsonObject.getJSONObject("location").get("lat").toString();
        String lon = jsonObject.getJSONObject("location").get("lon").toString();
        String country = jsonObject.getJSONObject("location").get("country").toString();
        String name = jsonObject.getJSONObject("location").get("name").toString();


        Weather weather = new Weather();

        Current current =  Current.builder()
                .temp_c(Float.parseFloat(temp))
                .build();

        Location location =  Location.builder()
                .lat(Float.parseFloat(lat))
                .lon(Float.parseFloat(lon))
                .country(country)
                .name(name)
                .build();

        weather.setCurrent(current);
        weather.setLocation(location);

        return weather;
    }
}