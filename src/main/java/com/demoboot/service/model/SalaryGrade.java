package com.demoboot.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SALARY_GRADE")
public class SalaryGrade {

	@Id
	@Column(name = "GRADE")
	private Integer grade;

	@Column(name = "LOW_SALARY", nullable = false)
	private Float lowSalary;

	@Column(name = "HIGH_SALARY", nullable = false)
	private Float highSalary;
}
