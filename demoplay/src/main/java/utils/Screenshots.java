package utils;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.microsoft.playwright.Page;

public class Screenshots {

    public static String takeScreenshot(Page page, String testName) {

        String timeStamp =new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String screenshotPath ="Screenshots" + File.separator+ testName + "_"+ timeStamp + ".png";

        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)).setFullPage(true));

        System.out.println("Screenshot saved: " + screenshotPath);

        return screenshotPath;
    }
}