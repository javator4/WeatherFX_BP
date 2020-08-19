package pl.sda.weather.view;

import com.mapbox.api.staticmap.v1.MapboxStaticMap;
import com.mapbox.api.staticmap.v1.StaticMapCriteria;
import com.mapbox.geojson.Point;
import javafx.scene.image.Image;
import lombok.Getter;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class MapImage implements Runnable{
    private MapboxStaticMap map;
    private Image mapImage;
    private String token;
    private int width;
    private  int height;
    private float lon;
    private float lat;
    private boolean day;
    private final Object lock = new Object();

    public MapImage(int width, int height, float lon, float lat, boolean day) {
        this.width = width;
        this.height = height;
        this.lon = lon;
        this.lat = lat;
        this.day = day;
        token = "";
        run();
    }

    @Override
    public void run() {
        File f = new File("token.txt");
        Scanner sr = null;
        try {
            sr = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.err.println("token.txt not included, go to mapbox.com");
        }
        if(sr.hasNextLine()) token = sr.nextLine();
        map = MapboxStaticMap.builder()
                .accessToken(token)
                .styleId(
                        (day) ? StaticMapCriteria.LIGHT_STYLE : StaticMapCriteria.DARK_STYLE
                )
                .cameraPoint(Point.fromLngLat(lon, lat)) // Image's centerpoint on map
                .cameraZoom(11)
                .width(width) // Image width
                .height(height) // Image height
                .retina(true) // Retina 2x image will be returned
                .build();
        synchronized (lock) {
            mapImage = new Image(map.url().toString());
        }
    }

    public Image getMapImage() {
        synchronized (lock) {
            return mapImage;
        }
    }
}
