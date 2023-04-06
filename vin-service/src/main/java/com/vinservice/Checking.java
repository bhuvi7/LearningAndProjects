package com.vinservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Checking {
	
	public static void main(String[] args) throws ParseException {
//		Integer value = 193000;
//		int year = value / 10000;
//		int month = (value % 10000) / 100;
//		int day = value % 100;
//		Date date = new GregorianCalendar(year, month, day).getTime();
		int hours   = 19/ 60;   // since both are ints, you get an int
		int minutes = 30 % 60;
		System.out.printf("%d:%02d", hours, minutes);
		
//		Date dt = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(String.valueOf(19000101));
//		System.out.println(dt);
	}
	
}
