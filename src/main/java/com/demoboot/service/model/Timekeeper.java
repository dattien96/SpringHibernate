package com.demoboot.service.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TIMEKEEPER")
public class Timekeeper {

	public static final char IN = 'I';
	public static final char OUT = 'O';

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "Timekeeper_Id", length = 36)
	private String timekeeperId;

	@Column(name = "Date_Time", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_ID", nullable = false)
	private Employee employee;

	// 'I' or 'O'
	@Column(name = "In_Out", nullable = false, length = 1)
	private char inOut;
}
