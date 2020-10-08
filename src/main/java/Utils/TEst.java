package Utils;

public class TEst extends GenericSkins {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		sProjectPath = "./";

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

		// Create a folder 'Screenshots' inside current testresults folder
		sScreenShotFolder = createfolder(sTestResultsPath, "ScreenShots");
		Copy_File(sTestDataPath + "Loads.xlsx", sTestResultsPath);
		TestDataImport.writeExcel(sTestResultsPath, 
				"Loads.xlsx", "View Load", "ASD78A", 6, "Loads_TC002");
	}

}
