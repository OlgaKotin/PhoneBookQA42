package helpers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesWriter {

    private static final String PROPERTIES_FILE = "src/main/resources/resources.properties";

    public static void writeProperty(String key, String value, boolean cleanFile) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            if (Files.exists(Paths.get(PROPERTIES_FILE))) {
                Files.createFile(Paths.get(PROPERTIES_FILE));
            }
            fileInputStream = new FileInputStream(PROPERTIES_FILE);
            properties.load(fileInputStream);
            if (cleanFile) {
                properties.clear();
            }
            properties.setProperty(key, value);
            fileOutputStream = new FileOutputStream(PROPERTIES_FILE);
            properties.store(fileOutputStream, "");
        }
        catch (IOException exception) {exception.printStackTrace();}
        finally {
            try{
                if(fileOutputStream != null);
                fileOutputStream.close();
                if(fileInputStream != null)
                fileInputStream.close();
            } catch (IOException exeption) {
                    exeption.printStackTrace();
            }
        }
    }
}
