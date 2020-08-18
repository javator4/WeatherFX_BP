package pl.sda.weather;


import org.json.JSONException;
import org.json.JSONObject;
import pl.sda.weather.model.Current;
import pl.sda.weather.model.Location;
import pl.sda.weather.model.Weather;

public class JSONDataOrg extends AbstractJSONData {

    private String city;
    public JSONDataOrg(String url, String key) {
        this.url = url;
        this.key = key;
    }

    @Override
    public Weather getWeather() {
        this.city = city;
        String temp, lat, lon, country, name;
        temp = lat = lon = country = name = "";
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(this.getJSONData(this.city));


         temp = jsonObject.getJSONObject("current")
                .get("temperature").toString();
         lat = jsonObject.getJSONObject("location").get("lat").toString();
         lon = jsonObject.getJSONObject("location").get("lon").toString();
         country = jsonObject.getJSONObject("location").get("country").toString();
         name = jsonObject.getJSONObject("location").get("name").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Weather weather = new Weather();

        Current current =  Current.builder()
                .temperature(Float.parseFloat(temp))
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