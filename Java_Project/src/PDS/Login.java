package PDS;

/*
* Module to Login as a User and Admin with the help of valid credentials
*/
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Login extends Main {
	// Declare variables
	public static String ration_card_id = null;
	public static String Ausername;

	// Method to handle login details
	public void loginDetails() {
		Dashboard obj = new Dashboard();
		try {
			// Load the JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Database connection details
			String url = "jdbc:mysql://localhost:3306/pds";
			String UserName = "root";
			String PassWord = "SYSTEM";
			// Establish database connection
			Connection conn = DriverManager.getConnection(url, UserName, PassWord);
			// Create a scanner for user input
			Scanner scanner = new Scanner(System.in);
			// Display login menu
			while (true) {
				System.out.println("1. Admin Login");
				System.out.println("2. User Login");
				System.out.println("3. Back to Home Page");
				System.out.println("4. Exit");
				System.out.print("Enter your choice: ");
				int choice = scanner.nextInt();
				scanner.nextLine();
				// Handle user choices
				if (choice == 1) {
					// Admin login
					System.out.print("Enter username: ");
					Ausername = scanner.nextLine();
					System.out.print("Enter password: ");
					String Apassword = scanner.nextLine();
					// SQL query for admin login
					String selectUserSQL = "SELECT * FROM admin WHERE admin_id = ? AND password = ?";
					PreparedStatement selectStatement = conn.prepareStatement(selectUserSQL);
					selectStatement.setString(1, Ausername);
					selectStatement.setString(2, Apassword);
					ResultSet resultSet = selectStatement.executeQuery();
					// Check if login is successful
					if (resultSet.next()) {
						System.out.println("Login successful as a Admin!");
						obj.adminDashboard();
						break;
					} else {
						System.out.println("Incorrect Admin's username or password. Please try again.");
					}
				} else if (choice == 2) {
					// User login
					System.out.print("Enter Your Ration Card Id: ");
					ration_card_id = scanner.nextLine();
					System.out.print("Enter password: ");
					String password = scanner.nextLine();
					// SQL query for user login
					String selectUserSQL = "SELECT * FROM User WHERE ration_card_id = ? AND passwordforID = ?";
					PreparedStatement selectStatement = conn.prepareStatement(selectUserSQL);
					selectStatement.setString(1, ration_card_id);
					selectStatement.setString(2, password);
					ResultSet resultSet = selectStatement.executeQuery();
					// Check if login is successful
					if (resultSet.next()) {
						System.out.println("Login successful as a User!");
						obj.userDashboard();
						break;
					} else {
						System.out.println("Incorrect User's username or password. Please try again.");
					}
				} else if (choice == 3) {
					// Go back to the main menu
					mainMenu();
					break;
				} else if (choice == 4) {
					// Exit the program
					System.out.println("Thankyou For Using Public Distribution System");
					System.exit(0);
				} else {
				} // Invalid choice
				System.out.println("Invalid choice. Please select a valid option.");
			}
			// Close scanner and database connection
			scanner.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
