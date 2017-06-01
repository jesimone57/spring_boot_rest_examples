package com.jsimone.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "primeNumbers")
public class PrimeNumbers {
	@XmlElement
	private List<Integer> primes = new ArrayList<Integer>();

	@XmlElement
	private Integer start;
	
	@XmlElement
	private Integer end;
	
	public void setPrimeNumbers(List<Integer> primes) {
		this.primes = primes;
	}
	public List<Integer> getPrimeNumbers() {
		return primes;
	}
	public void addPrime(Integer prime) {
		primes.add(prime);
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

}