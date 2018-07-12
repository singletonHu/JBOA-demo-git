package cn.jboa.entity;
// default package

import java.util.Date;

import javax.persistence.*;

/**
 * 请假信息
 */
@Entity
@Table(name="`BIZ_LEAVE`")
public class Leave  implements java.io.Serializable {


    // Fields    
	 private static final long serialVersionUID = 1L;
	 @Id
	 @Column(name="`ID`")
	 @GeneratedValue(strategy=GenerationType.SEQUENCE ,generator="seq_leave")
	 @SequenceGenerator(name="seq_leave",sequenceName="SEQ_LEAVE")
	 private Long id;				// 请假编号
	 
	 @ManyToOne(fetch=FetchType.LAZY)
	 @JoinColumn(name="`EMPLOYEE_SN`")
     private Employee creator;		// 申请人
	 
	 @Column(name="`STARTTIME`")
     private Date startTime;		// 请假开始时间
	 
	 @Column(name="`ENDTIME`")
     private Date endTime;			// 请假结束时间
	 
	 @Column(name="`LEAVEDAY`")
     private Double leaveDay;		// 请假天数
	 
	 @Column(name="`REASON`")
     private String reason;			// 请假理由
	 
	 @Column(name="`STATUS`")
     private String status;			// 审核状态
	 
	 @Column(name="`LEAVETYPE`")
     private String leaveType;		// 请假类型
	 
	 @ManyToOne(fetch=FetchType.LAZY)
	 @JoinColumn(name="`NEXT_DEAL_SN`")
     private Employee nextDeal;		// 待处理人
	 
	 @Column(name="`APPROVE_OPINION`")
     private String approveOpinion;	// 审批意见
	 
	 @Column(name="`CREATETIME`")
     private Date createTime;		// 申请时间
	 
	 @Column(name="`MODIFYTIME`")
     private Date modifyTime;		// 审核时间


    // Constructors

    /** default constructor */
    public Leave() {
    }
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getLeaveDay() {
        return this.leaveDay;
    }
    
    public void setLeaveDay(Double leaveday) {
        this.leaveDay = leaveday;
    }

    public String getReason() {
        return this.reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getLeaveType() {
        return this.leaveType;
    }
    
    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

   

    public String getApproveOpinion() {
        return this.approveOpinion;
    }
    
    public void setApproveOpinion(String approveOpinion) {
        this.approveOpinion = approveOpinion;
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
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

	


}