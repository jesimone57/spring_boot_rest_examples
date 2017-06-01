package com.jsimone.controller;

import com.jsimone.entity.ClockTime;

public interface TimeOfTheDay {
	String getTimeOfTheDay();
	ClockTime getTimeOfTheDayInXML();
	ClockTime getTimeOfTheDayInJSON();
}
