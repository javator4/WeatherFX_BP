package pl.sda.weather;

import org.apache.commons.io.IOUtils;
import pl.sda.weather.model.Weather;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

public abstract class AbstractJSONData {

     String finalURL;
     String url;
     String key;
     String data = "";

    public AbstractJSONData() {
    }

    public String getJSONData(String city) {


            buildURL(this.url, this.key);
            finalURL += city;
            System.out.println("Connected to server");
            try {
                this.data = IOUtils.toString(new URL(this.finalURL), Charset.forName("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }


        return data;
    }

    abstract Weather getWeather();

    public void buildURL(String url, String key) {
        this.url = url;
        this.key = key;
        this.finalURL = url + "?access_key=" + key + "&query=";
    }
}
