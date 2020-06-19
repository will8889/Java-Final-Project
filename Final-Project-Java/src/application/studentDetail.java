package application;

import javafx.beans.property.*;

public class studentDetail {

	private final IntegerProperty student_id;
	private final StringProperty student_name;
	private final StringProperty student_gender;
	private final IntegerProperty student_day;
	private final IntegerProperty student_month;
	private final IntegerProperty student_year;
	private final StringProperty student_address;
	private final StringProperty student_phoneNumber;
	private final StringProperty student_course;
	
	public studentDetail(int student_id, String student_name, String student_gender, int student_day,
			int student_month, int student_year, String student_address, String student_phoneNumber, String student_course) {
		this.student_id = new SimpleIntegerProperty(student_id);
		this.student_name = new SimpleStringProperty(student_name);
		this.student_gender = new SimpleStringProperty(student_gender);
		this.student_day = new SimpleIntegerProperty(student_day);
		this.student_month = new SimpleIntegerProperty(student_month);
		this.student_year = new SimpleIntegerProperty(student_year);
		this.student_address = new SimpleStringProperty(student_address);
		this.student_phoneNumber = new SimpleStringProperty(student_phoneNumber);
		this.student_course = new SimpleStringProperty(student_course);
	}
	
	
	public int getID() {return student_id.get();}
	public String getName() {return student_name.get();}
	public String getGender() {return student_gender.get();}
	public int getDay() {return student_day.get();}
	public int getMonth() {return student_month.get();}
	public int getYear() {return student_year.get();}
	public String getAddress() {return student_address.get();}
	public String getPhoneNumber() {return student_phoneNumber.get();}
	public String getCourse() {return student_course.get();}
	
	public void setID(int x) {student_id.set(x);}
	public void setName(String x) {student_name.set(x);}
	public void setGender(String x) {student_gender.set(x);}
	public void setDay(int x) {student_day.set(x);}
	public void setMonth(int x) {student_month.set(x);}
	public void setYear(int x) {student_year.set(x);}
	public void setAddress(String x) {student_address.set(x);}
	public void setPhoneNumber(String x) {student_phoneNumber.set(x);}
	public void setCourse(String x) {student_course.set(x);}
	
	public IntegerProperty student_idProperty() {return student_id;}
	public StringProperty student_nameProperty() {return student_name;}
	public StringProperty student_genderProperty() {return student_gender;}
	public IntegerProperty student_dayProperty() {return student_day;}
	public IntegerProperty student_monthProperty() {return student_month;}
	public IntegerProperty student_yearProperty() {return student_year;}
	public StringProperty student_addressProperty() {return student_address;}
	public StringProperty student_phoneNumberProperty() {return student_phoneNumber;}
	public StringProperty student_courseProperty() {return student_course;}
}
