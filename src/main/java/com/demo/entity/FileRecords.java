package com.demo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.poiji.annotation.ExcelCellName;


@Entity
@Table
public class FileRecords implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 5931435142731890965L;

	@Id
	@GeneratedValue
	private Long fileRecordsId;

	@ExcelCellName("Employee name")
	private String employeeName;

	@ExcelCellName("Age")
	private int age;

	@ExcelCellName("D-O-B")
	private String dob;

	@ExcelCellName("Stack")
	private String stack;

	@ExcelCellName("Year of experience ")
	private String yearOfExperience;

	@Override
	public String toString() {
		return "FileRecords [fileRecordsId=" + fileRecordsId + ", employeeName=" + employeeName + ", age=" + age
				+ ", dob=" + dob + ", stack=" + stack + ", yearOfExperience=" + yearOfExperience + "]";
	}

	public Long getFileRecordsId() {
		return fileRecordsId;
	}

	public void setFileRecordsId(Long fileRecordsId) {
		this.fileRecordsId = fileRecordsId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getStack() {
		return stack;
	}

	public void setStack(String stack) {
		this.stack = stack;
	}

	public String getYearOfExperience() {
		return yearOfExperience;
	}

	public void setYearOfExperience(String yearOfExperience) {
		this.yearOfExperience = yearOfExperience;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}