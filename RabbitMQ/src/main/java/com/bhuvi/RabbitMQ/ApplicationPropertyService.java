//package com.bhuvi.RabbitMQ;
//
//import java.sql.Timestamp;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//public class ApplicationPropertyService {
//
//	@Autowired
//	private SchedulerRepository schedulerRepository;
//
//	String difference = "";
//
//
//	
////	 @Scheduled(fixedRate = 3000)
////	    public void checkDatabase() {
////		 int count = 0;
////		 
////	    	  String uri = "http://localhost:8080/scheduler/stop";
////	        RestTemplate restTemplate = new RestTemplate();
////	        String result = restTemplate.getForObject(uri, String.class);
////	        System.out.println(result);
////	    }
//	
//	public String getApplicationProperty() throws ParseException {
//		LocalDateTime currentDateTime = LocalDateTime.now();
//		List<Object[]> scheduler = schedulerRepository.findScheduledTimes(currentDateTime);
//		List<LocalDateTime> scheduledTimes = new ArrayList<>();
//		for (Object[] row : scheduler) {
//			Timestamp scheduledTime = (Timestamp) row[0];
//			scheduledTimes.add(scheduledTime.toLocalDateTime());
//		}
//		long eventScheduledDateInMilli = scheduledTimes.get(0).atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
//		System.out.println(scheduler);
//		Calendar cal = Calendar.getInstance();
//		long currentDateInMilli = cal.getTimeInMillis();
//		System.out.println("Event scheduled Date in milli " + eventScheduledDateInMilli);
//		System.out.println("Current Date in milli " + currentDateInMilli);
//		difference = String.valueOf(eventScheduledDateInMilli - currentDateInMilli);
//		System.out.println("Time difference " + difference);
////		pubishNotification();
////		gotTime= true;
//		return difference;
//	}
////	 
//	public void pubishNotification() {
//		LocalDateTime currentDateTime = LocalDateTime.now();
//		List<Object[]> scheduler = schedulerRepository.findScheduledTimes(currentDateTime);
//		List<LocalDateTime> scheduledTimes = new ArrayList<>();
//		System.out.println(scheduler.size());
//		for (Object[] row : scheduler) {
//			Timestamp scheduledTime = (Timestamp) row[0];
//			scheduledTimes.add(scheduledTime.toLocalDateTime());
//		}
//
//		List<Scheduler> currentRecords = schedulerRepository.findByScheduledTime(scheduledTimes.get(0), currentDateTime);
//		currentRecords.forEach(record -> {
//			record.setEntry(false);
//			schedulerRepository.save(record);
//			System.out.println("Event published for Catalog ID " + record.getCatalogId());
//		});
//		System.out.println(currentRecords);
//	}
//
//	public String generateCronExpression() throws ParseException {
//		String myDate = "2023/5/23 13:52:00";
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		Date date = simpleDateFormat.parse(myDate);
//		try {
//
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String dt = dateFormat.format(date);
//
//			Date cronDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dt);
//
//			CronUtil calHelper = new CronUtil(cronDate);
//			String cron = calHelper.getSeconds() + " " + calHelper.getMins() + " " + calHelper.getHours() + " "
//					+ calHelper.getDaysOfMonth() + " " + calHelper.getMonths() + " " + calHelper.getDaysOfWeek() + " "
//					+ calHelper.getYears();
//			System.out.println("cron " + cron);
//			return cron;
//		} finally {
//			System.out.println("Finally Block ");
//		}
//	}
//}
