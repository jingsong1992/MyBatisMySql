package com.cs.ce.vc.toolkit.inter;

import com.cs.ce.vc.toolkit.vo.IssueBundle;
import com.cs.ce.vc.toolkit.vo.IssueCategory;
import com.cs.ce.vc.toolkit.vo.IssueSubCategory;
import com.cs.ce.vc.toolkit.vo.Issues;



public interface MybitsUtils {

	public void addIssueBundle(IssueBundle issuebun);
	public void addIssueCategory(IssueCategory issuecatg);
	public void addIssueSubCategory(IssueSubCategory issuecatg);
	public void addIssueFunc(Issues issues);
}
