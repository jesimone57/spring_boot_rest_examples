package com.jsimone.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "perfectNumbers")
public class PerfectNumbers {
	@XmlElement
	@JsonProperty
	private List<Integer> perfectNumbers = new ArrayList<>();

	@XmlElement
	@JsonProperty
	private Integer start;

	@XmlElement
	@JsonProperty
	private Integer end;

	@XmlElement
	@JsonProperty
	private Integer count;


	public void setPerfectNumbers(List<Integer> perfectNumbers) {
		this.perfectNumbers = perfectNumbers;
		this.count = perfectNumbers.size();
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

}