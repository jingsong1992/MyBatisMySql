package com.cs.ce.vc.toolkit.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.cs.ce.vc.toolkit.common.Common;
import com.cs.ce.vc.toolkit.vo.IssueCategory;





public class ReadExcelIntroduction {

      private int rowStart;
      private int rowEnd;
      
      public ReadExcelIntroduction(int rowStart,int rowEnd){
    	  this.rowStart=rowStart;
    	  this.rowEnd=rowEnd;
      }
      
      
      
      
      public List<IssueCategory>  getIssue_category(){
    	  Workbook wb = Common.getWorkbook();
    	  Sheet sheet = wb.getSheetAt(0);
    	  int id=1;
    	  List<IssueCategory>list_Ctg = new ArrayList<IssueCategory>();
    	  
    	  for(int rowIndex=rowStart;rowIndex<=rowEnd;rowIndex++){
    		  Row row =sheet.getRow(rowIndex);
    		  IssueCategory  issue  = new IssueCategory();
    		  issue.setId(id++);
              
    		  String code = row.getCell(1).getStringCellValue();
    		  String type = row.getCell(1).getStringCellValue();
    		  String description = row.getCell(1).getStringCellValue();
    		  
    		  issue.setCode(code);
    		  issue.setType(type);
    		  issue.setDescription(description);
    		  
    		  list_Ctg.add(issue);  		    
    	  }
    	  return list_Ctg;
      }
	
	

	
	
	
	
	
	
	
	

}
