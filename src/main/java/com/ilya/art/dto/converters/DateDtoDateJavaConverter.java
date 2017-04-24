package com.ilya.art.dto.converters;

import java.util.Calendar;
import java.util.Date;

import com.ilya.art.dto.DtoDate;

public abstract class DateDtoDateJavaConverter {

	static public Date convert(DtoDate dtoDate) {

		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, dtoDate.getYear());
		calendar.set(Calendar.MONTH, dtoDate.getMonth() - 1);
		calendar.set(Calendar.DAY_OF_MONTH, dtoDate.getDay());
		Date date = calendar.getTime();
		calendar.clear();
		return date;

	}

	static public DtoDate convert(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		DtoDate retDate = new DtoDate(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.YEAR));
		calendar.clear();
		return retDate;

	}

}
