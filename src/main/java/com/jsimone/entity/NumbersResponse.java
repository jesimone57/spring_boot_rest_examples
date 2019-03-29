package com.jsimone.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "numbers")
public class NumbersResponse {
	@XmlElement
	@JsonProperty
	private List<Integer> numbers = new ArrayList<>();

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

	public NumbersResponse() {}
	public NumbersResponse(Integer start, Integer end) {
		this.start = start;
		this.end = end;
	}
	public NumbersResponse(Range range) {
		range.validate();
		this.start = range.getStart();
		this.end = range.getEnd();
	}

	public void setNumbers(List<Integer> numbers, NumbersType type) {
		this.numbers = numbers;
		this.count = numbers.size();
		this.type = type;
	}

	public List<Integer> getNumbers() {
		return this.numbers;
	}

}