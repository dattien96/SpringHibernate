package com.demoboot.service.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEE", uniqueConstraints = { @UniqueConstraint(columnNames = { "EMP_NO" }) })
public class Employee {
	
	@Id
	@Column(name = "EMP_ID")
	private Long empId;
	
	@Column(name = "EMP_NO", length = 20, nullable = false)
	private String empNo;

	@Column(name = "EMP_NAME", length = 50, nullable = false)
	private String empName;
	
	@Column(name = "JOB", length = 30, nullable = false)
	private String job;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MNG_ID")
	private Employee manager;
	
	@Column(name = "HIRE_DATE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date hideDate;
	
	@Column(name = "SALARY", nullable = false)
	private Float salary;
	
	@Column(name = "IMAGE", length = 1111111, nullable = true)
	@Lob
	private byte[] image;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPT_ID", nullable = false)
	private Department department;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empId")
	private Set<Employee> employees = new HashSet<Employee>(0);
}
