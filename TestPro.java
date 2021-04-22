package pm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestPro {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        /*InputStream inputStream = new FileInputStream("");
        properties.load(inputStream);*/
        properties.load(TestPro.class.getClassLoader().getResourceAsStream("dp.properties"));

        // 可以将配置写到文件中
        String driver = properties.getProperty("oracle.driver");
        System.out.println(driver);
    }
}
