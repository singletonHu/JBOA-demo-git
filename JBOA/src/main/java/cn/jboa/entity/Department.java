package cn.jboa.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
/**
 * 部门实体类
 */
@Entity
@Table(name="`SYS_DEPARTMENT`")
public class Department implements java.io.Serializable {

    private static final long serialVersionUID = -4386159382927256061L;
    @Id
    @Column(name = "`ID`")
    private Integer id;				// 部门编号
    
    @Column(name = "`NAME`")
    private String name;			// 部门名称
    
    // 员工集合
    @OneToMany(mappedBy="sysDepartment",cascade= {CascadeType.ALL})
    private Set<Employee> sysEmployees = new HashSet<Employee>(0);

    /** default constructor */
    public Department() {
    }

    /** minimal constructor */
    public Department(String name) {
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public Set<Employee> getSysEmployees() {
		return sysEmployees;
	}

	public void setSysEmployees(Set<Employee> sysEmployees) {
		this.sysEmployees = sysEmployees;
	}

}