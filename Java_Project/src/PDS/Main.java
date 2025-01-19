package PDS;

import java.util.*;

/*
* The Public Distribution System is an Indian food security system that was established by the Government of India under the Ministry of Consumer Affairs, Food and Public Distribution to distribute food and non-food items to India's poor at subsidised rates.
* This project aims to provide user 2 options - register and login to take the bill of that month
* This project also aims to provide admin the option to login to view and update the stock.
*/
public class Main {
	public static void main(String[] args) {
		Main main = new Main(); // creation of object for main class
		main.mainMenu(); // calling the main menu method
	}

	public void mainMenu() {
		Scanner sc = new Scanner(System.in);
		// Printing the name of system
		System.out.println("==========================================================");
		System.out.println("                Public Distribution System                ");
		System.out.println("==========================================================");
		System.out.println("1.Register\n2.Login\n3.Exit");// Printing the options
		System.out.println("Enter your choice:");
		int choice = sc.nextInt(); // Users choice
		switch (choice) {
		case 1:
			Registration re = new Registration(); // creating object for Registration class
			re.insert(); // calling registration module to insert data and if valid gets ration card id
							// and passwords
			break;
		case 2:
			Login lg = new Login(); // Creating object for Login class
			lg.loginDetails(); // calling login moduleto provide option to login as user or admin
			break;
		case 3:
			System.out.println("Thankyou For Using Public Distribution System");
			System.exit(0); // To terminate the program
			break;
		}
	}
}