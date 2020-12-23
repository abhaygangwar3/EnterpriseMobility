package pckg_XL_Ops;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Cls_XL_Ops {
	
	public static File fileOut =  new File(System.getProperty("user.dir")+"\\Output XL\\OutputSheet.xlsx");
	public static File fileIn =  new File("C:\\Selenium_Automation\\IO Sheets\\InputSheet.xlsx");
	public static String sheetName = "Output";
	public static XSSFWorkbook wb;
	XSSFSheet sheet1;
	
		public static void Data(String excelpath) throws Exception{
			File src=new File(excelpath);
			FileInputStream file=new FileInputStream(src);
			wb=new XSSFWorkbook(file);
		}

		public static String getdata(String Sheetname1,int row,int collumn) throws IOException{
			FileInputStream inputStream = new FileInputStream("C:\\Selenium_Automation\\IO Sheets\\InputSheet.xlsx");
		    Workbook OutWorkbook = new XSSFWorkbook(inputStream);
			Sheet sheet1 = OutWorkbook.getSheet(Sheetname1);
			//sheet1=wb.getSheet(Sheetname1);
			String data=sheet1.getRow(row).getCell(collumn).getStringCellValue();
			OutWorkbook.close();
			return data;
		}

		public static void XL_Output_WithSheetName(String strSheetName, int colNumber, String strWritevalue) throws IOException{	
		    FileInputStream inputStream = new FileInputStream(fileOut);
		    Workbook OutWorkbook = new XSSFWorkbook(inputStream);
			Sheet OutSheet = OutWorkbook.getSheet(strSheetName);
			int rowCount = OutSheet.getLastRowNum();
			Cell cell;
		    Row rowout;
		    //rowCount = rowCount + 1;
		    rowout = OutSheet.getRow(rowCount);
		    cell = rowout.createCell(colNumber - 1);
		    cell.setCellValue(strWritevalue);

		    FileOutputStream OpStream = new FileOutputStream(fileOut);
		    OutWorkbook.write(OpStream);
		    OutWorkbook.close();
		}
		
		public static String XL_Read_WithSheetNameLastRow(String strSheetName,int colNumber) throws IOException{
			 //File file =  new File("D:\\Sel\\Excel_Sel.xlsx");
				String strReadValue = null;
			    FileInputStream inputStream = new FileInputStream(fileIn);
			    Workbook PDWorkbook = new XSSFWorkbook(inputStream);
			    Sheet PDSheet = PDWorkbook.getSheet(strSheetName);
			    int rowNumber = PDSheet.getLastRowNum();
			    Row row = PDSheet.getRow(rowNumber);
			    strReadValue = row.getCell(colNumber-1).getStringCellValue();
			    
			   PDWorkbook.close();
			   // System.out.println("Over");
			    return strReadValue;
		}

		public static void XL_Output_WithSheetNameNextRow(String strSheetName, int colNumber, String strWritevalue) throws Exception{	
		    FileInputStream inputStream = new FileInputStream(fileOut);
		    Workbook OutWorkbook = new XSSFWorkbook(inputStream);
			Sheet OutSheet = OutWorkbook.getSheet(strSheetName);
			int rowCount = OutSheet.getLastRowNum();
			Cell cell;
		    Row rowout;
		    rowCount = rowCount + 1;
		    rowout = OutSheet.getRow(rowCount);
		    if (rowout == null)
		    {
		    	 rowout = OutSheet.createRow(rowCount);   	 
		    }
		    cell = rowout.createCell(colNumber - 1);
		    cell.setCellValue(strWritevalue);

		    FileOutputStream OpStream = null;
		    OpStream = new FileOutputStream(fileOut);
		    OutWorkbook.write(OpStream);
		    OutWorkbook.close();    
		}
		
		public static String XL_Read_WithSheetName(String strSheetName, int rowNumber, int colNumber) throws IOException{
		 //File file =  new File("D:\\Sel\\Excel_Sel.xlsx");
			String strReadValue = null;
		    FileInputStream inputStream = new FileInputStream(fileIn);
		    Workbook PDWorkbook = new XSSFWorkbook(inputStream);
		    Sheet PDSheet = PDWorkbook.getSheet(strSheetName);
		    Row row = PDSheet.getRow(rowNumber-1);
		    strReadValue = row.getCell(colNumber-1).getStringCellValue();
		   
		    
		   PDWorkbook.close();
		   // System.out.println("Over");
		    return strReadValue;
		}

		public static Object[] XL_Read_WithSheetName_ScenarioToExecute(String strSheetName) throws IOException{
			String strReadValue = null;
			String strScenarioToExecute = null;
			int colNumber = 1;
			int reqrow = 0;
		    FileInputStream inputStream = new FileInputStream(fileIn);
		    Workbook PDWorkbook = new XSSFWorkbook(inputStream);
		    Sheet PDSheet = PDWorkbook.getSheet(strSheetName);
		    int rowNumber = PDSheet.getLastRowNum();
		    for(int i=1;i<rowNumber + 1;i++){
		    	Row row = PDSheet.getRow(i);
			    strReadValue = row.getCell(colNumber-1).getStringCellValue();
		    	if(strReadValue.equals("Y")){
		    		strScenarioToExecute = row.getCell(colNumber).getStringCellValue(); 
		    		reqrow = i + 1;
		    		//System.out.println(strScenarioToExecute +" to return "+ reqrow);
		    	}
		    }
		   PDWorkbook.close();
		   return new Object[] {strScenarioToExecute, reqrow};
		}
		
		//In the below function n denotes a value for obtaining Last but n row in a sheet.
		public static String XL_Read_WithSheetNameLastButnElement(String strSheetName,int colNumber,int n) throws IOException{
			 //File file =  new File("D:\\Sel\\Excel_Sel.xlsx");
				String strReadValue = null;
			    FileInputStream inputStream = new FileInputStream(fileIn);
			    Workbook PDWorkbook = new XSSFWorkbook(inputStream);
			    Sheet PDSheet = PDWorkbook.getSheet(strSheetName);
			    int rowNumber = PDSheet.getLastRowNum();
			    Row row = PDSheet.getRow(rowNumber-n);
			    strReadValue = row.getCell(colNumber-1).getStringCellValue();
			    
			   PDWorkbook.close();
			   // System.out.println("Over");
			    return strReadValue;
		}
}