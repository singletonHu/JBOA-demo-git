package cn.jboa.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
/**
 *	角色
 */
@Entity
@Table(name="`SYS_POSITION`")
public class Position implements java.io.Serializable 
{
	
    private static final long serialVersionUID = -1777501621600424586L;
    @Id
    @Column(name="`ID`")
    private Integer id;		// 部门编号
    @Column(name="`NAME_CN`")
    private String nameCn;	// 部门中文名
    @Column(name="`NAME_EN`")
    private String nameEn;	// 部门英文名
    // 员工集合
    @OneToMany(mappedBy="sysPosition", cascade= {CascadeType.ALL})
    private Set<Employee> emps = new HashSet<Employee>();

    /** default constructor */
    public Position() {
    }
    public Integer getId() {
        return this.id;
    }

    public Set<Employee> getEmps() {
		return emps;
	}
	public void setEmps(Set<Employee> emps) {
		this.emps = emps;
	}
	public void setId(Integer id) {
        this.id = id;
    }

    public String getNameCn() {
        return this.nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameEn() {
        return this.nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
}