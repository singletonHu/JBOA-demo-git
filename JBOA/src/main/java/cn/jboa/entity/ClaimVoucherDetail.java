package cn.jboa.entity;

import javax.persistence.*;

/**
 * 报销单详细
 *
 */
@Entity
@Table(name="`BIZ_CLAIM_VOUCHER_DETAIL`")
public class ClaimVoucherDetail implements java.io.Serializable {

    private static final long serialVersionUID = 8187404228626478972L;
    @Id
    @Column(name="`ID`")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_detail")
    @SequenceGenerator(name="seq_detail",sequenceName="SEQ_VOUCHER_DETAIL")
    private Long id;						// 编号
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="`MAIN_ID`")
    private ClaimVoucher bizClaimVoucher;	// 报销单
    
    @Column(name="`ITEM`")
    private String item;					// 报销项目
    
    @Column(name="`ACCOUNT`")
    private Double account;					// 报销金额
    
    @Column(name="`DES`")
    private String desc;					// 费用说明

    /** default constructor */
    public ClaimVoucherDetail() {
    }

    /** full constructor */
    public ClaimVoucherDetail(ClaimVoucher bizClaimVoucher,
            String item, Double account, String desc) {
        this.bizClaimVoucher = bizClaimVoucher;
        this.item = item;
        this.account = account;
        this.desc = desc;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClaimVoucher getBizClaimVoucher() {
        return this.bizClaimVoucher;
    }

    public void setBizClaimVoucher(ClaimVoucher bizClaimVoucher) {
        this.bizClaimVoucher = bizClaimVoucher;
    }

    public String getItem() {
        return this.item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getAccount() {
        return this.account;
    }

    public void setAccount(Double account) {
        this.account = account;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}