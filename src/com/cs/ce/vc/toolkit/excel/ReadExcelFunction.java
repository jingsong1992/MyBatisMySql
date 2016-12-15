package com.cs.ce.vc.toolkit.excel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.cs.ce.vc.toolkit.common.Common;
import com.cs.ce.vc.toolkit.vo.Issues;


public class ReadExcelFunction {
	
	public List<Issues> getIssues(){
		List<Issues> list_Issues = new ArrayList<Issues>();
			Workbook wb =  Common.getWorkbook();
			HSSFSheet hssfSheet = (HSSFSheet)wb.getSheetAt(2);
			String valueType = null;
			String title= null;
			String CellDesc = null;
			String value = null;
			String cellFix_version = null;
			int id = 0;
			ReadExcelFunction ref = new ReadExcelFunction();
			Map<String, String> map = ref.SheetFive();
			for(int rowNum = 2;rowNum<hssfSheet.getLastRowNum()+1;rowNum++){                    
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if(hssfRow!=null){
				HSSFCell cellCode = hssfRow.getCell(0);                                         
				HSSFCell cellGdbCode = hssfRow.getCell(2);
				HSSFCell cellRmCode = hssfRow.getCell(3);
				HSSFCell cellType = hssfRow.getCell(4);
				if(Common.getCellValue(cellType) == ""){                                              
					valueType = null;
				}else if("b".equalsIgnoreCase(Common.getCellValue(cellType).substring(0, 1))){
					valueType = "B";
				}else if("e".equalsIgnoreCase(Common.getCellValue(cellType).substring(0, 1))){
					valueType = "E";
				}
				HSSFCell cellDescription = hssfRow.getCell(6);
				if(Common.getCellValue(cellDescription)==""){
					title = null;
					CellDesc = null;
				}else if(Common.getCellValue(cellDescription).length()>64){
						title = Common.getCellValue(cellDescription).substring(0, 61)+"...";
						CellDesc = Common.getCellValue(cellDescription);
					}else{
						title = Common.getCellValue(cellDescription);
						CellDesc = Common.getCellValue(cellDescription) ;
				}
				HSSFCell cellReportVersion = hssfRow.getCell(7);
				HSSFCell cellReportProject = hssfRow.getCell(8);
				HSSFCell cellTime = hssfRow.getCell(9);
				HSSFCell cellStatus = hssfRow.getCell(10);
				HSSFCell cellFixedBy = hssfRow.getCell(11);
				HSSFCell cellSD = hssfRow.getCell(16);
				HSSFCell cellFixedTime = hssfRow.getCell(18);
				HSSFCell cellClosedReason = hssfRow.getCell(22);				
				if(Common.getCellValue(cellStatus)==""){
					value = null;
				}else if("c".equalsIgnoreCase(Common.getCellValue(cellStatus).substring(0, 1))){
					value = "C";
				}else if("p".equalsIgnoreCase(Common.getCellValue(cellStatus).substring(0, 1))){
					value = "P";
				}else {
					value = "M";
				}
				if(Common.getCellValue(cellCode)== ""){      
					continue;
				}else{
					if(!map.containsKey(Common.getCellValue(cellCode))){         
						cellFix_version = null;
				}else{
					cellFix_version =   (String) map.get(Common.getCellValue(cellCode));
				}
				Issues issues = new Issues();
				id++;  
				issues.setId(id);
				issues.setParent_issue_id(id);
				issues.setCode(Common.getCellValue(cellCode));
				issues.setTitle(title);
				issues.setDescription(CellDesc);
				issues.setGdb_code(Common.getCellValue(cellGdbCode));
				issues.setRm_code(Common.getCellValue(cellRmCode));
				issues.setType(valueType);  
				issues.setReport_version(Common.getCellValue(cellReportVersion));
				issues.setReport_project(Common.getCellValue(cellReportProject));
				issues.setReport_time(Common.getCellTime(cellTime));
				issues.setFixed_version(cellFix_version);
				issues.setStatus(value);
				issues.setFixed_by(Common.getCellValue(cellFixedBy));
				issues.setFixed_time(Common.getCellTime(cellFixedTime));
				issues.setSd(Common.getCellValue(cellSD));
				issues.setClosed_reason(Common.getCellValue(cellClosedReason));
				list_Issues.add(issues);
				}
			}
		}
	return list_Issues;  
		
   }
	
	public  Map<String, String> SheetFive(){
		Workbook wb =  Common.getWorkbook();
		HSSFSheet hssfSheet = (HSSFSheet)wb.getSheetAt(4);
		Map<String, String> map = new HashMap<String, String>();
		for(int rowNum = 2;rowNum<hssfSheet.getLastRowNum()+1;rowNum++){
		HSSFRow hssfRow = hssfSheet.getRow(rowNum);
		if(hssfRow != null){
			HSSFCell cellCode = hssfRow.getCell(0);
			HSSFCell cellGdbCode = hssfRow.getCell(2);
			map.put(Common.getCellValue(cellCode), Common.getCellValue(cellGdbCode));
		}
		}
		return map;
	}
}

