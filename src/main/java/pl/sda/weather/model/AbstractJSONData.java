package pl.sda.weather.model;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public abstract class AbstractJSONData implements Runnable {

     String finalURL;
     String url;
     String key;
     String data = "";

    public AbstractJSONData() {
    }

    public String getJSONData(String city) {
        buildURL(this.url, this.key);
        finalURL += city;

        double time = System.currentTimeMillis();
        try {
            this.data = IOUtils.toString(new URL(this.finalURL), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return data;
        }
        System.out.print("Connected to server");
        double delta = System.currentTimeMillis() - time;
        System.out.printf("... Ping: %.0f ms\n", delta);


        return data;
    }

    abstract Weather getWeather();

    public void buildURL(String url, String key) {
        this.url = url;
        this.key = key;
        this.finalURL = url + "?access_key=" + key + "&query=";
    }
}
