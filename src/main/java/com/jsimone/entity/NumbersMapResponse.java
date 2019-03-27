package com.jsimone.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@XmlRootElement(name = "numbers")
public class NumbersMapResponse {
	@XmlElement
	@JsonProperty
	private Map<Integer, List<Integer>> numbers = new TreeMap<>();

	@XmlElement
	@JsonProperty
	private Integer start;

	@XmlElement
	@JsonProperty
	private Integer end;

	@XmlElement
	@JsonProperty
	private Integer count;

	@XmlElement
	@JsonProperty
	private NumbersType type;

	public NumbersMapResponse() {}
	public NumbersMapResponse(Integer start, Integer end) {
		this.start = start;
		this.end = end;
	}

	public void setNumbers(Map<Integer, List<Integer>> numbers, NumbersType type) {
		this.numbers = numbers;
		this.count = numbers.size();
		this.type = type;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

}