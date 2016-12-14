package com.cs.ce.vc.toolkit.excel;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;

import com.cs.ce.vc.toolkit.common.Common;
import com.cs.ce.vc.toolkit.vo.IssueBundle;

public class ReadExcelVersion_trace {
	

	public  ArrayList<IssueBundle> readExcel() {
		Workbook wb = Common.getWorkbook();
		Sheet sheet = (Sheet) wb.getSheetAt(4);
		Row row = null;

		ArrayList<IssueBundle> result = new ArrayList<IssueBundle>();

		for (int i = 2; i < sheet.getLastRowNum(); i++) {

			row = sheet.getRow(i);

			IssueBundle isb = new IssueBundle();

			for (Cell c : row) {
				String rs = "";
				boolean isMerge = isMergedRegion(sheet, i, c.getColumnIndex());
				// 判断是否含有合并单元格
				if (isMerge) {
					rs = getMergedRegionValue(sheet, row.getRowNum(), c.getColumnIndex());
				} else {
					rs = Common.getCellValue(c);
				}

				if (c.getColumnIndex() == 3) {
					isb.setBundle_version(rs);
				} else if (c.getColumnIndex() == 4) {
					isb.setIshead(rs);
				} else if (c.getColumnIndex() == 5) {
					isb.setStatus(rs);
				}

			}
			result.add(isb);

		}

		return filterExcel(result);

	}

	private  boolean isMergedRegion(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	public  String getMergedRegionValue(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();

		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();

			if (row >= firstRow && row <= lastRow) {

				if (column >= firstColumn && column <= lastColumn) {
					Row fRow = sheet.getRow(firstRow);
					Cell fCell = fRow.getCell(firstColumn);
					return Common.getCellValue(fCell);
				}
			}
		}

		return null;
	}

	public  ArrayList<IssueBundle> filterExcel(ArrayList<IssueBundle> result) {
		ArrayList<IssueBundle> list = new ArrayList<IssueBundle>();

		IssueBundle isb = null;

		for (int i = 0; i < result.size(); i++) {
			String bundle_version = result.get(i).getBundle_version();
			String ishead = result.get(i).getIshead();
			if (ishead.equals("Yes")) {
				ishead = "Y";
			} else if (ishead.equals("No")) {
				ishead = "N";
			} else {
				ishead = "";
			}
			String status = result.get(i).getStatus();
			if (status.equals("To be bundled")) {
				status = "T";
			} else if (status.equals("Bundled")) {
				status = "B";
			} else if (status.equals("No need")) {
				status = "N";
			} else {
				status = "";
			}
			if (!bundle_version.equals("/") && !bundle_version.equals("")) {
				if (bundle_version.contains("/")) {
					String[] bundle_versions = bundle_version.split("/");
					for (int j = 0; j < bundle_versions.length; j++) {
						isb = new IssueBundle();
						isb.setBundle_version(bundle_versions[j]);
						isb.setIshead(ishead);
						isb.setStatus(status);
						list.add(isb);
					}
				} else if (bundle_version.contains("(")) {
					String[] bundle_versions = bundle_version.split("\\(");
					for (int j = 0; j < bundle_versions.length; j++) {
						isb = new IssueBundle();
						if (bundle_versions[j].contains(")"))
							bundle_versions[j] = bundle_versions[j].substring(0, bundle_versions[j].length() - 1);
						isb.setBundle_version(bundle_versions[j]);
						isb.setIshead(ishead);
						isb.setStatus(status);
						list.add(isb);
					}
				} else {
					isb = new IssueBundle();
					isb.setBundle_version(bundle_version);
					isb.setIshead(ishead);
					isb.setStatus(status);
					list.add(isb);
				}
			}
		}
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setId(i + 1);
			list.get(i).setIssue_id(i + 1);
		}
		return list;
	}
}
