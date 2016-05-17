package methods;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by Александр on 17.05.2016.
 */
public class GetScreenshot {

    public static String getRundomString(int length) {
        StringBuilder sb = new StringBuilder();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        for (int i = 0; i < length; i++) {
            int index = (int)(Math.random()*characters.length());
            sb.append(characters.charAt(index));
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
    public static void getScreenshot(WebDriver driver) throws IOException {
        String fileName = getRundomString(6) + ".png";
        String directory = "C:\\Users\\1\\";

        File sourcefile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourcefile, new File(directory + fileName));

    }
}
