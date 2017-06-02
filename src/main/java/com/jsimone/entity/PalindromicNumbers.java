package com.jsimone.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "palindromicNumbers")
public class PalindromicNumbers {
	@XmlElement
	private List<Integer> palindromicNumbers = new ArrayList<>();

	@XmlElement
	@JsonProperty
	private Integer start;

	@XmlElement
	@JsonProperty
	private Integer end;

	@XmlElement
	@JsonProperty
	private Integer count;

	public void setPalindromicNumbers(List<Integer> palindromicNumbers) {
		this.palindromicNumbers = palindromicNumbers;
		this.count = palindromicNumbers.size();
	}

	public List<Integer> getPalindromicNumbers() {
		return palindromicNumbers;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public int getCount() {
		return count;
	}
}