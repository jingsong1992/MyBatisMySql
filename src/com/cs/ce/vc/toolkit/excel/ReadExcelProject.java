package com.cs.ce.vc.toolkit.excel;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.cs.ce.vc.toolkit.common.Common;
import com.cs.ce.vc.toolkit.vo.ExcelProject;
import com.cs.ce.vc.toolkit.vo.Issues;

public class ReadExcelProject {
	

	public List<Issues> getExcelData() {
		
			int k = 1530 ;//insert after sheet function
		  	List<Issues> list = new ArrayList();
			//project
			HSSFSheet hstp = (HSSFSheet) Common.getWorkbook().getSheetAt(3);
			//version_trace
			HSSFSheet hsfv = (HSSFSheet) Common.getWorkbook().getSheetAt(4);
			int rowNum = hstp.getLastRowNum();
		    try {
				for(int i = 2; i <= rowNum; i++){
					Issues ep = new Issues();
					HSSFRow hfrp = hstp.getRow(i);
					if(hfrp == null){
						continue;
					}
					HSSFCell ceref = hfrp.getCell(0);
					HSSFCell gdb_code = hfrp.getCell(1);
					HSSFCell rm_code = hfrp.getCell(2);
					HSSFCell ttype = hfrp.getCell(3);
					HSSFCell probDescription = hfrp.getCell(5);
					HSSFCell report_version = hfrp.getCell(6);
					HSSFCell report_project = hfrp.getCell(7);
					HSSFCell report_time = hfrp.getCell(8);
					HSSFCell sstatus = hfrp.getCell(9);
					HSSFCell fixed_by = hfrp.getCell(10);
					HSSFCell sd = hfrp.getCell(15);
				  //if ce ref is null,read next line
					if(ceref == null || "".equals(ceref)||ceref.getCellType() ==HSSFCell.CELL_TYPE_BLANK){
						continue;
					}
					ep.setId(k);
					ep.setParent_issue_id(k++);
					ep.setCode(Common.getCellValue(ceref));
					ep.setGdb_code(Common.getCellValue(gdb_code));
					ep.setRm_code(Common.getCellValue(rm_code));
					String type = identiType(Common.getCellValue(ttype));
					ep.setType(type);
					ep.setDescription(Common.getCellValue(probDescription));
					ep.setReport_version(Common.getCellValue(report_version));
					ep.setReport_project(Common.getCellValue(report_project));
					Timestamp rpTime = stdTimeFormat( Common.getCellValue(report_time));
					ep.setReport_time(rpTime);
					String status = identiStatus(Common.getCellValue(sstatus));
					ep.setStatus(status);
					ep.setFixed_by(Common.getCellValue(fixed_by));
					ep.setSd(Common.getCellValue(sd));
					//在version trace表根据ce ref得到相应的fixed version
					String fixedversion = getFixedversion(hsfv,Common.getCellValue(ceref));
					ep.setFixed_version(fixedversion);
					String title = getTitle(Common.getCellValue(probDescription));
					ep.setTitle(title);
					list.add(ep);
					}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
    }
			
	
		//replace closed with C,replace pending with P,replace canceled with C
		public String  identiStatus(String status){
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
		public String identiType(String type){
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
		
		public String getFixedversion(HSSFSheet hsfv,String str) throws IOException{
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
		public Timestamp stdTimeFormat(String content) throws ParseException {
			if (content == null || content.equals("")) {
				return null;

			} else {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy.MM.dd");
				Date date = sf.parse(content);
				Timestamp t1 = new Timestamp(date.getTime());
				return t1;
			}
		}
		
		public String getTitle(String content) {
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
