package cn.jboa.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * 员工类
 */
@Entity
@Table(name = "`SYS_EMPLOYEE`")
public class Employee implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="`SN`")
	private String sn;					// 员工id
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="`POSITION_ID`")
	private Position sysPosition;		// 角色对象 多个员工对应一个角色
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="`DEPARTMENT_ID`")
	private Department sysDepartment;	// 部门对象
	
	@Column(name="`NAME`")
	private String name;				// 员工名称
	
	@Column(name="`PASSWORD`")
	private String password;			// 密码
	
	@Column(name="`STATUS`")
	private String status;				// 状态
	
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Position getSysPosition() {
		return sysPosition;
	}

	public void setSysPosition(Position sysPosition) {
		this.sysPosition = sysPosition;
	}

	public Department getSysDepartment() {
		return sysDepartment;
	}

	public void setSysDepartment(Department sysDepartment) {
		this.sysDepartment = sysDepartment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}