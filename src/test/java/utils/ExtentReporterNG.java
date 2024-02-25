package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    //Object of extent report and use it to add steps/artifacts etc to report
    public static ExtentReports extent;

    public static ExtentReports getReportObject(String folderName) {

        String pathOfMyReport = System.getProperty("user.dir") + "/reports/" + folderName + "/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(pathOfMyReport);
        reporter.config().setReportName("Appium Sample Report");
        reporter.config().setDocumentTitle("Results for Apppium Test");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        return extent;
    }


}
