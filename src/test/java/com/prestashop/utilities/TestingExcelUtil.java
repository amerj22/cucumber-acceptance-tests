package com.prestashop.utilities;


import java.util.List;
import java.util.Map;

public class TestingExcelUtil {

	public static void main(String[] args) {

		ExcelUtil excelObject = new ExcelUtil("Products.xlsx", "Sheet1");

		System.out.println("colums: " + excelObject.columnCount());

		System.out.println("Rows: " + excelObject.rowCount());

		List<String> columnsNames = excelObject.getColumnsNames();
		System.out.println(columnsNames);

		String[][] dataArray = excelObject.getDataArray();

		for (String[] row : dataArray) {

			for (String value : row) {
				System.out.println(value +" (mulitArray) "+ "\t");

			}
			System.out.println();
	    }
	    
	    List<Map<String,String>> dataList = excelObject.getDataList();
	    
	    System.out.println(dataList+" (Map) ");
	    
	    for (Map<String, String> row : dataList) {
	      System.out.println(row + " (Map row) ");
	    }
	    
	    // name of the product in the 3rd row
	    System.out.println("Name of the PRODUCT in the 3rd row");
	    
	    System.out.println(dataList.get(3).get("Product"));
	    
	    // get data by index
	    System.out.println(excelObject.getCellData(0, 0));
	  }
	  
	  
}
