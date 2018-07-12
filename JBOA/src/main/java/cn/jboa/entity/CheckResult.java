package cn.jboa.entity;

import java.util.Date;

import javax.persistence.*;

/**
 * 审核结果实体
 *
 */
@Entity
@Table(name="`BIZ_CHECK_RESULT`")
public class CheckResult implements java.io.Serializable {

    private static final long serialVersionUID = -6927459782166236900L;
    
    @Id
    @Column(name="`ID`")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_result")
    @SequenceGenerator(name="seq_result",sequenceName="SEQ_CHECK_RESULT")
    private Long id;					// 结果编号
    
    @Column(name="`CLAIM_ID`")
    private Long claimId;		// 报销单编号
    
    @Column(name="`CHECK_TIME`")
    private Date checkTime;				// 审核时间
    
    @Column(name="`RESULT`")
    private String result;				// 审核结果
    
    @Column(name="`COMM`")
    private String comment;				// 审核意见
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "`CHECKER_SN`")
    private Employee checkEmployee;		// 审核人

    /** default constructor */
    public CheckResult() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getCheckTime() {
        return this.checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


	public Long getClaimId() {
		return claimId;
	}

	public void setClaimId(Long claimId) {
		this.claimId = claimId;
	}

	public Employee getCheckEmployee() {
		return checkEmployee;
	}



	public void setCheckEmployee(Employee checkEmployee) {
		this.checkEmployee = checkEmployee;
	}

	
    

}