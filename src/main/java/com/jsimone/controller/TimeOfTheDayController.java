package com.jsimone.controller;

import com.jsimone.constant.UrlPath;
import com.jsimone.entity.ClockTime;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@RestController
@Api(description = "Time of day examples", tags="Time of the Day API")
@RequestMapping("/")
public class TimeOfTheDayController implements TimeOfTheDay {
	private static String pattern = "MM.dd.yyyy HH:mm:ss";
	private SimpleDateFormat simpleDateFormatter = new SimpleDateFormat(pattern);

	@GetMapping(value = UrlPath.URL_TIME_OF_THE_DAY)
	public String getTimeOfTheDay() {
		return simpleDateFormatter.format(Calendar.getInstance().getTime());
	}

	@GetMapping(value = UrlPath.URL_TIME_OF_THE_DAY_XML, produces = {MediaType.APPLICATION_XML_VALUE})
	public ClockTime getTimeOfTheDayInXML() {
		ClockTime clock = new ClockTime();
		clock.setName("Big Ben");
		clock.setTime(simpleDateFormatter.format(Calendar.getInstance().getTime()));
		return clock;
	}

	@GetMapping(value = UrlPath.URL_TIME_OF_THE_DAY_JSON, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ClockTime getTimeOfTheDayInJSON() {
		ClockTime clock = new ClockTime();
		clock.setName("Big Ben");
		clock.setTime(simpleDateFormatter.format(Calendar.getInstance().getTime()));
		return clock;
	}

}