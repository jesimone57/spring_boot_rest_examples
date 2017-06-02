package com.jsimone.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "armstrongNumbers")
public class ArmstrongNumbers {
	@XmlElement
	private List<Integer> armstrongNumbers = new ArrayList<>();

	@XmlElement
	private Integer start;

	@XmlElement
	private Integer end;

	public void setArmstrongNumbers(List<Integer> armstrongNumbers) {
		this.armstrongNumbers = armstrongNumbers;
	}

	public List<Integer> getArmstrongNumbers() {
		return armstrongNumbers;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

}