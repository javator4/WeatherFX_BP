package pl.sda.weather.model;

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

public class MapImage {
    private MapboxStaticMap map;
    private StaticMapCriteria staticMapCriteria;
    @Getter
    private Image mapImage;
    private String token;
    private int width;
    private  int height;
    private float lon;
    private float lat;

    public MapImage(int width, int height, float lon, float lat, boolean day) {
        this.width = width;
        this.height = height;
        this.lon = lon;
        this.lat = lat;
        token = "";
        File f = new File("token.txt");
        Scanner sr = null;
        try {
            sr = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

        mapImage = new Image(map.url().toString());

    }
}
