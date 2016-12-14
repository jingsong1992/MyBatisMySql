package com.cs.ce.vc.toolkit.excel;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.cs.ce.vc.toolkit.common.Common;
import com.cs.ce.vc.toolkit.vo.IssueSubCategory;





public class ReadExcelModule {

    private int rowStart;
    private int rowEnd;

	
    public  ReadExcelModule(int rowStart,int rowEnd){
  	  this.rowStart=rowStart;
  	  this.rowEnd=rowEnd;

    }
    
    
    
	public List<IssueSubCategory>  getIssue_sub_cat(){
		  Workbook wb = Common.getWorkbook();
    	  Sheet sheet = wb.getSheetAt(1);
    	  int id=1;
    	  List<IssueSubCategory>list_sub_Ctg = new ArrayList<IssueSubCategory>();
    	  
    	  for(int rowIndex=rowStart;rowIndex<=rowEnd;rowIndex++){
    		  Row row =sheet.getRow(rowIndex);
    		  
    		  
    		  if(row!=null){
    		  IssueSubCategory  issue  = new IssueSubCategory();
		    		  if(row.getCell(0)!=null && row.getCell(4)!=null){
		    		  String code = row.getCell(0).getStringCellValue();
		    		  String description = row.getCell(4).getStringCellValue();
				    		  if(!"".equals(code) && code.length()!=7){
				    			 
						    		  issue.setId(id++);
						    		  int cat_id = Integer.parseInt(code.substring(5,7));
						    		  issue.setCategory_id(cat_id);
						    		  issue.setCode(code);
						    		  issue.setDescription(description);				    		  
						    		  list_sub_Ctg.add(issue);
//						    		  System.out.println(code+":"+cat_id);
				    		  }
		    		  }
    		  }
    		    
    	  }
    	    
    	  return list_sub_Ctg;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		new ReadExcelModule(3, 225).getIssue_sub_cat();
	}
	
	

}
