package com.EmpSystem.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.EmpSystem.entity.Employee;


public class Helper {
//
//	id;
//	private String name;
//	private String address;
//	private String email;
//	private String phone;
//	private Integer salary
	public static String[] Header = { "Id", "Name", "Email", "Phone","Salary" };

	public static String Sheet_Name = "employee";

	public static ByteArrayInputStream dataToExcel(List<Employee> list) {
		
		// create workbook
		
		Workbook workbook = new XSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			

			// create sheet
			Sheet sheet = workbook.createSheet(Sheet_Name);

			// create rows: header rows
			Row row = sheet.createRow(0);

			for (int i = 0; i < Header.length; i++) {

				Cell cell = row.createCell(i);
				cell.setCellValue(Header[i]);

			}

			// values rows
			int rowIndex = 1;
			for (Employee emp : list) {

				Row dataRow = sheet.createRow(rowIndex);
				rowIndex++;
				dataRow.createCell(0).setCellValue(emp.getId());
				dataRow.createCell(1).setCellValue(emp.getName());
				dataRow.createCell(2).setCellValue(emp.getEmail());
				dataRow.createCell(3).setCellValue(emp.getPhone());
				dataRow.createCell(4).setCellValue(emp.getSalary());
			}

			workbook.write(out);

		} catch (Exception e) {

			e.printStackTrace();

			System.out.println("fail to import data into excell");

		} finally {

			try {
				workbook.close();
				out.close();
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

		return new ByteArrayInputStream(out.toByteArray());

	}

}
