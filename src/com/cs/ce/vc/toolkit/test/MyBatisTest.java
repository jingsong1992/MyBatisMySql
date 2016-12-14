package com.cs.ce.vc.toolkit.test;

import java.util.List;

import org.junit.Test;

import com.cs.ce.vc.toolkit.common.Common;
import com.cs.ce.vc.toolkit.excel.ReadExcelIntroduction;
import com.cs.ce.vc.toolkit.excel.ReadExcelModule;
import com.cs.ce.vc.toolkit.excel.ReadExcelProject;
import com.cs.ce.vc.toolkit.excel.ReadExcelVersion_trace;
import com.cs.ce.vc.toolkit.vo.ExcelProject;
import com.cs.ce.vc.toolkit.vo.IssueBundle;
import com.cs.ce.vc.toolkit.vo.IssueCategory;
import com.cs.ce.vc.toolkit.vo.IssueSubCategory;

public class MyBatisTest {
	

	
	
	
	@Test
	public void testConfig(){
		ReadExcelIntroduction Intrd = new ReadExcelIntroduction(12, 28);
		List<IssueCategory> ctList = Intrd.getIssue_category();
		List<IssueBundle> res = new ReadExcelVersion_trace().readExcel();
		List<IssueSubCategory> IssSubCtg = new ReadExcelModule(3, 225)
		.getIssue_sub_cat();
		List<ExcelProject> excPro = new ReadExcelProject().getExcelData();
		
 	   for(IssueCategory ct : ctList){
		  Common.saveByConfig("com.cs.ce.vc.toolkit.inter.MybitsUtils.addIssueCategory", ct);
	   }

	   for(IssueSubCategory issuecatg : IssSubCtg){
		   Common.saveByConfig("com.cs.ce.vc.toolkit.inter.MybitsUtils.addIssueSubCategory", issuecatg);
 	   }

	   

//
//	   for(ExcelProject expro : excPro){
//		   Common.saveByConfig("com.cs.ce.vc.toolkit.inter.MybitsUtils.addExcelProject", expro);
//	   }
//		for(IssueBundle isb:res){
//			Common.saveByConfig("com.cs.ce.vc.toolkit.inter.MybitsUtils.addIssueBundle", isb);
//		}
	   Common.closeSqlSession();
	}
	
	
	@Test
	public void testInterface(){
		
		
		ReadExcelIntroduction Intrd = new ReadExcelIntroduction(12, 28);
		List<IssueCategory> ctList = Intrd.getIssue_category();
		List<IssueBundle> res = new ReadExcelVersion_trace().readExcel();
		List<IssueSubCategory> IssSubCtg = new ReadExcelModule(3, 225)
		.getIssue_sub_cat();
		List<ExcelProject> excPro = new ReadExcelProject().getExcelData();
		
	 	   for(IssueCategory ct : ctList){
	 		  Common.saveByInterface(ct);
	 	   }

	 	   for(IssueSubCategory issuecatg : IssSubCtg){
	 		   Common.saveByInterface(issuecatg);
	  	   }

	 	   

	 //
//	 	   for(ExcelProject expro : excPro){
//	 		   Common.saveByInterface(expro)
//	 	   }
//	 		for(IssueBundle isb:res){
//	 			Common.saveByInterface(isb);
//	 		}
	 		
	 		
	 		Common.closeSqlSession();
	}
}
