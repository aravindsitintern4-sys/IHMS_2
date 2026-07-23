// package utils;

// import java.io.File;
// import java.nio.file.Paths;
// import java.text.SimpleDateFormat;
// import java.util.Date;

// import com.microsoft.playwright.Page;

// public class Screenshots {

//     public static String takeScreenshot(Page page, String testName) {

//         String timeStamp =new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

//         String screenshotPath ="Screenshots" + File.separator+ testName + "_"+ timeStamp + ".png";

//         page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)).setFullPage(true));

//         System.out.println("Screenshot saved: " + screenshotPath);

//         return screenshotPath;
//     }
// }




package utils;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.microsoft.playwright.Page;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

public class Screenshots {

    public static String takeScreenshot(Page page, String testName) {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String screenshotPath = "Screenshots" + File.separator
                + testName + "_" + timeStamp + ".png";

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(screenshotPath))
                .setFullPage(true));

        System.out.println("Screenshot saved: " + screenshotPath);

        return screenshotPath;
    }

    // OCR ---> Tesseract ---> READ THE TEXT FROM IMAGE
    public static String getReceiptNumber(Page page, String testName) {

        try {

            // Take Screenshot
            String imagePath = takeScreenshot(page, testName);

            // OCR
            ITesseract tesseract = new Tesseract();
            tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");           

            String text = tesseract.doOCR(new File(imagePath));

            System.out.println("OCR TEXT:");
            System.out.println(text);

            // Dynamic Regex
            Pattern pattern = Pattern.compile("(?i)(Registration\\s*No|Reg\\s*No|Reg\\.?\\s*Number|UIN)\\s*:?\\s*([A-Za-z0-9\\-/]+)");

            Matcher matcher = pattern.matcher(text);

            if (matcher.find()) {    
                return matcher.group(2);    
            }

            return "";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}