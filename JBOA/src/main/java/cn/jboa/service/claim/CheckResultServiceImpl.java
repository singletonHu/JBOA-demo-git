package cn.jboa.service.claim;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jboa.common.Constants;
import cn.jboa.dao.claim.CheckResultDao;
import cn.jboa.dao.claim.ClaimVoucherDao;
import cn.jboa.dao.emp.EmployeeDao;
import cn.jboa.entity.CheckResult;
import cn.jboa.entity.ClaimVoucher;

@Service("checkResultService")
@Transactional
public class CheckResultServiceImpl implements CheckResultService 
{
	@Autowired
	@Qualifier("claimVoucherDao")
	private ClaimVoucherDao claimVoucherDao;
	
	@Autowired
	@Qualifier("checkResultDao")
	private CheckResultDao checkResultDao;

	@Autowired
	@Qualifier("empDao")
	private EmployeeDao empDao;
	
	@Override
	public void saveCheckResult(CheckResult checkResult) 
	{
		checkResult.setCheckTime(new Date());
		checkResultDao.save(checkResult);
		
		ClaimVoucher claimVoucher = claimVoucherDao.get(checkResult.getClaimId());
		updateCliamVoucher(claimVoucher, checkResult.getResult(), checkResult.getCheckEmployee().getSysPosition().getNameCn());
		claimVoucher.setModifyTime(checkResult.getCheckTime());
	}

	/**
	 * 更新报销单状态及待处理人
	 * @param claimVoucher
	 * @param checkResult
	 * @param position
	 */
	protected void updateCliamVoucher(ClaimVoucher claimVoucher, String checkResult, String position)
	{
		if (Constants.CHECKRESULT_PASS.equals(checkResult))
		{
			if (Constants.POSITION_FM.equals(position))
			{
				if (claimVoucher.getTotalAccount() >= 5000)
				{
					claimVoucher.setStatus(Constants.CLAIMVOUCHER_APPROVING);
					claimVoucher.setNextDeal(empDao.getGM());
				}
				else
				{
					claimVoucher.setStatus(Constants.CLAIMVOUCHER_APPROVED);
					claimVoucher.setNextDeal(empDao.getCashier());
				}
			}
			else if (Constants.POSITION_GM.equals(position))
			{
				claimVoucher.setStatus(Constants.CLAIMVOUCHER_APPROVED);
				claimVoucher.setNextDeal(empDao.getCashier());
			}
			else if (Constants.POSITION_CASHIER.equals(position))
			{
				claimVoucher.setStatus(Constants.CLAIMVOUCHER_PAID);
				claimVoucher.setNextDeal(null);
			}
		}
		else if (Constants.CHECKRESULT_REJECT.equals(checkResult))
		{
			claimVoucher.setStatus(Constants.CLAIMVOUCHER_TERMINATED);
			claimVoucher.setNextDeal(null);
		}
		else if (Constants.CHECKRESULT_BACK.equals(checkResult))
		{
			claimVoucher.setStatus(Constants.CLAIMVOUCHER_BACK);
			claimVoucher.setNextDeal(claimVoucher.getCreator());
		}
	}
	
	
	
	
	

	public void setEmpDao(EmployeeDao empDao) {
		this.empDao = empDao;
	}

	public void setClaimVoucherDao(ClaimVoucherDao claimVoucherDao) {
		this.claimVoucherDao = claimVoucherDao;
	}

	public void setCheckResultDao(CheckResultDao checkResultDao) {
		this.checkResultDao = checkResultDao;
	}

}
