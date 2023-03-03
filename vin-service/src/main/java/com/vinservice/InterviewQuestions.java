package com.vinservice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InterviewQuestions {

	
	public static void main(String[] args) {
		
//	 Consider a list with null,positive,negative integers.Write code to sum all the positive integers
		List<Integer> li = Arrays.asList(null,-1,2,-10,3,4);
		
//	Integer total = li.stream().filter(x-> x!=null).collect(Collectors.summingInt(Integer::intValue));
		Integer total = li.stream().filter(x-> x !=null).reduce((a,b)->a+b).get();
		System.out.println( "Total value " +total);
		
//		Count and display the number of positive, negative and null values in the list
		Long count = li.stream().count();
		int nullCounter =0;
		int negativeCounter = 0;
		int positiveCounter =0;
		int len=li.size();
		for(int i=0; i<len; i++) {
			if(li.get(i) == null) {
				nullCounter++;
			}
			else if(li.get(i) <0 ) {
				negativeCounter++;
			}
			else {
				positiveCounter++;
			}
		}
		System.out.println("Count of Null "+ nullCounter);
		System.out.println("Count of Positive "+ positiveCounter);
		System.out.println("Count of Negative "+ negativeCounter);
		System.out.println("Count of the list "+ count);
		
		//Get current date in java 8
		LocalDate date = LocalDate.now();
		System.out.println("Current date "+ date);
		
//		  MyThread myThread = new MyThread();
//	        myThread.start();
//	        
//	        // main thread continues executing while myThread runs concurrently
//	        for (int i = 0; i < 10; i++) {
//	            System.out.println("Main thread: " + i);
//	        }
	        
//	        java program to remove duplicates from an array
	    	List<Integer> lii = Arrays.asList(null,-1,2,-10,3,4,2); 
	    	
	   List<Integer> withoutDups = 	lii.stream().distinct().collect(Collectors.toList());
	   System.out.println("Without duplications " + withoutDups);
	   
		
	}
	
}
class MyThread extends Thread {
    public void run() {
        // thread code to be executed concurrently
        for (int i = 0; i < 10; i++) {
            System.out.println("MyThread: " + i);
        }
    }
}
