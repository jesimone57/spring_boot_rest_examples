package com.jsimone.controller;

import com.jsimone.constant.UrlPath;
import com.jsimone.entity.ClockTime;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@RestController
@RequestMapping("/")
public class TimeOfTheDayController implements TimeOfTheDay {
	private static String PATTERN = "MM.dd.yyyy HH:mm:ss";
	private static SimpleDateFormat simpleDateFormatter = new SimpleDateFormat(PATTERN);

	@RequestMapping(value = UrlPath.URL_TIME_OF_THE_DAY, method = RequestMethod.GET)
	public String getTimeOfTheDay() {
		return simpleDateFormatter.format(Calendar.getInstance().getTime());
	}

	@RequestMapping(value = UrlPath.URL_TIME_OF_THE_DAY_XML, method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
	public ClockTime getTimeOfTheDayInXML() {
		ClockTime clock = new ClockTime();
		clock.setName("Big Ben");
		clock.setTime(simpleDateFormatter.format(Calendar.getInstance().getTime()));
		return clock;
	}

	@RequestMapping(value = UrlPath.URL_TIME_OF_THE_DAY_JSON, method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ClockTime getTimeOfTheDayInJSON() {
		ClockTime clock = new ClockTime();
		clock.setName("Big Ben");
		clock.setTime(simpleDateFormatter.format(Calendar.getInstance().getTime()));
		return clock;
	}

}