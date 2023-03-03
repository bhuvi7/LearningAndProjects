package com.vinservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SamplePrograms {

	/**
	 * @param args
	 */
	public static void main(String args[]) {
		
		
//		Q1 Given a list of integers, find out all the even numbers exist in the list using Stream functions?
	List<Integer> listInteger = Arrays.asList(10,1,2,2,3,3,4,5,16,17);
	List<Integer> res = listInteger.stream().filter(x -> x%2==0).collect(Collectors.toList());
	System.out.println("Even Numbers " + res);
	
//		Q2 Given a list of integers, find out all the numbers starting with 1 using Stream functions?
	List<String> res1 =listInteger.stream().map(x -> x+"").collect(Collectors.toList());
	System.out.println("String conversion " + res1);
	List<String> res2 =listInteger.stream().map(x -> x+"").filter(x-> x.startsWith("1")).collect(Collectors.toList());
	System.out.println("starting with 1 " + res2);
	
//		Q3 How to find duplicate elements in a given integers list in java using Stream functions?
	Set<Integer> intSet = new HashSet<Integer>();
	
	List<Integer> res3= listInteger.stream().filter(x-> !intSet.add(x)).collect(Collectors.toList());
	System.out.println("duplicate elements " + res3);
//		Q4 Given the list of integers, find the first element of the list using Stream functions?
	Integer res4 = listInteger.stream().findFirst().get();
	System.out.println("Find the first element " + res4);
	
//		Q5 Given a list of integers, find the total number of elements present in the list using Stream functions?
	Long res5 = listInteger.stream().count();
	System.out.println("Total number of elements " + res5);
	
//		Q6 Given a list of integers, find the maximum value element present in it using Stream functions?
	Integer res6 = listInteger.stream().max(Comparator.comparing(Integer::valueOf)).get();
	System.out.println("Maximum value " + res6);
	
//		Q7 Given a String, find the first non-repeated character in it using Stream functions?
	Map<Integer,Integer> intSet1 = new HashMap();
//	listInteger.stream().map(x-> intSet1.put(x, count++));
    String input = "Java Hungry Blog Alive is Awesome";
   Map<Character,Long >result = listInteger.stream().map(x->x+"").collect(Collectors.joining()).chars() // Stream of String       
                            .mapToObj(s -> Character.toLowerCase(Character.valueOf((char) s)))         
                            .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
   System.out.println("joining & individual characters "+result);
	
//		Q8 Given a String, find the first repeated character in it using Stream functions?
   Set intSet2 = new HashSet();
  Integer res8 = listInteger.stream().filter(x-> !intSet2.add(x)).findFirst().get();
  System.out.println("find the first repeated character "	 + res8);
  
//		Q9 Given a list of integers, sort all the values present in it using Stream functions?
  List<Integer> res9 = listInteger.stream().sorted().collect(Collectors.toList());
  System.out.println("sort all the values present "	 + res9);
  
//		Q10 Given a list of integers, sort all the values present in it in descending order using Stream functions?
  List<Integer> res10 = listInteger.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
  System.out.println("sort all the values descending "	 + res10);
  
//		1. How do you reverse a string in Java?
  String str = "Bhuvi";
  StringBuffer sb= new StringBuffer();
  int len = str.length();
  char[] ch = str.toCharArray();
  for(int i=len-1; i>=0 ;i--) {
	  sb.append(ch[i]);
	  
  }
  System.out.println("reverse a string "+ sb);
//		2. How do you swap two numbers without using a third variable in Java?
  
  int a = 5;
  int b = 10;
  a=a+b;
  b=a-b;
  a=a-b;
  System.out.println("Without 3rd varibale "+ a+"  "+ b);
  
//		3. Write a Java program to check if a vowel is present in a string.
  String str1 ="bhuvi";
  char[] ch1 = {'a','e','i','o','u'};
  char[] ch2 = str1.toCharArray();
  
  int len1 = str1.length();
  System.out.println(len1);
  int len2= ch1.length;  
  System.out.println(len2);
  for(int i=0; i<len1; i++) {
	  for(int j=0; j<len2; j++) {
		  if(ch1[i]==ch2[j]) {
			  System.out.println("vowel "+ ch1[i]);
		  }
	  }
	  
  }
  
//		4. Write a Java program to check if the given number is a prime number.
//		5. Write a Java program to print a Fibonacci sequence using recursion.
//		6. How do you check if a list of integers contains only odd numbers in Java?
//		7. How do you check whether a string is a palindrome in Java?
//		8. How do you remove spaces from a string in Java?
//		9. How do you remove leading and trailing spaces from a string in Java?
//		10. How do you sort an array in Java?
		
//		1 How to reverse the list of object by using iterator?
//		2 sort the Employee data using comparator/comparable.
//		3 Write the code to find all emoloyees from list of employees whose age is greater than 40?
//		4 given a scenario like there  are many records of employee name , age, salary and id.
//		how will get the data of employees  salaries whose age>30 .
//		5 Count and display the number of positive, negative and null values in the list
//		6 Given a list of integers, find out all the even numbers exist in the list using Stream functions?
//		7.Sort the Employee data based on the salary if salary is same then sort based on the age.

//  Write a code that has to return a list of unique strings
  List<String> strr = Arrays.asList("a","b","a");
  Set<String> sets = new HashSet<String>();
  
  for(String s : strr) {
	  System.out.println(s);
//	  sets.add(strr[1]);
	  
  }
  
	}
}
