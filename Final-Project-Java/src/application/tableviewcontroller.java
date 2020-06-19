package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class tableviewcontroller implements Initializable  {
	@FXML
	private TableView<studentDetail> tableUser;
	@FXML
	private TableColumn<studentDetail, Integer> column_id;
	@FXML
	private TableColumn<studentDetail, String> column_name;
	@FXML
	private TableColumn<studentDetail, String> column_gender;
	@FXML
	private TableColumn<studentDetail, Integer> column_day;
	@FXML
	private TableColumn<studentDetail, Integer> column_month;
	@FXML
	private TableColumn<studentDetail, Integer> column_year;
	@FXML
	private TableColumn<studentDetail, String> column_address;
	@FXML
	private TableColumn<studentDetail, String> column_phoneNumber;
	@FXML
	private TableColumn<studentDetail, String> column_course;
	@FXML 
	private TextField text_id;
	@FXML 
	private Label text_name;

	ObservableList<studentDetail> data = FXCollections.observableArrayList();

	private DBConnector dc;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		dc = new DBConnector();
	}
	@FXML
	public void searchName(ActionEvent event) {
		try {
	    	Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","password");
			PreparedStatement ps = connection.prepareStatement("select * from student where student_id = ?");
			ps.setInt(1, Integer.parseInt(text_id.getText()));
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String setName = rs.getString("student_name");
				text_name.setText(setName);
			}
    	}catch(Exception e1) {
			System.out.println(e1);
    	}
	}
	@FXML
	public void deleteName(ActionEvent event) {
		try {
	    	Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","password");
			PreparedStatement ps = connection.prepareStatement("delete from student where student_id = ?");
			ps.setInt(1, Integer.parseInt(text_id.getText()));
			ps.execute();
			text_name.setText("name");
    	}catch(Exception e1) {
			System.out.println(e1);
    	}
	}
	@FXML
	private void loadData(ActionEvent event) {
		try {
			Connection conn = dc.getConnection();
			data = FXCollections.observableArrayList();
			ResultSet rs = conn.createStatement().executeQuery("select * from student");
			while(rs.next()) {
				data.add(new studentDetail(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9)));
			}
		} catch (SQLException ex) {
			System.err.println("Error" + ex);
		}
		
		column_id.setCellValueFactory(new PropertyValueFactory<>("student_id"));
		column_name.setCellValueFactory(new PropertyValueFactory<>("student_name"));
		column_gender.setCellValueFactory(new PropertyValueFactory<>("student_gender"));
		column_day.setCellValueFactory(new PropertyValueFactory<>("student_day"));
		column_month.setCellValueFactory(new PropertyValueFactory<>("student_month"));
		column_year.setCellValueFactory(new PropertyValueFactory<>("student_year"));
		column_address.setCellValueFactory(new PropertyValueFactory<>("student_address"));
		column_phoneNumber.setCellValueFactory(new PropertyValueFactory<>("student_phoneNumber"));
		column_course.setCellValueFactory(new PropertyValueFactory<>("student_course"));
		
		tableUser.setItems(null);
		tableUser.setItems(data);
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
	public void goToUpdate(ActionEvent event) {
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