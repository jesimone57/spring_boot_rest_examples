package com.jsimone.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@XmlRootElement(name = "amicableNumbers")
public class AmicableNumbers {
	@XmlElement
	@JsonProperty
	private List<Set<Integer>> amicableNumbers = new ArrayList<>();

	@XmlElement
	@JsonProperty
	private Integer start;

	@XmlElement
	@JsonProperty
	private Integer end;

	@XmlElement
	@JsonProperty
	private Integer count;

	public void setAmicableNumbers(List<Set<Integer>> amicableNumbers) {
		this.amicableNumbers = amicableNumbers;
		this.count = amicableNumbers.size();
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public Integer getCount() {
		return count;
	}
}