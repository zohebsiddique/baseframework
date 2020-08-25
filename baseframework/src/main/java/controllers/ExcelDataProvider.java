package controllers;

import java.io.File;
import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import utils.ExcelUtils;

public class ExcelDataProvider {
	@DataProvider(name="multiSheetExcelRead", parallel=true)
	public static Object[][] multiSheetExcelRead(Method method) throws Exception
	{
		File file = new File("./testdata/");
		String SheetName = method.getName();
		System.out.println(SheetName);
		Object testObjArray[][] = ExcelUtils.getTableArray(file.getAbsolutePath(), SheetName);
		return testObjArray;
	}
	
	@DataProvider(name="excelSheetNameAsMethodName",parallel=true)
	public static Object[][] excelSheetNameAsMethodName(Method method) throws Exception
	{
		File file = new File("testdata/"+method.getName()+".xlsx");
		Object testObjArray[][] = ExcelUtils.getTableArray(file.getAbsolutePath());
		return testObjArray;
	}
}
