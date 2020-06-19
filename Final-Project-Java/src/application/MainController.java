package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class MainController {
	//the choice box selection
	ObservableList<String> textgenderlist = FXCollections.observableArrayList("M","F");
	ObservableList<Integer> textdaylist = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31);
	ObservableList<Integer> textmonthlist = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12);
	@FXML
	public TextField textname;
	@FXML
	public ChoiceBox<String> textgender;
	@FXML
	public ChoiceBox<Integer> textday;
	@FXML
	public ChoiceBox<Integer> textmonth;
	@FXML
	public TextField textyear;
	@FXML
	public TextField textaddress;
	@FXML
	public TextField textphoneNumber;
	@FXML
	public TextField textcourse;
	
	//at the start of running this program it will run this code that will add the choice box selection to the choice box in fxml
	@FXML
	private void initialize() {
		textgender.setItems(textgenderlist);
		textday.setItems(textdaylist);
		textmonth.setItems(textmonthlist);
	}
	//add the filled in information of student into the the database
	public void register(ActionEvent actionEvent) {
		try {
			//connecting to the database
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","password");
			//the "?" is the value that i inserted in below this statement which mean the student_name is textname.getText(), and so on.
			PreparedStatement ps = connection.prepareStatement("insert into student(student_name,student_gender,student_day,"
					+ "student_month,student_year,student_address,student_phonenumber,student_course) values(?,?,?,?,?,?,?,?)");
			ps.setString(1, textname.getText());
			ps.setObject(2,textgender.getValue());
			ps.setObject(3, textday.getValue());
			ps.setInt(4, textmonth.getValue());
			ps.setInt(5, Integer.parseInt(textyear.getText()));
			ps.setString(6, textaddress.getText());
			ps.setString(7, textphoneNumber.getText());
			ps.setString(8, textcourse.getText());
			int x = ps.executeUpdate();
			if(x>0) {
				System.out.println("Registration success");
			}else {
				System.out.println("failed");
			} 
			}
		catch(Exception e1) {
			System.out.println(e1);
		}
	}
	//change window to the TableView.fxml
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
	//change window to the update.fxml
	public void moveToUpdate(ActionEvent event) {
		try {
			Parent root2 = FXMLLoader.load(getClass().getResource("update.fxml"));
			Scene scene = new Scene(root2);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
