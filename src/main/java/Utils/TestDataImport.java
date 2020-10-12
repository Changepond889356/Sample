package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataImport
{
  
	/* public static XSSFWorkbook ExcelWbook;
	 public static XSSFSheet ExcelWsheet;
	 public static XSSFRow Row;
	 public static XSSFCell Cell;*/
	
    private static XSSFWorkbook ExcelWbook;
    private static XSSFSheet ExcelWsheet;
    private static XSSFCell Cell;
    private static XSSFRow Row;
	 public static XSSFFont font;
	 public static CellStyle style;
	 
	
     public static String Path_TestResults;
    
     //--------------------------Method to set Excel File-----------------------------------------------------
     public static void SetExcelFile(String Path,String FileName) throws Exception

     {
		 try
		 {
		  
		   FileInputStream File = new FileInputStream(Path+FileName);
		   ExcelWbook = new XSSFWorkbook(File);
		  
		   GenericSkins.sFileTestData=FileName;
		 }
		 
		 catch(Exception error_message)
		 
		 {
			 System.out.println("SetExcelFile||Error||"+error_message.getMessage());
		 }
	 
    }
     
     //--------------------------Method to create sheet in Excel File-----------------------------------------------------
     public static void CreateSheet(String sSheetName) throws Exception

     {
		 try
		 {
			 
			 ExcelWsheet = ExcelWbook.createSheet(sSheetName);
			 System.out.println("CreatedSheet:"+sSheetName);
		   
		 }
		 
		 catch(Exception error_message)
		 
		 {
			 System.out.println("CreateSheet||Error||"+error_message.getMessage());
		 }
	 
    }
     //--------------------------Method to create row in a sheet in Excel File-----------------------------------------------------
     public static void CreateCell(int iRow, int iCol, String sValue) throws Exception

     {
		 try
		 {
			
			 Row = ExcelWsheet.createRow(iRow);
			 Row.createCell(iCol).setCellValue(sValue);
		   
		 }
		 
		 catch(Exception error_message)
		 
		 {
			 System.out.println("CreateCell||Error||"+error_message.getMessage());
		 }
	 
    }
     
    //--------------------------Method to Get Data of particular cell and Sheet from Excel File---------------
     
 	// @SuppressWarnings("null")
 	public static String GetCellData(String SheetName,int ColNum,int RowNum)throws Exception
 	 
 	{
 		Object Cellvalue=null;
 		 
 		ExcelWsheet = ExcelWbook.getSheet(SheetName);
 		try
 		{
 		  
 			 Cell = ExcelWsheet.getRow(RowNum).getCell(ColNum);
 			 int type = Cell.getCellType();
 			
 			 //System.out.println("Cell type:"+type);
 			  switch(type)
 			  {
 			  case 0: double x = Cell.getNumericCellValue();
 			              int y = (int) x;
 			              Cellvalue = y;
 			          
 			          break;
 			  case 1: Cellvalue = Cell.getStringCellValue();  
 			          break;
 			  case 2: Cellvalue = "";
			          break;
 			  
 			  }
 			  
 		}
 		catch(Exception error_message)
 		{
 			Cellvalue = "";
 		}
 			  
 		return Cellvalue.toString();
 	
 	}
 	
 	
 	
 	//-------------------------------Method to get row count from particular sheet of Excel File-------------------- 
 	public static int GetRowCount(String SheetName)throws Exception
 		
 	{
 			int RowCount=0;
 		    //System.out.println(SheetName);
 			ExcelWsheet = ExcelWbook.getSheet(SheetName);
 			// System.out.println("Excel Sheet Name:" +ExcelWsheet.getSheetName());
 			try
 			{
 				 RowCount = ExcelWsheet.getLastRowNum()+1;
 				 //System.out.println(RowCount);
 			}
 			catch(Exception e)
 			{
 				 RowCount = 0;
 				 System.out.println("ExcelUtils||GetRowCount||" +e.getMessage());
 			}
 			return RowCount;
 	}
 	
 	
 	
 	//--------Method to get row number that contains particular data in particular column of Excel file--------------
 	public static int GetRowContains(String sContent,int ColNum,String SheetName) throws Exception
 		
 	{
 			ExcelWsheet = ExcelWbook.getSheet(SheetName);
 			//System.out.println("Inside getrowcontains");
 			int row=0;
 			try
 			{
 				//int RowCOunt = ExcelUtils.GetRowCount(SheetName);
 				
 				for(row=0;!sContent.equalsIgnoreCase(TestDataImport.GetCellData(SheetName, ColNum, row));row++);
 				
 				//System.out.println("RowContains:" +row);
 				//return row;
 			}
 			catch(Exception error_message)
 			{
 				System.out.println(error_message.getMessage());
 				//throw error_message;
 			}
 			
 			return row;
 			
 	}
 	
 	//----------------------Method to set data in particular cell of Excel sheet----------------------------------
 	@SuppressWarnings("static-access")
 	public static void setCellData(String SheetName, int RowNum, int ColNum,String Result, String sFontColor) throws Exception
 		
 	{
 	        try
 	        {
 	        	
 	        	ExcelWsheet = ExcelWbook.getSheet(SheetName);
 	            Row  = ExcelWsheet.getRow(RowNum);
 	            Cell = Row.getCell(ColNum);//, Row.RETURN_BLANK_AS_NULL);
 	            if (Cell == null) 
 	            {
 	            	Cell = Row.createCell(ColNum);
 	            }
 	                
 	            font= ExcelWbook.createFont();
 	  		    style = ExcelWbook.createCellStyle();
 	              
 	  		    if(!(sFontColor.equalsIgnoreCase("NA")))
 	  		    {
 	  		    	switch(sFontColor.toUpperCase())
 	  		    	{
 	  		    	   case "GREEN":
 	  		    		   
 	  		    		    font.setColor(IndexedColors.GREEN.getIndex());
 	  		    		    break;
 	  		    		    
 	  		    	   case "RED":
	  		    		   
	  		    		    font.setColor(IndexedColors.RED.getIndex());
	  		    		    break;
	  		    		    
 	  		    	   case "BLUE":
	  		    	
	  		    		    font.setColor(IndexedColors.BLUE.getIndex());
	  		    		    break;
 	  		    		   
 	  		    	}
 	  		    	style.setFont(font);
 	            	Cell.setCellStyle(style);
 	  		    }
 	  		
 	            Cell.setCellValue(Result);
 	           FileOutputStream fileOut = new FileOutputStream( new File(GenericSkins.sTestResultsPath+GenericSkins.sFileTestData));
 	              
 	            ExcelWbook.write(fileOut);
 	            
 	            fileOut.close();
 	            
 	            // ExcelWbook = new XSSFWorkbook(new FileInputStream(Path_TestResults+Constants.File_TestData));
 	               
 	        }
 	        catch(Exception error_message)
 	        {
 	            	 
 	            //DriverScript.bResult = false;
 	            System.out.println("TestDataImport||setCellData||catch||:"+error_message.getMessage());
 	      
 	        }
 	 }
 	
 	public static void writeExcel(String nTestDataPath,String nFileName,String nSheetName, String swriteData, int colNum,String sTestCaseID) throws Exception {
		
 		TestDataImport.SetExcelFile(nTestDataPath, nFileName);
 		File nfile = new File(nTestDataPath+nFileName);
 		FileInputStream ninputStream = new FileInputStream(nfile);
 		XSSFWorkbook excelWbook = new XSSFWorkbook(ninputStream);
 		XSSFSheet excelWsheet = excelWbook.getSheet(nSheetName);
 		XSSFCell nCell;
 	    XSSFRow nRow;
 	    int rowcount = excelWsheet.getLastRowNum();//TestDataImport.GetRowCount(sSheetName);
 		for(int i =1; i<= rowcount; i++) {
 			nRow = excelWsheet.getRow(i);
 			if(nRow.getCell(0).getStringCellValue().equalsIgnoreCase(sTestCaseID)) {
 				nCell = nRow.createCell(colNum);
 				nCell.setCellValue(swriteData);
 				System.out.println("Write Done " +nTestDataPath+nFileName);
 			}
 		}
 		FileOutputStream nfileOut = new FileOutputStream(new File(nTestDataPath+nFileName));   
 		excelWbook.write(nfileOut);
 		nfileOut.close();	
 	}
	
 	
 	public static String[][] readExcel(String filePath,String fileName,String sheetName) throws Exception {
		
 		TestDataImport.SetExcelFile(filePath, fileName);
 		ExcelWsheet = ExcelWbook.getSheet(sheetName);
		int rowcount = ExcelWsheet.getLastRowNum();
		int cellcount = ExcelWsheet.getRow(rowcount).getLastCellNum();
		String data[][] = new String[rowcount][cellcount];		 
		data = getData(data,0,cellcount);
		
		 return data;
	 }
 	public static String[][] getData(String data[][],int firstcell,int cellcount) {
		
		 int rowcount = ExcelWsheet.getLastRowNum();
		 
		 for (int i = 1; i <= rowcount; i++) {
			 Row = ExcelWsheet.getRow(i);
			 for (int j = 0; j < cellcount; j++) {
				 
				 Cell = Row.getCell(j);
				 //firstcell ++;
				 try {
					 if(Cell != null) {
						 if (Cell.getCellType() == Cell.CELL_TYPE_STRING) {
							 data[i - 1][j] = Cell.getStringCellValue();
						 } 
						 else if (Cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
							 data[i - 1][j] = String.valueOf((int)Cell.getNumericCellValue());
						 }
						 else {
							 data[i - 1][j] = String.valueOf(Cell.getNumericCellValue());
						 }
					 }
					 else {
						 //data[i - 1][j] = "NA";
					 }
				 } catch (Exception e) {
					 e.printStackTrace();
				 }
			 }
		 }
		return data;
	 }
 	public static String[] readExcel(String filePath,String fileName,String sheetName,int nRow, String sTestCaseID) throws Exception {
		
 		TestDataImport.SetExcelFile(filePath, fileName);
 		ExcelWsheet = ExcelWbook.getSheet(sheetName);
		int cellcount = ExcelWsheet.getRow(nRow).getLastCellNum();
		String data[] = new String[cellcount];		 
		data = getDataRow(data,nRow,cellcount);		
		return data;
	 }
 	
 	public static String[] getDataRow(String data[],int nRow,int cellcount) {
 		Row = ExcelWsheet.getRow(nRow);
 		for (int j = 0; j < cellcount; j++) {
 			Cell = Row.getCell(j);
 			try {
 				if(Cell != null) {
 					if (Cell.getCellType() == Cell.CELL_TYPE_STRING) {
 						data[j] = Cell.getStringCellValue();
 					} 
 					else if (Cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
 						data[j] = String.valueOf((int)Cell.getNumericCellValue());
 					}
 					else {
 						data[j] = String.valueOf(Cell.getNumericCellValue());
 					}
 				}
 				else {
 					//data[i - 1][j] = "NA";
 				}
 			} catch (Exception e) {
 				e.printStackTrace();
 			}
 		}

		return data;
	 }
}
