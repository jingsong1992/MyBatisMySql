package com.cs.ce.vc.toolkit.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import com.cs.ce.vc.toolkit.common.Common;
import com.cs.ce.vc.toolkit.vo.ExcelProject;

public class ReadExcelProject {
	

	public  List<ExcelProject> getExcelData() {
			int k = 1530 ;//insert after sheet function
		  	List<ExcelProject> list = new ArrayList();
		  	Workbook wb =  Common.getWorkbook();
			//project
			HSSFSheet hstp = (HSSFSheet)wb.getSheetAt(3);
			
			int rowNum = hstp.getLastRowNum();
			for(int i = 2; i <= rowNum; i++){
				ExcelProject ep = new ExcelProject();
				HSSFRow hfrp = hstp.getRow(i);
				if(hfrp == null){
					continue;
				}

				
			    HSSFCell ceref = hfrp.getCell(0);
			    
			  //if ce ref is null,read next line
				if(ceref == null || "".equals(ceref)||ceref.getCellType() ==HSSFCell.CELL_TYPE_BLANK){
					continue;
				}
				ep.setId(k);
				ep.setParent_issue_id(k++);
				ep.setCode(Common.getCellValue(ceref));
				HSSFCell gdb_code = hfrp.getCell(1);
				ep.setGdb_code(Common.getCellValue(gdb_code));
				HSSFCell rm_code = hfrp.getCell(2);
			ep.setRm_code(Common.getCellValue(rm_code));
				HSSFCell ttype = hfrp.getCell(3);
				String type = identiType(Common.getCellValue(ttype));
				ep.setType(type);
				HSSFCell probDescription = hfrp.getCell(5);
				ep.setDescription(Common.getCellValue(probDescription));
				HSSFCell report_version = hfrp.getCell(6);
				ep.setReport_version(Common.getCellValue(report_version));
				HSSFCell report_project = hfrp.getCell(7);
				ep.setReport_project(Common.getCellValue(report_project));
				HSSFCell report_time = hfrp.getCell(8);
				Timestamp rpTime;
				try {
					rpTime = stdTimeFormat( Common.getCellValue(report_time));
					ep.setReport_time(rpTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				HSSFCell sstatus = hfrp.getCell(9);
				String status = identiStatus(Common.getCellValue(sstatus));
				ep.setStatus(status);
				HSSFCell fixed_by = hfrp.getCell(10);
				ep.setFixed_by(Common.getCellValue(fixed_by));
				HSSFCell sd = hfrp.getCell(15);
				ep.setSd(Common.getCellValue(sd));
				//在version trace表根据ce ref得到相应的fixed version
				HSSFSheet hsfv = (HSSFSheet) Common.getWorkbook().getSheetAt(4);
				String fixedversion;
				try {
					fixedversion = getFixedversion(hsfv,Common.getCellValue(ceref));
					ep.setFixed_version(fixedversion);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String title = getTitle(Common.getCellValue(probDescription));
				ep.setTitle(title);
				list.add(ep);
				
			 }
			return list;
	 }		
	
		//replace closed with C,replace pending with P,replace canceled with C
		public  String  identiStatus(String status){
			String stanstatus = "";
			if (status.equalsIgnoreCase("closed")) {
				stanstatus = "C";
			} else if (status.equalsIgnoreCase("pending")) {
				stanstatus = "P";
			} else if (status.equalsIgnoreCase("canceled")) {
				stanstatus = "N";
			}
			return stanstatus;
			
		}
		//replace bug with B,replace enhance E
		public  String identiType(String type){
			String spectype = null;
			if (type.equalsIgnoreCase("bug")) {
				spectype = "B";
			} else if (type.equalsIgnoreCase("enhance")) {
				spectype = "E";
			} else {
				spectype = "";
			}
			return spectype;
			
		}
		public  String getFixedversion(HSSFSheet hsfv,String str) throws IOException{
			//Version trace
				boolean isMatch = false;
				String vtfixversion = null;
				for(int m = 2 ; m < hsfv.getLastRowNum(); m++){
					HSSFRow vrow = hsfv.getRow(m);
					if(vrow == null){
						continue;
					}
					HSSFCell cfcell = vrow.getCell(0);
					String  ceref= Common.getCellValue(cfcell);
						if(ceref.equals(str)){
							 isMatch = true;
						}
						else{
							continue;
						}
						if(isMatch){
							//get fixed_version from sheet version trace according ce ref in sheet project
							vtfixversion  = Common.getCellValue(vrow.getCell(2));
							break;
						}
					
				}
				return vtfixversion;
			}
		//set time type Timestamp
		public  Timestamp stdTimeFormat(String content) throws ParseException {
			if (content == null || content.equals("")) {
				return null;

			} else {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy.MM.dd");
				Date date = sf.parse(content);
				Timestamp t1 = new Timestamp(date.getTime());
				return t1;
			}
		}
		
		public  String getTitle(String content) {
			String title = null;
			// get  problem description,if its length is greater than 64 ,intercept the top 61,replace the next with ... 
			if(content.length()==0){
				return null;
			}
			else if(content.length() > 64){
					title =content .substring(0, 61);
					title+="...";
				}
			else{
				//if its length is less than 64 ,set title description
					title = content;
			}
			
			return title;
		}

}
