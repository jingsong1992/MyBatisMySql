package com.cs.ce.vc.toolkit.test;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
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
import com.mysql.jdbc.log.Log4JLogger;

public class MyBatisTest {
		
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
//		List<Issues> excPro = new ReadExcelProject().getExcelData();
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
	public static void main(String[] args){	
		try{
        long starTime=System.currentTimeMillis();
        SqlSession session=Common.sqlSession;
        MybitsUtils mybatisuti = session.getMapper(MybitsUtils.class);
//        ReadExcelFunction ref = new ReadExcelFunction();
//        List<Issues> list_Issues = ref.getIssues();
//        for(int i = 0;i<list_Issues.size();i++){
//        	mybatisuti.addIssueFunc(list_Issues.get(i));
//        	}
//        List<Issues> excPro = new ReadExcelProject().getExcelData();
//	   for(Issues expro : excPro){
//		   mybatisuti.addIssueFunc(expro);
// }
//	  List<IssueBundle> res = new ReadExcelVersion_trace().readExcel();
//	 for(IssueBundle isb:res){
//		 mybatisuti.addIssueBundle(isb);
// }
		ReadExcelIntroduction Intrd = new ReadExcelIntroduction(12, 28);
		List<IssueCategory> ctList = Intrd.getIssue_category();
	   for(IssueCategory ct : ctList){
		   mybatisuti.addIssueCategory(ct);
	   }
//		List<IssueSubCategory> IssSubCtg = new ReadExcelModule(3, 225)
//		.getIssue_sub_cat();
//	   for(IssueSubCategory issuecatg : IssSubCtg){
//		   mybatisuti.addIssueSubCategory(issuecatg);
//	   }
	   long  endTime=System.currentTimeMillis();
	   System.out.println(endTime-starTime);	
	   session.commit();      
	   session.close(); 
		} catch (Exception e) {
		    PropertyConfigurator.configure("src/log4j.properties");                  //add catch errof of log4j 
			Logger logger3 = Logger.getLogger("MyBatisTest.class");
			logger3.error(e.getMessage());
		}
	}
	


}
