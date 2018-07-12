package cn.jboa.entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * 报销单实体类。
 *
 */
@Entity
@Table(name="`BIZ_CLAIM_VOUCHER`")
public class ClaimVoucher implements java.io.Serializable {

	private static final long serialVersionUID = -8423258065596709084L;
	@Id
	@Column(name="`ID`")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_voucher")
	@SequenceGenerator(name="seq_voucher",sequenceName="SEQ_VOUCHER")
	private Long id;					// 报销单编号
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="`CREATE_SN`")
	private Employee creator;			// 申请人
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="`NEXT_DEAL_SN`")
	private Employee nextDeal;			// 待处理人
	
	@Column(name="`CREATE_TIME`")
	private Date createTime;			// 申请时间
	
	@Column(name="`EVENT`")
	private String event;				// 报销事由
	
	@Column(name="`TOTAL_ACCOUNT`")
	private Double totalAccount = 0d;	// 报销总金额
	
	@Column(name="`STATUS`")
	private String status;				// 审核状态
	
	@Column(name="`MODIFY_TIME`")
	private Date modifyTime;			// 审核时间
	
	// 报销详细列表
	@OneToMany(mappedBy="bizClaimVoucher", cascade = CascadeType.ALL)
	private List<ClaimVoucherDetail> detailList = new ArrayList<ClaimVoucherDetail>();
	// 审核结果列表
	@OneToMany(mappedBy="claimId", cascade = CascadeType.ALL)
	private List<CheckResult> checkResultList = new ArrayList<CheckResult>();

	// Constructors

	/** default constructor */
	public ClaimVoucher() {
	}


	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	

	public Employee getCreator() {
		return creator;
	}

	public void setCreator(Employee creator) {
		this.creator = creator;
	}
	
	

	public Employee getNextDeal() {
		return nextDeal;
	}


	public void setNextDeal(Employee nextDeal) {
		this.nextDeal = nextDeal;
	}


	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Double getTotalAccount() {
		return this.totalAccount;
	}

	public void setTotalAccount(Double totalAccount) {
		this.totalAccount = totalAccount;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public List<ClaimVoucherDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<ClaimVoucherDetail> detailList) {
		this.detailList = detailList;
	}


	public List<CheckResult> getCheckResultList() {
		return checkResultList;
	}


	public void setCheckResultList(List<CheckResult> checkResultList) {
		this.checkResultList = checkResultList;
	}

	
	
	

}