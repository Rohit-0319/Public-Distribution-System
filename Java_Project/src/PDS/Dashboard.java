package PDS;

/*
* Module to view The dashboard of Admin and registered User by thier valid credentials;
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Dashboard extends Login {
	Scanner sc = new Scanner(System.in);

	public void userDashboard() {
		try {
			// Establishing a connection with Database
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/PDS";
			String username = "root";
			String password = "SYSTEM";
			// Establishing the proper connection
			Connection con = DriverManager.getConnection(url, username, password);
			Statement st = con.createStatement();
			// Importing value of ration_card_id from Login Class
			String A = super.ration_card_id;
			// SQL Statement to derive the data from Database Table
			String sql = "Select ration_card_id, members, ration_area_code from User where ration_card_id = ?";
			// Creating an object for preparedStatement
			PreparedStatement pm = con.prepareStatement(sql);
			// Inserting value of a To the SQL Command
			pm.setString(1, A);
			// To get the data from the Database Table.
			ResultSet rs = pm.executeQuery();
			// Heading of the UserDashboard Page
			System.out.println("==========================================================");
			System.out.println("             Public Distribution System Bill              ");
			System.out.println("==========================================================");
			while (rs.next()) {
				// Getting the ration card id from the table
				String rationcardNo = rs.getString("ration_card_id");
				// Getting the Number of family members from the table
				int familyMembers = rs.getInt("members");
				// Getting the Area code from the table
				String areaCode = rs.getString("ration_area_code");
				// Increasing family members with 1 as registered user is also a family member
				// except members of family!
				familyMembers = familyMembers + 1;
				// printing the Number of family members in console
				System.out.println("Number of Family Members: " + familyMembers);
				// printing the ration card id in console
				System.out.println("Ration Card No: " + rationcardNo);
				// printing the Area code in console
				System.out.println("Area Code: " + areaCode);
				// Setting the rate of particular product
				double riceRate = 2.0;
				double saltRate = 3.0;
				double wheatRate = 5.0;
				double pulsesRate = 4.0;
				double totalAmount = 0.0;
				// Printing the bills
				System.out.println("\nBill Details:");
				System.out.println("Product Name | Rate (Rs/kg) | Quantity (kg) | Price (Rs)");
				System.out.println("----------------------------------------------------------");
				// Calculate and display the bill for rice, wheat, salt, and pulses for each
				// member
				String[] products = { "Rice", "Wheat", "Salt", "Pulses" };
				double[] rates = { riceRate, wheatRate, saltRate, pulsesRate };
				int quantity = 4; // kg for rice and wheat, 2 kg for salt and pulses
				for (int i = 0; i < products.length; i++) {
					// Calculating price
					double price = quantity * familyMembers * rates[i];
					System.out.printf("%-12s | %-11.2f  | %-14d| %.2f%n", products[i], rates[i],
							quantity * familyMembers, price);
					// Calculating Total Amount
					totalAmount += price;
				}
				// Printing the final output with total amount displayed!
				System.out.println("----------------------------------------------------------");
				System.out.println("Total Amount: Rs " + totalAmount);
				System.out.println("==========================================================\n");
				// Asking user whether to continue or not!
				System.out.println("Do You Want to Continue (Yes/No)");
				// Taking user input
				String Abd = sc.next();
				if (Abd.equalsIgnoreCase("Yes")) {
					// taking user to Home page
					mainMenu();
				} else {
					// If user will not continue then this message will be displayed!
					System.out.println("Thankyou For Using Public Distribution System");
					// To terminate the program
					System.exit(0);
				}
			}
		} catch (Exception e) {
			// To handle error for the JDBC class!
			e.printStackTrace();
		}

	}

	public void adminDashboard() {
		try {
			// Establishing a connection with Database
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/PDS";

			String username = "root";
			String password = "SYSTEM";

			Connection con = DriverManager.getConnection(url, username, password);

			Statement stmt = con.createStatement();
			// Importing username of admin from login page
			String B = super.Ausername;
			// Changing the String value to Integer
			int c = Integer.valueOf(B);

			// Heading of the AdminDashboard
			System.out.println("product List");
			System.out.println("1. For View");
			System.out.println("2. For Update");
			// Storing the user choice
			int value = sc.nextInt();
			// Using Swith Case
			switch (value) {

			case 1: // for view
				// SQL Statement for getting the data product table
				String select = "Select * from product where admin_id = ?";
				PreparedStatement pm1 = con.prepareStatement(select);
				// Assigning the Username in prepared Statement
				pm1.setInt(1, c);
				ResultSet rs1 = pm1.executeQuery();

				while (rs1.next()) {

					// getting values from database table
					int Dealer = rs1.getInt(1);
					int Ration_Area_Code = rs1.getInt(2);
					int Rice = rs1.getInt(3);
					int Salt = rs1.getInt(4);
					int Pulses = rs1.getInt(5);
					int Wheat = rs1.getInt(6);
					int R_rate = rs1.getInt(7);
					int S_rate = rs1.getInt(8);
					int P_rate = rs1.getInt(9);
					int W_rate = rs1.getInt(10);
					// Printing Heading in table Format by proper spacing.
					System.out.printf(
							"| %-15s | %-15s | %-11s | %-12s | %-13s | %-16s | %-19s | %-20s | %-10s | %-10s |%n",
							"Dealer", "Ration Area Code", "Rice per kg", "Salt per kg", "Pulses per kg", "Wheat per kg",
							"Rice Rate/kg", "Salt rate/kg", "Pulse Rate/kg", "Wheat Rate/kg");

					// Printing values in table Format by proper spacing.
					System.out.printf(
							"| %-15s | %-15s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s |%n",
							Dealer, Ration_Area_Code, Rice, Salt, Pulses, Wheat, R_rate, S_rate, P_rate, W_rate);
				}
				// closing result Set
				rs1.close();

				break;

			case 2: // for update

				// Updating rice quantity
				String update = "update product set Rice= (?) where Rice =(?) and admin_id=? ";
				PreparedStatement pstmts = con.prepareStatement(update);

				// Giving new quantity of rice
				System.out.println("Enter the new Quantity of Rice: ");
				// storing in int r
				int r = sc.nextInt();

				// Giving old quantity of rice
				System.out.println("Enter the old Rice quantity where you want to change: ");
				// storing in int or
				int or = sc.nextInt();

				// Updating salt quantity
				String update1 = "update product set Salt= (?) where Salt =(?) and admin_id=?";
				// using prepared statement for update
				PreparedStatement pstmts1 = con.prepareStatement(update1);

				// Giving new quantity of salt
				System.out.println("Enter the new Quantity of Salt: ");
				// storing in int s
				int s = sc.nextInt();

				// Giving old quantity of salt
				System.out.println("Enter the old Salt quantity where you want to change: ");
				// storing in int os
				int os = sc.nextInt();

				// Updating pulses quantity
				String update2 = "update product set Pulses= (?) where Pulses =(?) and admin_id=?";
				// using prepared statement for update
				PreparedStatement pstmts2 = con.prepareStatement(update2);

				// Giving new quantity of pulses
				System.out.println("Enter the new Quantity of Pulses: ");
				// storing in int p
				int p = sc.nextInt();

				// Giving old quantity of pulses
				System.out.println("Enter the old Pulses quantity where you want to change: ");
				// storing in int op
				int op = sc.nextInt();

				// Updating wheat quantity
				String update3 = "update product set Wheat= (?) where Wheat =(?) and admin_id=?";
				// using prepared statement for update
				PreparedStatement pstmts3 = con.prepareStatement(update3);

				// Giving new quantity of wheat
				System.out.println("Enter the new Quantity of Wheat: ");
				// storing in int w
				int w = sc.nextInt();

				// Giving old quantity of wheat
				System.out.println("Enter the old Wheat quantity where you want to change: ");
				// storing in int ow
				int ow = sc.nextInt();

				// Updating rice rate quantity
				String update4 = "update product set R_rate= (?) where R_rate =(?) and admin_id=?";
				// using prepared statement for update
				PreparedStatement pstmts4 = con.prepareStatement(update4);

				// Giving new price of rice rate
				System.out.println("Enter the new price of Rice_Rate: ");
				// storing in int rr
				int rr = sc.nextInt();

				// Giving old price of rice rate
				System.out.println("Enter the old Rice_Rate price where you want to change: ");
				// storing in int orr
				int orr = sc.nextInt();

				// Updating salt rate quantity
				String update5 = "update product set S_rate= (?) where S_rate =(?) and admin_id=?";
				// using prepared statement for update
				PreparedStatement pstmts5 = con.prepareStatement(update5);

				// Giving new price of salt rate
				System.out.println("Enter the new price of Salt_Rate: ");
				// storing in int sr
				int sr = sc.nextInt();

				// Giving old price of salt rate
				System.out.println("Enter the old Salt_Rate price where you want to change: ");
				// storing in int osr
				int osr = sc.nextInt();

				// Updating pulses rate quantity
				String update6 = "update product set P_rate= (?) where P_rate =(?) and admin_id=?";
				// using prepared statement for update
				PreparedStatement pstmts6 = con.prepareStatement(update6);

				// Giving new price of pulses rate
				System.out.println("Enter the new price of Pulses_Rate: ");
				// storing in int pr
				int pr = sc.nextInt();

				// Giving old price of pulses rate
				System.out.println("Enter the old Pulses_Rate price where you want to change: ");
				// storing in int opr
				int opr = sc.nextInt();

				// Updating wheat rate quantity
				String update7 = "update product set W_rate= (?) where W_rate =(?) and admin_id=?";
				// using prepared statement for update
				PreparedStatement pstmts7 = con.prepareStatement(update7);

				// Giving new price of wheat rate
				System.out.println("Enter the new price of Wheat_Rate: ");
				// storing in int variable
				int wr = sc.nextInt();

				// Giving old price of wheat rate
				System.out.println("Enter the old Wheat_Rate price where you want to change: ");
				// storing in int variable
				int owr = sc.nextInt();
				// To getting the entered username from login page
				System.out.println(c);
				// Input values from database and updating
				pstmts.setInt(1, r);
				pstmts.setInt(2, or);
				pstmts.setInt(3, c);
				pstmts1.setInt(1, s);
				pstmts1.setInt(2, os);
				pstmts1.setInt(3, c);
				pstmts2.setInt(1, p);
				pstmts2.setInt(2, op);
				pstmts2.setInt(3, c);
				pstmts3.setInt(1, w);
				pstmts3.setInt(2, ow);
				pstmts3.setInt(3, c);
				pstmts4.setInt(1, rr);
				pstmts4.setInt(2, orr);
				pstmts4.setInt(3, c);
				pstmts5.setInt(1, sr);
				pstmts5.setInt(2, osr);
				pstmts5.setInt(3, c);
				pstmts6.setInt(1, pr);
				pstmts6.setInt(2, opr);
				pstmts6.setInt(3, c);
				pstmts7.setInt(1, wr);
				pstmts7.setInt(2, owr);
				pstmts7.setInt(3, c);

				// executing all update statements
				pstmts.executeUpdate();
				pstmts1.executeUpdate();
				pstmts2.executeUpdate();
				pstmts3.executeUpdate();
				pstmts4.executeUpdate();
				pstmts5.executeUpdate();
				pstmts6.executeUpdate();
				pstmts7.executeUpdate();

				// Printing statement
				System.out.println("Record has been updated");

				break;
			}
			// Printing statement
			System.out.println("Do You Want to Continue (Yes/No)");
			// Storing in string Abd
			String Abd = sc.next();
			// using if else statement for ignoring case
			if (Abd.equalsIgnoreCase("Yes")) {
				// If user will select yes than our project will navigate to main menu page
				mainMenu();
			} else {
				// Printing statement
				System.out.println("Thankyou For Using Public Distribution System");
				// To terminate the whole project
				System.exit(0);
			}

			// closing try block and doing catch exception
		} catch (Exception e) {

			// storing exception in e
			e.printStackTrace();
		}

	}

}