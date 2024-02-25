package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class SSUtils {

    public static void takeScreenShot(WebDriver driver, String fileName) throws IOException {
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile , new File(fileName));
    }

    public static String takeScreenShotAsBase64(WebDriver driver)  {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
    }

    public static  String getDateTimeStamp() {
        return new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new java.util.Date());
    }
}
