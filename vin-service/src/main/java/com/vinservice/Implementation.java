package com.vinservice;

public class Implementation {

//	public int working(int a, int b){
//		return a*b;
//		}
		
		
public static void main(String[] args) {

	Interface i = (a, b) -> a+b;
	i.working(2, 20);
System.out.println(i.working(2, 20));

name obj1 = name.Instance;


String str = new String("Welcome to JavaTpoint.");   
String str1 = new String("Welcome to JavaTpoint");  
System.out.println(str1 == str); // prints false  

String str2 = new String("Welcome to JavaTpoint").intern(); // statement - 1  
String str3 = new String("Welcome to JavaTpoint").intern(); // statement - 2  
System.out.println(str1 == str2); // prints true  

String str4 = "Welcome to JavaTpoint";
System.out.println(str3 == str4);

}

enum name{
	
	Instance;
	int i;
	public static void check() {
		System.out.println("checking");
		
	}
	
}


}
