package com.jsimone.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "clock")
public class ClockTime {
	@JsonProperty
	@XmlElement
	private String time;

	@JsonProperty
	@XmlElement
	private String name;

	public void setTime(String time) {
		this.time = time;
	}

	public void setName(String name) {
		this.name = name;
	}

}