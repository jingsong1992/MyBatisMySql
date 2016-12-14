package com.cs.ce.vc.toolkit.test;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.cs.ce.vc.toolkit.common.Common;
import com.cs.ce.vc.toolkit.excel.ReadExcelFunction;
import com.cs.ce.vc.toolkit.excel.ReadExcelIntroduction;
import com.cs.ce.vc.toolkit.excel.ReadExcelModule;
import com.cs.ce.vc.toolkit.excel.ReadExcelProject;
import com.cs.ce.vc.toolkit.excel.ReadExcelVersion_trace;
import com.cs.ce.vc.toolkit.inter.MybitsUtils;
import com.cs.ce.vc.toolkit.vo.ExcelProject;
import com.cs.ce.vc.toolkit.vo.IssueBundle;
import com.cs.ce.vc.toolkit.vo.IssueCategory;
import com.cs.ce.vc.toolkit.vo.IssueSubCategory;
import com.cs.ce.vc.toolkit.vo.Issues;

public class MyBatisTest {
	

	
//	
//	
//	@Test
//	public void testConfig(){
//		ReadExcelIntroduction Intrd = new ReadExcelIntroduction(12, 28);
//		List<IssueCategory> ctList = Intrd.getIssue_category();
//		List<IssueBundle> res = new ReadExcelVersion_trace().readExcel();
//		List<IssueSubCategory> IssSubCtg = new ReadExcelModule(3, 225)
//		.getIssue_sub_cat();
//		List<ExcelProject> excPro = new ReadExcelProject().getExcelData();
//		
// 	   for(IssueCategory ct : ctList){
//		  Common.saveByConfig("com.cs.ce.vc.toolkit.inter.MybitsUtils.addIssueCategory", ct);
//	   }
//
//	   for(IssueSubCategory issuecatg : IssSubCtg){
//		   Common.saveByConfig("com.cs.ce.vc.toolkit.inter.MybitsUtils.addIssueSubCategory", issuecatg);
// 	   }
//
//	   
//
////
////	   for(ExcelProject expro : excPro){
////		   Common.saveByConfig("com.cs.ce.vc.toolkit.inter.MybitsUtils.addExcelProject", expro);
////	   }
////		for(IssueBundle isb:res){
////			Common.saveByConfig("com.cs.ce.vc.toolkit.inter.MybitsUtils.addIssueBundle", isb);
////		}
//	   Common.closeSqlSession();
//	}
//	
//	
//	@Test
//	public void testInterface(){
//		
//		
//		ReadExcelIntroduction Intrd = new ReadExcelIntroduction(12, 28);
//		List<IssueCategory> ctList = Intrd.getIssue_category();
//		List<IssueBundle> res = new ReadExcelVersion_trace().readExcel();
//		List<IssueSubCategory> IssSubCtg = new ReadExcelModule(3, 225)
//		.getIssue_sub_cat();
//		List<ExcelProject> excPro = new ReadExcelProject().getExcelData();
//		
//	 	   for(IssueCategory ct : ctList){
//	 		  Common.saveByInterface(ct);
//	 	   }
//
//	 	   for(IssueSubCategory issuecatg : IssSubCtg){
//	 		   Common.saveByInterface(issuecatg);
//	  	   }
//
//	 	   
//
//	 //
////	 	   for(ExcelProject expro : excPro){
////	 		   Common.saveByInterface(expro)
////	 	   }
////	 		for(IssueBundle isb:res){
////	 			Common.saveByInterface(isb);
////	 		}
//	 		
//	 		
//	 		Common.closeSqlSession();
//	}
	public static void main(String[] args) throws IOException{		
        long starTime=System.currentTimeMillis();
        ReadExcelFunction ref = new ReadExcelFunction();
        List<Issues> list_Issues = ref.getIssues();
        long  endTime=System.currentTimeMillis();	   
        System.out.println(endTime-starTime); 
        SqlSession session=Common.sqlSession;
        MybitsUtils mybatisuti = session.getMapper(MybitsUtils.class);
        for(int i = 0;i<list_Issues.size();i++){
        	mybatisuti.addIssueFunc(list_Issues.get(i));
        	}
	   session.commit();      
	   session.close();        	
	}
}
