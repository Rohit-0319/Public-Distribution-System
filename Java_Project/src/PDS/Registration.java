package PDS;

/*
* Module to register a new beneficiary based on the criterion and provide Ration card Id and password if valid
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Registration extends Main {
	// Static variables to be used in the program
	static String name;
	String age;
	String mobilenumber;
	String email;
	String address;
	String ward;
	String city;
	String district;
	String pincode;
	String rationareacode;
	String uid;
	int income;
	int members;
	String mem;
	String inc;
	String Check;
	String Check1;
	String Check2;
	String Check3;

	// Method to accept data from user
	public void insert() {
		Scanner sc = new Scanner(System.in); // Scanner class to accept data given by user
		Registration obj = new Registration();// creating object of Registration class to call methods
		System.out.println();
		System.out.println("Welcome to Public Distribution System");
		System.out.println();
		System.out.println();
		System.out.println("PDS aims to provide ration at subsidiary rates to the families below poverty line ");
         System.out.println("To register the annual income of the family must be below Rs.80000 per annum and resident of India");
		System.out.println();
		System.out.println("Fill in the below details to procede further");
		System.out.println();
		// Details of beneficiary
		while (true) {
			System.out.println("Name:");
			name = sc.next(); // Accepting name of the beneficiary
			sc.nextLine();
			try {
				// Checking if name entered is of appropriate length
				if (name.length() < 3 || name.length() > 60) {
					throw new IllegalArgumentException(
							"Invalid name: Name must be between 3 and 50 characters!!! Please re-enter");
				}
				// Checking if name accepts only alphabets.
				if (!name.matches("^[a-zA-Z]+$")) {
					throw new InputMismatchException("Invalid Name:Please Enter Valid name containing letters");
				} else {
					break;
				}
			} // to catch the exceptions and print the respective message to continue the
				// system
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			}
		}
		while (true) {
			System.out.println("Age");// Accepting age of the beneficiary
			age = sc.next();
			try {
				// Checking if age accepts only numbers.
				if (!age.matches("^[0-9]+$")) {
					throw new InputMismatchException("Please Enter Valid age ");
				}
				int ag = Integer.valueOf(age);// converting string to int
				if (ag < 18 || ag > 100) {// to check if the age is above 18 years and below 100
					throw new IllegalArgumentException("Error: Age must be more than 18 years or less than 100 years");
				} else {
					break;
				}
			}
			// to catch the exceptions and print the respective message to continue the
			// system
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			}
		}
		while (true) {
			System.out.println("Mobile Number:");// Accepting mobile of the beneficiary
			mobilenumber = sc.next();
			try {
				// Checking if name entered is of appropriate length
				if (mobilenumber.length() < 10 || mobilenumber.length() > 10) {
					throw new IllegalArgumentException("Enter valid Mobile Number");
				}
				// Checking if name accepts only alphabets.
				if (!mobilenumber.matches("^[0-9]+$")) {
					throw new InputMismatchException("Please Enter Valid Mobile Number ");
				} else {
					break;
				}
			}
			// to catch the exceptions and print the respective message to continue the
			// system
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			}
		}
		while (true) {
			System.out.println("Email Id:");// Accepting email Id of the beneficiary
			email = sc.next();
			sc.nextLine();
			try {
				// Checking if email entered is of appropriate length
				if (email.length() < 8) {
					throw new IllegalArgumentException("Enter valid email id");
				}
				// Checking if the email is valid
				if (!(email.endsWith("@gmail.com") || email.endsWith("@yahoo.com")))
					throw new InputMismatchException("Please Enter Valid email id");
				if (!email.matches("^[a-zA-Z0-9.@]+$")) {
					throw new InputMismatchException("Please Enter Valid email id");
				} else {
					break;
				}
			}
			// to catch the exceptions and print the respective message to continue the
			// system
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			}
		}

		while (true) {
			System.out.println("Address");// Accepting address of the beneficiary
			address = sc.next();
			sc.nextLine();
			try {
				// Checking if address entered is of appropriate length
				if (address.length() < 3 || address.length() > 200) {
					throw new IllegalArgumentException("Enter valid address");
				}
				// Checking if address accepts only alphabets.
				if (!address.matches("^[a-zA-Z,-/.]+$")) {
					throw new InputMismatchException("Enter valid address");
				} else {
					break;
				}
			}
			// to catch the exceptions and print the respective message to continue the
			// system
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			}
		}

		while (true) {
			System.out.println("Ward:");// Accepting ward of the beneficiary
			ward = sc.next();
			sc.nextLine();
			try {
				// Checking if ward entered is of appropriate length
				if (ward.length() < 3 || ward.length() > 3) {
					throw new IllegalArgumentException("Enter valid ward");
				}
				// Checking if ward accepts only alphabets.
				if (!ward.matches("^[0-9]+$")) {
					throw new InputMismatchException("Enter valid ward");
				} else {
					break;
				}
			}
			// to catch the exceptions and print the respective message to continue the
			// system
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			}
		}

		while (true) {
			System.out.println("City:");// Accepting city of the beneficiary
			city = sc.next();
			sc.nextLine();
			try {
				// Checking if city entered is of appropriate length
				if (city.length() < 3 || city.length() > 50) {
					throw new IllegalArgumentException("Enter valid city");
				}
				// Checking if city accepts only alphabets.
				if (!city.matches("^[a-zA-Z]+$")) {
					throw new InputMismatchException("Enter valid city");
				} else {
					break;
				}
				// to catch the exceptions and print the respective message to continue the
				// system
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			}
		}
		// Accepting District
		while (true) {
			System.out.println("District:");
			district = sc.next();
			sc.nextLine();
			try {
				// Checking if district entered is of appropriate length
				if (district.length() < 3 || district.length() > 50) {
					throw new IllegalArgumentException("Enter valid district");
				}
				// Checking if district accepts only alphabets.
				if (!district.matches("^[a-zA-Z]+$")) {
					throw new InputMismatchException("Enter valid district");
				} else {
					break;
				}
				// to catch the exceptions and print the respective message to continue the
				// system
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			}
		}
		// Entering Pincode
		while (true) {
			System.out.println("Pincode:");
			pincode = sc.next();
			try {
				// Checking if pincode entered is of appropriate length
				if (pincode.length() < 6 || pincode.length() > 6) {
					throw new IllegalArgumentException("Enter valid pincode");
				}
				// Checking if pincode accepts only alphabets.
				if (!pincode.matches("^[0-9]+$")) {
					throw new InputMismatchException("Enter valid pincode");
				} else {
					break;
				}
				// to catch the exceptions and print the respective message to continue the
				// system
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			}
		}
		// Entering Ration Area Code
		while (true) {
			System.out.println("Ration Area Code:");
			rationareacode = sc.next();
			try {
				// Checking if ration area code entered is of appropriate length
				if (rationareacode.length() < 4 || rationareacode.length() > 4) {
					throw new IllegalArgumentException("Enter valid Ration area code");
				}
				// Checking if ration area code accepts only numbers.
				if (!rationareacode.matches("^[0-9]+$")) {
					throw new InputMismatchException("Enter valid Ration area code");
				} else {
					break;
				}
				// to catch the exceptions and print the respective message to continue the
				// system
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			}
		}
		try {
			// Steps to connect with db
			// JDBC Driver -- Java class
			// Step 1 load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");// method for loading driver class.forName()
			// Step 2 make connection
			String url = "jdbc:mysql://localhost:3306/pds";
			// Step 3 Provide Username & Password
			String username = "root";
			String password = "SYSTEM";
			Connection con = DriverManager.getConnection(url, username, password);

			// Entering UID number
			while (true) {
				System.out.println("UID No.:");
				uid = sc.next();
				sc.nextLine();
				try {
					// To check if the UID Number is not duplicate (not present in User table)
					Check = "Select * from user where uid_no = ?";
					PreparedStatement pstmt1 = con.prepareStatement(Check);
					pstmt1.setString(1, uid);
					ResultSet checkUid = pstmt1.executeQuery();
					if (checkUid.next() && checkUid.getInt(1) > 0) {
						throw new Exception(
								"The Member is ALready Registered with this UID \n  Please Enter Valid UID");
					}
					// To check if the UID Number is not duplicate (not present in Member table)
					Check3 = "Select * from members where uid_no = ?";
					PreparedStatement pstmt4 = con.prepareStatement(Check3);
					pstmt4.setString(1, uid);
					ResultSet checkUid3 = pstmt4.executeQuery();
					if (checkUid3.next() && checkUid3.getInt(1) > 0) {
						throw new Exception("You Have entered the same UID  \n  Please Enter Valid UID");
					}

					// Checking if UID entered is of appropriate length
					if (uid.length() < 12 || uid.length() > 12) {
						throw new IllegalArgumentException("Enter valid UID number");
					}
					// Checking if UID accepts only numbers.
					if (!uid.matches("^[0-9]+$")) {
						throw new InputMismatchException("Enter valid UID number");
					} else {
						break;
					}
					// to catch the exceptions and print the respective message to continue the
					// system
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				} catch (InputMismatchException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			// Entering Income
			while (true) {
				System.out.println("Current Income:");
				inc = sc.next();
				income = Integer.parseInt(inc);
				sc.nextLine();
				try {
					// Checking if name entered is of appropriate length
					if (inc.length() < 0 || inc.length() > 5) {
						throw new IllegalArgumentException("Enter valid Income");
					}
					// Checking if income accepts only alphabets.
					if (!inc.matches("^[0-9]+$")) {
						throw new InputMismatchException("Enter valid Income");
					}
					// checking if Income is less than 80000
					if (income > 80000) {
						throw new InputMismatchException("ENter Valid Income");
					} else {
						break;
					}
					// to catch the exceptions and print the respective message to continue the
					// system
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				} catch (InputMismatchException e) {
					System.out.println(e.getMessage());
				}
			}
			// Entering members in family
			while (true) {
				System.out.println("Number of members in a family:");
				mem = sc.next();
				sc.nextLine();
				members = Integer.parseInt(mem);
				try {
					// Checking if members entered is of appropriate length
					if (members < 0) {
						throw new IllegalArgumentException("Enter valid number of members in te family");
					}

					// Checking if the members contains only numbers
					if (!mem.matches("^[0-9]+$")) {
						throw new InputMismatchException("Enter valid number of members in te family");
					} else {
						break;
					}
					// to catch the exceptions and print the respective message to continue the
					// system
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				} catch (InputMismatchException e) {
					System.out.println(e.getMessage());
				}
			}

			// Attributes of family members
			String[] famname = new String[members + 1];
			String famuid[] = new String[members + 1];
			String relation[] = new String[members + 1];
			// loop for entering details of family members
			for (int i = 1; i <= members; i++) {
				// Entering name of member
				while (true) {
					System.out.println("Name of member");
					famname[i] = sc.next();
					sc.nextLine();
					try {
						// Checking if name entered is of appropriate length
						if (famname[i].length() < 3 || famname[i].length() > 50) {
							throw new IllegalArgumentException("Error: Name must be between 3 and 50 characters!!!");
						}
						// Checking if name accepts only alphabets.
						if (!famname[i].matches("^[a-zA-Z]+$")) {
							throw new InputMismatchException("Please Enter Valid name containing letters");
						} else {
							break;
						}
						// to catch the exceptions and print the respective message to continue the
						// system
					} catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					} catch (InputMismatchException e) {
						System.out.println(e.getMessage());
					}
				}
				// Entering the UID Number of member
				while (true) {
					System.out.println("UID No. of Member");
					famuid[i] = sc.next();
					sc.nextLine();
					// To check if the UID Number is not duplicate (not present in Members table)
					try {
						Check1 = "Select * from members where uid_no = ?";
						PreparedStatement pstmt2 = con.prepareStatement(Check1);
						pstmt2.setString(1, famuid[i]);
						ResultSet checkUid1 = pstmt2.executeQuery();
						if (checkUid1.next() && checkUid1.getInt(1) > 0) {
							throw new Exception("You Have entered the same UID  \n  Please Enter Valid UID");
						}
						// To check if the UID Number is not duplicate (not present in User table)
						Check2 = "Select * from user where uid_no = ?";
						PreparedStatement pstmt3 = con.prepareStatement(Check1);
						pstmt3.setString(1, famuid[i]);
						ResultSet checkUid2 = pstmt3.executeQuery();
						if (checkUid2.next() && checkUid2.getInt(1) > 0) {
							throw new Exception("You Have entered the same UID  \n  Please Enter Valid UID");
						}
						if (famuid[i].equals(uid)) {
							throw new Exception("You Have entered the same UID  \n  Please Enter Valid UID");
						}

						// Checking if uid entered is of appropriate length
						if (famuid[i].length() < 12 || famuid[i].length() > 12) {
							throw new IllegalArgumentException("Enter valid UID number");
						}
						// Checking if UID accepts only numbers
						if (!famuid[i].matches("^[0-9]+$")) {
							throw new InputMismatchException("Enter valid UID number");
						} else {
							break;
						}
						// to catch the exceptions and print the respective message to continue the
						// system
					} catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					} catch (InputMismatchException e) {
						System.out.println(e.getMessage());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				// To ask for relation
				while (true) {
					System.out.println("Relation with the person");
					relation[i] = sc.next(); // Entering the relation of member with the user
					sc.nextLine();
					try {
						// Checking if relation entered is of appropriate length
						if (relation[i].length() < 3 || relation[i].length() > 50) {
							throw new IllegalArgumentException("Enter valid relationship");
						}
						// Checking if the relation are letters
						if (!relation[i].matches("^[a-zA-Z]+$")) {
							throw new InputMismatchException("Enter valid relationship");
						}
						// Checking if the relation is valid
						if (!(relation[i].equalsIgnoreCase("son") || relation[i].equalsIgnoreCase("daughter")
								|| relation[i].equalsIgnoreCase("mother") || relation[i].equalsIgnoreCase("father")
								|| relation[i].equalsIgnoreCase("husband") || relation[i].equalsIgnoreCase("wife")))
							throw new InputMismatchException("Enter valid relationship");
						else
							break;
					}
					// to catch the exceptions and print the respective message to continue the
					// system
					catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					} catch (InputMismatchException e) {
						System.out.println(e.getMessage());
					}
				}
			}
			System.out.println("Successfully registered"); // Printing the successfully registered message
			String ration_card_id = obj.RationCardIdGenerate(); // calling ration card id generation method to get the
																// ration card id
			System.out.println("Your Ration Card Id - " + ration_card_id); // Printing the ration card ID of the
																			// beneficiary
			String passwordforID = obj.PasswordGenerate(name); // calling password generation method to get the password
			System.out.println("Your Password - " + passwordforID); // Printing the password
			// Using JDBC to insert the details of the beneficiary in the user table in PDS
			// Database
			String insert = "insert into User(ration_card_id,username,age,mobile_number,email,address,ward, city , district, pincode,ration_area_code, uid_no, income ,members,passwordforID) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";// SQL
																																																										// Statement
																																																										// in
																																																										// String
			PreparedStatement pstmt = con.prepareStatement(insert);// object to callPrepared statement
			pstmt.setString(1, ration_card_id);// Entering ration_card_id in table
			pstmt.setString(2, name);// Entering name in table
			pstmt.setString(3, age);// Entering age in table
			pstmt.setString(4, mobilenumber);// Entering mobile number in table
			pstmt.setString(5, email);// Entering email in table
			pstmt.setString(6, address);// Entering address in table
			pstmt.setString(7, ward);// Entering ward number in table
			pstmt.setString(8, city);// Entering city in table
			pstmt.setString(9, district);// Entering district in table
			pstmt.setString(10, pincode);// Entering pincode in table
			pstmt.setString(11, rationareacode);// Entering ration area code in table
			pstmt.setString(12, uid); // Entering Aadhar number/ UID number in table
			pstmt.setInt(13, income); // Entering income in table
			pstmt.setInt(14, members); // Entering number of members in the family in table
			pstmt.setString(15, passwordforID); // Entering password in table
			pstmt.executeUpdate(); // Updates the database

			// Entering the details of members in the members table
			for (int i = 1; i <= members; i++) {
				String insert2 = "insert into Members(ration_card_id,name, uid_no, relation) values(?,?,?,?)"; // SQL
																												// statement
				PreparedStatement pstmt2 = con.prepareStatement(insert2); // object to callPrepared statement
				pstmt2.setString(1, ration_card_id); // Entering ration card id in table
				pstmt2.setString(2, famname[i]); // Entering name of member in table
				pstmt2.setString(3, famuid[i]); // Entering aadhar id of member in database
				pstmt2.setString(4, relation[i]); // Entering relation with the member in database
				pstmt2.executeUpdate(); // Updates the database

			}
			// to catch the exceptions and print the respective message to continue the
			// system
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Asking the user if they want to continue
		System.out.println("Do You Want To Continue? (Yes/No)");
		String ans = sc.next(); // User's answer
		if (ans.equalsIgnoreCase("Yes")) {
			mainMenu(); // calling main menu method
		} else {
			System.out.println("Thankyou For Using Public Distribution System"); // Printing the end message
			System.exit(0); // To terminate the program
		}
	}

	public String PasswordGenerate(String name) {
		// Create a Random object
		Random random = new Random();
		// Generate a random Password
		int pass = random.nextInt(9000) + 1000; // Generates a random 4-digit number
		String n = name.substring(0, 3); // To store first 3 letters of name
		String newpassword = pass + "@IND" + n; // To create password using random 4 digits+@IND+ First 3 letters of
												// name
		return newpassword;// To return password in the main
	}

	public String RationCardIdGenerate() {
		// Create a Random object
		Random random = new Random();
		// Generate a random login ID, e.g., a 6-digit number
		int loginId = random.nextInt(900000) + 100000; // Generates a random 6-digit number
		String Id = Integer.toString(loginId); // to convert integer to String
		return Id; // To return Ration Id in the main
	}
}