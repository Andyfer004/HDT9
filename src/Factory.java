import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Factory {

    public static Map<String, String> createMap() {
        Map<String, String> map = null;

        Properties properties = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("config.properties");
            properties.load(input);

            String mapType = properties.getProperty("mapType");

            if (mapType.equals("SplayTree")) {
                map = new SplayTree<>();
            } else if (mapType.equals("RedBlackTreeMap")) {
                map = new RedBlackTreeMap<>();
            } else {
                throw new IllegalArgumentException("Invalid map type specified in configuration file.");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return map;
    }
}
