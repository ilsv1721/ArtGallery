package com.ilya.art.dto.converters;

import java.util.Calendar;
import java.util.Date;

import com.ilya.art.dto.DateDto;

public abstract class DateDtoDateJavaConverter {

	static public Date convert(DateDto dtoDate) {

		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, dtoDate.getYear());
		calendar.set(Calendar.MONTH, dtoDate.getMonth() - 1);
		calendar.set(Calendar.DAY_OF_MONTH, dtoDate.getDay());
		Date date = calendar.getTime();
		calendar.clear();
		return date;

	}

	static public DateDto convert(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		DateDto retDate = new DateDto(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.YEAR));
		calendar.clear();
		return retDate;

	}

}
