package com.jsimone.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "primeNumbers")
public class PrimeNumbers {
	@XmlElement
	private List<Integer> primes = new ArrayList<>();

	@XmlElement
	@JsonProperty
	private Integer start;

	@XmlElement
	@JsonProperty
	private Integer end;

	@XmlElement
	@JsonProperty
	private Integer count;


	public void setPrimeNumbers(List<Integer> primes) {
		this.primes = primes;
		this.count = primes.size();
	}

	public List<Integer> getPrimeNumbers() {
		return primes;
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