package pl.sda;

import org.apache.commons.io.IOUtils;
import pl.sda.model.Weather;

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

        if (this.data.equals("")) {
            buildURL(this.url, this.key);
            for (int i = 0; i < city.length(); i++) {
                if ( city.charAt(i) == ' ' ) {
                    city = city.substring(0, i) + "_" + city.substring(i + 1, city.length());
                }
            }
            finalURL += city;
            System.out.println("WYWOŁANIE");
            try {
                this.data = IOUtils.toString(new URL(this.finalURL), Charset.forName("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return data;
    }

    abstract Weather getWeather();

    public void buildURL(String url, String key) {
        this.url = url;
        this.key = key;
        this.finalURL = url + "?key=" + key + "&q=";
    }
}
