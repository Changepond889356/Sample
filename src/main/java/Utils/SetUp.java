package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class SetUp extends GenericSkins {

	protected static String reportName;
	private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;
    static int rowcount;
	static int cellcount;
	
	@BeforeSuite
	public void ReportSetup() {
		htmlReporter = new ExtentHtmlReporter(sTestResultsPath+"ExtentTestReport.html");       
        extent = new ExtentReports();
        htmlReporter.loadConfig("extent-config.xml"); 
        extent.attachReporter(htmlReporter);
	}
	
	@BeforeMethod
	public void beforeMethod(ITestResult result) throws Exception {
		setReportName(result);
		File file3 = new File(sTestDataPath+"\\Driver.xlsx");
		FileInputStream inputStream3 = new FileInputStream(file3);
		ExcelWBook = new XSSFWorkbook(inputStream3);
		ExcelWSheet = ExcelWBook.getSheet(sSheetRegressionPacks);  //ExcelWSheet = ExcelWBook.getSheetAt(1);
		rowcount = ExcelWSheet.getLastRowNum();
		for(int j = 1; j <= rowcount; j++) {
		 	 Row = ExcelWSheet.getRow(j);
			 Cell = Row.getCell(0);
			 if(Cell.getStringCellValue().equalsIgnoreCase(reportName)) {
				 tcDescription =  Row.getCell(1).getStringCellValue().replace(".", "<br>");
				 sFinalExpectedResult = Row.getCell(3).getStringCellValue();
			 }
		}
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception {
		getResult(result);		
		SetTestCaseStatus();
		String sColor = "NA";
		System.out.println("SRegPackTestCaseStatus:" + SRegPackTestCaseStatus);
		if (SRegPackTestCaseStatus.equalsIgnoreCase("Passed")) {
			sColor = "GREEN";
		} else if(SRegPackTestCaseStatus.equalsIgnoreCase("Failed")){
			sColor = "RED";
			sActualResult=sTempResult;
		}
		else
		{
			sColor="NA";
		}
		File file2 = new File(sTestResultsPath+"\\Driver.xlsx");
		FileInputStream inputStream2 = new FileInputStream(file2);
		ExcelWBook = new XSSFWorkbook(inputStream2);
		ExcelWSheet = ExcelWBook.getSheet(sSheetRegressionPacks);  //ExcelWSheet = ExcelWBook.getSheetAt(1);
		rowcount = ExcelWSheet.getLastRowNum();
		//String sheetName = ExcelWBook.getSheetName(1);
		//System.out.println("sheetName : " + sheetName);
		for(int j = 1; j <= rowcount; j++) {
			 	 Row = ExcelWSheet.getRow(j);
				 Cell = Row.getCell(0);
				 if(Cell.getStringCellValue().equalsIgnoreCase(reportName)) {
					//writeExcel(sTestResultsPath, "KansasCB.xlsx", sheetName,  status, j, testcaseStatus);
					TestDataImport.SetExcelFile(sTestResultsPath, sDriverFile);
					TestDataImport.setCellData(sSheetRegressionPacks, j, 4, sActualResult, "NA");
					
					TestDataImport.SetExcelFile(sTestResultsPath, sDriverFile);
					TestDataImport.setCellData(sSheetRegressionPacks, j, 5, SRegPackTestCaseStatus, sColor);
					break;
				 }
			
		}
		
		
	}
	
	@AfterSuite
	public void CloseReport() {
		extent.flush();
	}
	
	public static void getResult(ITestResult result) throws IOException {
		
        if(result.getStatus() == ITestResult.FAILURE) {
        	test.log(Status.FAIL, MarkupHelper.createLabel(" FAILED ", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(" PASSED ", ExtentColor.GREEN));
            //test.pass(tcDescription);
            test.info(tcDescription);
            test.info("<html><body><b>Expected Result: </b></body></html>"+sFinalExpectedResult);
            test.info("<html><body><b>Acutal Result: </b></body></html>"+sActualResult);
        }
        else {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }
	public static void setReportName(ITestResult result) {
		
	    reportName = result.getTestClass().toString();
		String [] sr = reportName.split("[.]",0);		
		reportName = sr[sr.length -1];
		reportName = reportName.substring(0, reportName.length() -1);
		System.out.println("reportName " + reportName);
	}
}
