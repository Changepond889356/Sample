package TestCases;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.testng.TestNG;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import Utils.GenericSkins;
import Utils.TestDataImport;

public class DriverScript extends GenericSkins {
	/*@BeforeTest
	@Parameters({ "browser", "NodeURL", "NodeName", "RegPackName" })
	public void remote(@Optional("Chrome") String browser, @Optional("NA") String NodeURL,
			@Optional("Log") String NodeName, @Optional("Null") String RegPackName) {
		sNodeBrowser = browser;
		sNodeURL = NodeURL;
		sNodeName = NodeName;
		sNodeRegPackName = RegPackName;

	}*/

	@Test
	@Parameters({ "browser", "NodeURL", "NodeName", "RegPackName" })
	public void main(@Optional("Chrome") String browser, @Optional("NA") String NodeURL,
			@Optional("Log") String NodeName, @Optional("Null") String RegPackName) throws Exception {

		sNodeBrowser = browser;
		sNodeURL = NodeURL;
		sNodeName = NodeName;
		sNodeRegPackName = RegPackName;
		// Initialization of project location
		//JFrame frame = new JFrame("InputDialog");
		// prompt the user to enter their project location
		//sProjectPath = JOptionPane.showInputDialog(frame, "Enter Automation Test Suite Path:", "D:\\Roger\\Development");
		sProjectPath = "D:\\a\\1\\a\\Roger";  //D:\\a\\1\\a\\Roger\\Development

		// Initialization of TestSuite folders
		InitializeTestFolderPaths();
		InitializeRegPackColumns();
		InitializeTestCaseColumns();
		InitializeTestStepsColumns();
		InitializeObjectListColumns();

		// Set SystemIndpendencyConfig File values
		LoadSystemIndependencyConfig();

		// Create Test Results Folder
		sTestResultsPath = createfolder(sProjectPath, "TestResults");
		// Create current date and time log folder inside test results folder
		CreateTestResultsFolder();

		// Copy Driver file from test data folder to current log folder
		Copy_File(sTestDataPath + sDriverFile, sTestResultsPath);

		// Create a folder 'TestLog' inside current log folder
		sPathTestLog = createfolder(sTestResultsPath, "TestLog");

		// Set Driver File
		TestDataImport.SetExcelFile(sTestResultsPath, sDriverFile);

		// Get the number of automation tests from sheet "Automation Tests"
		iTotalRegressionPacks = TestDataImport.GetRowCount(sSheetRegressionPacks) - 1;

		// Add summary columns in Regression packs sheet
		// TestReportFunctions.AddColumnsInRegPacksSheet();

		TestDataImport.SetExcelFile(sTestResultsPath, sDriverFile);
		// Read the regression pack names along with run mode
		
		XmlSuite suite = new XmlSuite();
		suite.setName("Suite");
		XmlTest xmlTest1 = new XmlTest(suite);
		xmlTest1.setThreadCount(5);
		xmlTest1.setName("Test");
		List<XmlClass> classes = new ArrayList<XmlClass>();
		
		for (int iTestCase = 1; iTestCase <= iTotalRegressionPacks; iTestCase++) {
			SRegPackTestCaseStatus = "NA";
			sActualResult = "NA";
			TestDataImport.SetExcelFile(sTestResultsPath, sDriverFile);
			String sTestCaseID = TestDataImport.GetCellData(sSheetRegressionPacks, 0, iTestCase);
			String sTestCaseDesc = TestDataImport.GetCellData(sSheetRegressionPacks, 1, iTestCase);
			String sTestCaseRunMode = TestDataImport.GetCellData(sSheetRegressionPacks, 2, iTestCase);
			sTestCaseExpectedResult = TestDataImport.GetCellData(sSheetRegressionPacks, 3, iTestCase);
			if (sTestCaseRunMode.equalsIgnoreCase("YES")) {
				// String sTestCaseID = "Test2";
				
				classes.add(new XmlClass("TestCases."+ sTestCaseID));
				xmlTest1.setXmlClasses(classes) ;
				
			    
				/*Class<?> c = Class.forName("TestCases." + sTestCaseID);
				Object obj = c.newInstance();
				Method method[] = obj.getClass().getMethods();
				for (int i = 0; i < method.length; i++) {

					if ((method[i].getName()).equalsIgnoreCase("MAIN")) {
						method[i].invoke(obj, null);
						SetTestCaseStatus();

						break;
					}
				}*/
			} else {
				sActualResult = "NA";
				SRegPackTestCaseStatus = "No Run";
			}
/*			String sColor = "NA";
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
			
			TestDataImport.SetExcelFile(sTestResultsPath, sDriverFile);
			TestDataImport.setCellData(sSheetRegressionPacks, iTestCase, 4, sActualResult, "NA");
			TestDataImport.setCellData(sSheetRegressionPacks, iTestCase, 5, SRegPackTestCaseStatus, sColor);
			TestDataImport.SetExcelFile(sTestResultsPath, sDriverFile);
*/
		}
			List<XmlSuite> suites = new ArrayList<XmlSuite>();
			suites.add(suite);
			TestNG tng = new TestNG();
			tng.setXmlSuites(suites);
			suite.setFileName("myTemp.xml");
			createXmlFile(suite); 
			tng.run(); 
		    

	}
	public static void createXmlFile(XmlSuite mSuite)  { 
		
		FileWriter writer; 
		try { 
			writer = new FileWriter(new File(System.getProperty("user.dir") + "/myTemp.xml")); 
			writer.write(mSuite.toXml()); 
			writer.flush(); 
			writer.close(); 
		} catch (IOException e) {
			e.printStackTrace(); 
		}
    }
}
