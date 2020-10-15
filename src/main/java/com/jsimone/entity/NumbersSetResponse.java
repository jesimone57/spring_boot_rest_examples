package com.jsimone.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@XmlRootElement(name = "numbers")
public class NumbersSetResponse {
	@XmlElement
	@JsonProperty
	private List<Set<Integer>> numbers = new ArrayList<>();

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

	public NumbersSetResponse() {}
	public NumbersSetResponse(Integer start, Integer end) {
		this.start = start;
		this.end = end;
	}
	public NumbersSetResponse(Range range) {
		range.validate();
		this.start = range.getStart();
		this.end = range.getEnd();
	}

	public void setNumbers(List<Set<Integer>> numbers, NumbersType type) {
		this.numbers = numbers;
		this.count = numbers.size();
		this.type = type;
	}

}