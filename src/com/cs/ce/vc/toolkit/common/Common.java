package com.cs.ce.vc.toolkit.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cs.ce.vc.toolkit.inter.MybitsUtils;
import com.cs.ce.vc.toolkit.vo.IssueBundle;
import com.cs.ce.vc.toolkit.vo.IssueCategory;
import com.cs.ce.vc.toolkit.vo.IssueSubCategory;
import com.cs.ce.vc.toolkit.vo.Issues;

public class Common {

	public static SqlSession sqlSession;
	public static String PATH;

	static {
		try {
			Properties prop = new Properties();
			InputStream is = Common.class
					.getResourceAsStream("/path.properties");
			prop.load(is);
			PATH = prop.getProperty("path");
			Reader reader = Resources.getResourceAsReader("Config.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(reader);
			sqlSession = sqlSessionFactory.openSession();

			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Workbook getWorkbook() {
		InputStream is;
		Workbook wb = null;
		try {
			is = new FileInputStream(PATH);

			if (PATH.endsWith("xls")) {
				wb = new HSSFWorkbook(is);
			} else if (PATH.endsWith("xlsx")) {
				wb = new XSSFWorkbook(is);
			}
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wb;
	}

	public static String getCellValue(Cell cell) {

		if (cell == null||cell.getCellType() == Cell.CELL_TYPE_BLANK)
			return "";

		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

			return cell.getStringCellValue();

		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {

			return String.valueOf(cell.getBooleanCellValue());

		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

			return cell.getCellFormula();

		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

			return String.valueOf(cell.getNumericCellValue());

		}
		return "";
	}

	// add method of getCellTime
	public static Timestamp getCellTime(Cell cell){                                 
		if(cell==null||"".equals(cell)||cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
			return null;
		}else if((cell.getCellType() == Cell.CELL_TYPE_NUMERIC)&&HSSFDateUtil.isCellDateFormatted(cell)){    	
			    double d = cell.getNumericCellValue();  
		        Date date = HSSFDateUtil.getJavaDate(d); 
		        SimpleDateFormat dformat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        return Timestamp.valueOf(dformat.format(date)); 	
	    }
		return null;
		}
	
	public static void saveByConfig(String nameSpace, Object obj) {

		sqlSession.insert(nameSpace, obj);
		sqlSession.commit();
	}
	//asdf
	
	
	public static MybitsUtils getMybitsUtils(){
		MybitsUtils mybatisUtils=sqlSession.getMapper(MybitsUtils.class);
		return mybatisUtils;
	}
	
	public static void commit(){
		sqlSession.commit();
	}
	
	
	
	
	public static void saveByInterface(Object obj){
		MybitsUtils mybatisUtils=sqlSession.getMapper(MybitsUtils.class);
		if(obj instanceof IssueBundle){
			IssueBundle isb=(IssueBundle) obj;
			mybatisUtils.addIssueBundle(isb);
		}else if(obj instanceof IssueCategory){
			IssueCategory isc=(IssueCategory) obj;
			mybatisUtils.addIssueCategory(isc);
		}else if(obj instanceof IssueSubCategory){
			IssueSubCategory issc=(IssueSubCategory) obj;
			mybatisUtils.addIssueSubCategory(issc);
		}else if(obj instanceof Issues){
			Issues issues=(Issues) obj;
			mybatisUtils.addIssueFunc(issues);
		}

	}

	public static void closeSqlSession() {
		if (sqlSession != null) {
			sqlSession.close();
		}
	}
}
