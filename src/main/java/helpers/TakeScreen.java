package helpers;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TakeScreen {

    @Attachment(value = "FailureTest", type = "image/png")
    public static byte[] takeScreenShot(WebDriver driver, String testName) {
        Allure.step("Trying to take a screenshot");
        try {
        String screenshotName = testName+"_"+System.currentTimeMillis()+".png";
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File("screenshots/"+screenshotName));
        return Files.readAllBytes(Paths.get("screenshots\\" + screenshotName));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
