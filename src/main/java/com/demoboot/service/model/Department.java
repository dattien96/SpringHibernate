package com.demoboot.service.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "DEPARTMENT", uniqueConstraints = { @UniqueConstraint(columnNames = { "DEPT_NO" }) })
public class Department {

	@Id
	@Column(name = "DEPT_ID")
	private Integer deptId;
	
	@Column(name = "DEPT_NO", length = 20, nullable = false)
	private String deptNo;

	@Column(name = "DEPT_NAME", nullable = false)
	private String deptName;
	
	@Column(name = "LOCATION")
	private String location;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
	private Set<Employee> employees = new HashSet<Employee>(0);

	public Department(Integer deptId, String deptName, String location) {
		this.deptId = deptId;
		this.deptNo = "D" + this.deptId;
		this.deptName = deptName;
		this.location = location;
	}
}
