package beans;

public class Employee {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	
	public Employee(Integer id, String firstName, String lastName, String emailAddress) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
	}
	
	public Integer getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}

	public String getEmployeeTableLine() {
		String action = "<form action='/tv/listEmployees' method='post'><input type='hidden' name='id' value='"+id+"'>" +
				"<div class='row'><input type='submit' value='Delete'></div></form>";
		
		return "<tr><td>" + id + "</td><td>" + firstName + "</td><td>" + lastName + "</td><td>" + emailAddress + 
				"</td><td>" + action + "</td></tr>";
	}
}
