package com.bhuvi.RabbitMQ;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Component
public class EventSchedulerService {

	int loopCount = 0;
	boolean gotTime = false;
	

//	@Scheduled(fixedDelayString = "#{@eventSchedulerService.getPublishTime()}")
//	public void publishNotification() throws ParseException {
//		pubishNotification(gotTime);
//	}
	 
//	 @Scheduled(fixedDelay = 3000)
//	 public void schedulerOne(){
//		 System.out.println("scheduler 1");
//	 }
//	 
//	 @Scheduled(fixedDelay = 1000)
//	 public void schedulerTwo(){
//		 System.out.println("scheduler 2");
//	 }
//	
	

//	public List<LocalDateTime> getPublishTimesOrdered() {
//		Date date = new Date();
//		Timestamp currentDate = new Timestamp(date.getTime());
//		List<Object[]> scheduler = eventSchedulerRepository.findScheduledTimes(currentDate);
//		if (scheduler.size() == 0) {
//			return null;
//		} else {
//			List<LocalDateTime> scheduledTimes = new ArrayList<>();
//			for (Object[] row : scheduler) {
//				Timestamp scheduledTime = (Timestamp) row[0];
//				scheduledTimes.add(scheduledTime.toLocalDateTime());
//			}
//			return scheduledTimes;
//		}
//	}
//
//	public String getPublishTime() {
//		ZoneId zone = ZoneId.of("Asia/Kolkata");
//		if (getPublishTimesOrdered() == null) {
//			String maxValueAsString = Long.toString(Long.MAX_VALUE);
//			return maxValueAsString;
//		} else {
//			long eventScheduledDateInMilli = getPublishTimesOrdered().get(0).atZone(zone).toInstant().toEpochMilli();
//			LocalDateTime localDateTime = LocalDateTime.now();
//			long currentTimeMilli = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//			String difference = String.valueOf(eventScheduledDateInMilli - currentTimeMilli - 15000);
//			gotTime = true;
//			return difference;
//		}
//	}
//
//	public void pubishNotification(boolean gotTime) {
//		loopCount++;
//		if (gotTime = true && loopCount == 2) {
//			Date date = new Date();
//			Timestamp currentDate = new Timestamp(date.getTime());
//			List<LocalDateTime> listOfScheduledTime = getPublishTimesOrdered();
//			List<Timestamp> timestampList = new ArrayList<>();
//			for (LocalDateTime localDateTime : listOfScheduledTime) {
//				Timestamp timestamp = Timestamp.valueOf(localDateTime);
//				timestampList.add(timestamp);
//			}
//			List<EventScheduler> currentRecords = eventSchedulerRepository.findByScheduledTime(timestampList.get(0),
//					currentDate);
//			currentRecords.forEach(record -> {
//				record.setPublished(true);
//				eventSchedulerRepository.save(record);
//				System.out.println("Event published for Catalog ID " + record.getCatalogId());
//			});
//			enableScheduler();
//			gotTime = false;
//			loopCount = 0;
//		}
//
//	}
//	
//	public void enableScheduler() {
//		 postProcessor.postProcessBeforeDestruction(eventSchedulerService, SCHEDULED_TASKS);
//		  postProcessor.postProcessAfterInitialization(eventSchedulerService, SCHEDULED_TASKS);
////		String uriStop = "http://localhost:8080/scheduler/stop";
////		String uriStart = "http://localhost:8080/scheduler/start";
////		RestTemplate restTemplate = new RestTemplate();
////		restTemplate.getForObject(uriStop, String.class);
////		restTemplate.getForObject(uriStart, String.class);
//		loopCount = 0;
//	}
//	
//	

}
