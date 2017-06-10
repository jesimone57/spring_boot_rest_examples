package com.jsimone.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "armstrongNumbers")
public class ArmstrongNumbers {
	@XmlElement
	@JsonProperty
	private List<Integer> armstrongNumbers = new ArrayList<>();

	@XmlElement
	@JsonProperty
	private Integer start;

	@XmlElement
	@JsonProperty
	private Integer end;

	@XmlElement
	@JsonProperty
	private Integer count;

	public void setArmstrongNumbers(List<Integer> armstrongNumbers) {
		this.armstrongNumbers = armstrongNumbers;
		this.count = armstrongNumbers.size();
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}
	
}