package Microserverhome.Home;


public class Employee {

	private String EmployeeName;
	private int age;
	private long salary;
	public Employee(String employeeName, int age, long salary) {
		super();
		EmployeeName = employeeName;
		this.age = age;
		this.salary = salary;
	}
	public String getEmployeeName() {
		return EmployeeName;
	}
	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [EmployeeName=" + EmployeeName + ", age=" + age + ", salary=" + salary + "]";
	}
	
	
}
