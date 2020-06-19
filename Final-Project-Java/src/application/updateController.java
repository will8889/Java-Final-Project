package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class updateController {
	ObservableList<String> textgenderlist = FXCollections.observableArrayList("M","F");
	ObservableList<Integer> textdaylist = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31);
	ObservableList<Integer> textmonthlist = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12);
    @FXML
    private TextField text_id;
    @FXML
    private TextField text_name;
    @FXML
    private ChoiceBox<String> text_gender;
    @FXML
    private ChoiceBox<Integer> text_day;
    @FXML
    private ChoiceBox<Integer> text_month;
    @FXML
    private TextField text_year;
    @FXML
    private TextField text_address;
    @FXML
    private TextField text_phoneNumber;
    @FXML
    private TextField text_course;

	@FXML
	private void initialize() {
		text_gender.setItems(textgenderlist);
		text_day.setItems(textdaylist);
		text_month.setItems(textmonthlist);
	}
	public void goToRegis(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void moveToData(ActionEvent event) {
		try {
			Parent root1 = FXMLLoader.load(getClass().getResource("tableView.fxml"));
			Scene scene = new Scene(root1);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	// to search the database with the id and then fill in the textfield with the data in the database
    public void searchDatabase(ActionEvent event) {
    	try {
    		text_name.clear();
    		text_year.clear();
    		text_address.clear();
    		text_phoneNumber.clear();
    		text_course.clear();
	    	Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","password");
			PreparedStatement ps = connection.prepareStatement("select * from student where student_id = ?");
			ps.setInt(1, Integer.parseInt(text_id.getText()));
			//insert the data into the Result set
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				//get the data from the ResultSet
				String setName = rs.getString("student_name");
				//set the textField to the data
				text_name.setText(setName);
				//repeat again until the very bottom
				String setGender = rs.getString("student_gender");
				text_gender.setValue(setGender);
				int setDay = rs.getInt("student_day");
				text_day.setValue(setDay);
				int setMonth = rs.getInt("student_month");
				text_month.setValue(setMonth);
				String setYear = rs.getString("student_year");
				text_year.setText(setYear);
				String setAddress = rs.getString("student_address");
				text_address.setText(setAddress);
				String setPhoneNumber = rs.getString("student_phoneNumber");
				text_phoneNumber.setText(setPhoneNumber);
				String setCourse = rs.getString("student_course");
				text_course.setText(setCourse);	
			}
    	}catch(Exception e1) {
			System.out.println(e1);
    	}
    }
    //if you want to change the data / update we can use this function to update the data and replace the original data
    public void updateDatabase(ActionEvent event) {
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","password");
			PreparedStatement ps = connection.prepareStatement("update student set student_name=?, student_gender=?, student_day=?, "
					+ "student_month=?, student_year=?, student_address=?, student_phoneNumber = ?, student_course=? where student_id = ?");
			ps.setString(1, text_name.getText());
			ps.setString(2, text_gender.getValue());
			ps.setInt(3, text_day.getValue());
			ps.setInt(4, text_month.getValue());
			ps.setString(5, text_year.getText());
			ps.setString(6, text_address.getText());
			ps.setString(7, text_phoneNumber.getText());
			ps.setString(8, text_course.getText());
			ps.setInt(9, Integer.parseInt(text_id.getText()));
			int x = ps.executeUpdate();
			if(x>0) {
				System.out.println("Update success");
			}else {
				System.out.println("failed");
			} 
    	}
    	catch(Exception e2){System.out.println(e2);}
    }
}