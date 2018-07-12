package cn.jboa.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cn.jboa.entity.CheckResult;
import cn.jboa.service.claim.CheckResultService;

public class CheckResultAction extends BaseAction<CheckResult>
{
	private static final long serialVersionUID = 1L;

	private CheckResult checkResult;
	
	@Autowired
	@Qualifier("checkResultService")
	private CheckResultService checkResultService;
	
	public String checkClaimVoucher()
	{
		checkResult.setCheckEmployee(getCurrentEmployee());
		checkResultService.saveCheckResult(checkResult);
		return "redirectList";
	}

	
	
	public CheckResult getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(CheckResult checkResult) {
		this.checkResult = checkResult;
	}

	public void setCheckResultService(CheckResultService checkResultService) {
		this.checkResultService = checkResultService;
	}
	
	
}
