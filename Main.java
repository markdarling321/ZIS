// NAME:	Mark Darling
// CLASS:	CEN-4025-01Z
// DATE:	10/31/21
// PROGRAM:	Zoo Information System

// BUGS / ISSUES:
//
// 1.) 

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
package com.student;

// IMPORTS
import java.io.*;
import java.util.*;

public class Main {

	// DEFINE CLASS VARIABLES...
	private static final String IN_FILE = "ZooInfo.txt";

	// CREATE ArrayList REFERENCE OF DATATYPE Inventory
	private static ArrayList<Staff> staff = new ArrayList<Staff>();

	// CREATE ArrayList REFERENCE OF DATATYPE CustomerPurchases
	private static ArrayList<Animal> animals = new ArrayList<Animal>();

	// DEFINE CUSTOM DATATYPE CLASSES...
	private static class Staff {

		// INSTANCE VARIABLES OF Staff CLASS
		String userId;							// 7 DIGITS
		String pin;								// 4 DIGITS
		double hourlyRate;                      // $ _ _ _ . _ _
		String firstHiredDate;                  // MM-DD-YYYY
		String jobTitle;                        // 120 CHAR MAX
		String department;                      // 120 CHAR MAX
		String name;                            // 80 CHAR MAX
		String phoneNumber;                     // ### - ### - ####
		String reportsTo;                       // 80 CHAR MAX
		int accessLevel;						// 0 (TEMPORARY) to 9 (ADMIN/FULL ACCESS)

		// CONSTRUCTOR
		public Staff( String userId,
					  String pin,
					  double hourlyRate,
					  String firstHiredDate,
					  String jobTitle,
					  String department,
					  String name,
					  String phoneNumber,
					  String reportsTo,
					  int accessLevel
		){
			this.userId = userId;
			this.pin = pin;
			this.hourlyRate = hourlyRate;
			this.firstHiredDate = firstHiredDate;
			this.jobTitle = jobTitle;
			this.department = department;
			this.name = name;
			this.phoneNumber = phoneNumber;
			this.reportsTo = reportsTo;
			this.accessLevel = accessLevel;
		}
	}// END Staff{}

	private static class Animal {

		// INSTANCE VARIABLES OF Animals CLASS
		String idNumber;                        // 7 DIGITS
		String sex;                             // 'M' or 'F'
		String species;                         // 120 CHAR MAX
		String name;                            // 120 CHAR MAX
		String birthDate;                       // MM-DD-YYYY or 'UNKNOWN'
		String dateOfArrival;                   // MM-DD-YYYY or 'UNKNOWN'
		String dateOfDeparture;                 // MM-DD-YYYY or 'UNKNOWN' or N/A
		String dietClass;                       // 'CARNIVORE' 'HERBIVORE' 'OMNIVORE' 'FRUGIVORE'
		int preferredTemperature;            	// +- ### F
		int minimumTemperature;              	// +- ### F
		int maximumTemperature;              	// +- ### F
		String notes;                           // current injuries, recent surgeries or procedures, temporary restrictions, etc

		// CONSTRUCTOR
		public Animal(  String idNumber,
						String sex,
						String species,
						String name,
						String birthDate,
						String dateOfArrival,
						String dateOfDeparture,
						String dietClass,
						int preferredTemperature,
						int minimumTemperature,
						int maximumTemperature,
						String notes
		){
			this.idNumber = idNumber;
			this.sex = sex;
			this.species = species;
			this.name = name;
			this.birthDate = birthDate;
			this.dateOfArrival = dateOfArrival;
			this.dateOfDeparture = dateOfDeparture;
			this.dietClass = dietClass;
			this.preferredTemperature = preferredTemperature;
			this.minimumTemperature = minimumTemperature;
			this.maximumTemperature = maximumTemperature;
			this.notes = notes;
		}
	}// END Animals{}

	// PROGRAM START / CORE LOGIC...
	public static void main(String[] args) {

		// LOAD INVENTORY BY READING INPUT FILE
		// (IDEALLY THIS WOULD BE AN ENCRYPTED DATABASE)
		loadData();

		// PRESENT MAIN MENU AND WAIT FOR USER INPUT
		while (mainMenu());

	}// END main()

	// CORE METHODS...
	private static void loadData() {

		// CREATE INPUT STREAM OBJECT
		Scanner inStream = null;

		// ATTEMPT TO OPEN THE FILE FOR READING
		try {
			inStream = new Scanner(new File(IN_FILE));

		}
		// HANDLE EXCEPTIONS
		catch (FileNotFoundException fnfe) {

			System.out.println("ERROR!  --  " + IN_FILE + " DOES NOT EXIST!");
		}

		// READ FILE CONTENTS
		while (inStream.hasNextLine()) {

			// GET NEXT LINE OF DATA
			String line = inStream.nextLine();

			////////////////////////////////////////////
			// PARSE PROGRAM DATA FROM TEXT FILE HERE //
			// (IDEALLY THIS WOULD BE A DATABASE)	  //
			////////////////////////////////////////////

			// PARSE LINE OF DATA INTO ARRAY OF DATA POINTS
			String[] data = line.split(";");

			// PARSE & STORE DATA POINTS IN TEMPORARY LOCAL VARIABLES
			if (data[0].equals("USER"))
			{
				String userId = data[1];
				String pin = data[2];
				double hourlyRate = Double.parseDouble(data[3]);
				String firstHiredDate = data[4];
				String jobTitle = data[5];
				String department = data[6];
				String name = data[7];
				String phoneNumber = data[8];
				String reportsTo = data[9];
				int accessLevel = Integer.parseInt(data[10]);

				// PUSH DATA TO MEMORY
				staff.add(new Staff(userId, pin, hourlyRate, firstHiredDate, jobTitle,
						department, name, phoneNumber, reportsTo, accessLevel));
			}
			else if (data[0].equals("ANIMAL"))
			{
				String idNumber = data[1];
				String sex = data[2];
				String species = data[3];
				String name = data[4];
				String birthDate = data[5];
				String dateOfArrival = data[6];
				String dateOfDeparture = data[7];
				String dietClass = data[8];
				int preferredTemperature = Integer.parseInt(data[9]);
				int minimumTemperature = Integer.parseInt(data[10]);
				int maximumTemperature = Integer.parseInt(data[11]);
				String notes = data[12];

				// PUSH DATA TO MEMORY
				animals.add(new Animal(idNumber, sex, species, name, birthDate, dateOfArrival, dateOfDeparture,
									   dietClass, preferredTemperature, minimumTemperature, maximumTemperature,
									   notes));
			}
		}// END while(){}
	}// END loadData()

	private static boolean mainMenu() {

		try {
			System.out.println("============================");
			System.out.println("|  ZOO INFORMATION SYSTEM  |");
			System.out.println("============================");
			System.out.println("[1] Punch Clock");
			System.out.println("[2] View Time Card");
			System.out.println("[3] Admin");
			System.out.println("[4] Settings");
			System.out.println("[5] Exit");
			System.out.println("[6] DISPLAY ALL USERS (DEBUG)");
			System.out.println("[7] DISPLAY ALL ANIMALS (DEBUG)");

			// CREATE NEW INPUT STREAM OBJECT
			Scanner input = new Scanner(System.in);

			// TAKE INPUT FROM USER
			int choice = input.nextInt();

			switch (choice) {
				case 1:
					punchClock();
					return true;
				case 2:
					viewTimeCard();
					return true;
				case 3:
					adminConsole();
					return true;
				case 4:
					applicationSettings();
					return true;
				case 5:
					return false;
				case 6:
					displayAllUsers();
					return true;
				case 7:
					displayAllAnimals();
				default:
					return mainMenu();
			}// END switch()
		}
		// HANDLE INVALID MENU CHOICES
		catch (Exception e) {

			return mainMenu();
		}
	}// END mainMenu()

	private static boolean masterMenu() {

		try {
			System.out.println("============================");
			System.out.println("|       MASTER MENU        |");
			System.out.println("============================");
			System.out.println("[1] Master1");
			System.out.println("[2] Master2");
			System.out.println("[3] Master3");
			System.out.println("[4] Master4");
			System.out.println("[5] Exit");

			// CREATE NEW INPUT STREAM OBJECT
			Scanner input = new Scanner(System.in);

			// TAKE INPUT FROM USER
			int choice = input.nextInt();

			switch (choice) {
				case 1:
					// OPTION 1
					return true;
				case 2:
					// OPTION 2
					return true;
				case 3:
					// OPTION 3
					return true;
				case 4:
					// OPTION 4
					return true;
				case 5:
					return false;
				default:
					return masterMenu();
			}// END switch()
		}
		// HANDLE INVALID MENU CHOICES
		catch (Exception e) {

			return masterMenu();
		}
	}// END masterMenu()

	private static boolean adminMenu() {

		try {
			System.out.println("============================");
			System.out.println("|        ADMIN MENU        |");
			System.out.println("============================");
			System.out.println("[1] Admin1");
			System.out.println("[2] Admin2");
			System.out.println("[3] Admin3");
			System.out.println("[4] Admin4");
			System.out.println("[5] Exit");

			// CREATE NEW INPUT STREAM OBJECT
			Scanner input = new Scanner(System.in);

			// TAKE INPUT FROM USER
			int choice = input.nextInt();

			switch (choice) {
				case 1:
					// OPTION 1
					return true;
				case 2:
					// OPTION 2
					return true;
				case 3:
					// OPTION 3
					return true;
				case 4:
					// OPTION 4
					return true;
				case 5:
					return false;
				default:
					return adminMenu();
			}// END switch()
		}
		// HANDLE INVALID MENU CHOICES
		catch (Exception e) {

			return adminMenu();
		}
	}// END adminMenu()

	private static boolean ownerMenu() {

		try {
			System.out.println("============================");
			System.out.println("|        OWNER MENU        |");
			System.out.println("============================");
			System.out.println("[1] Owner1");
			System.out.println("[2] Owner2");
			System.out.println("[3] Owner3");
			System.out.println("[4] Owner4");
			System.out.println("[5] Exit");

			// CREATE NEW INPUT STREAM OBJECT
			Scanner input = new Scanner(System.in);

			// TAKE INPUT FROM USER
			int choice = input.nextInt();

			switch (choice) {
				case 1:
					// OPTION 1
					return true;
				case 2:
					// OPTION 2
					return true;
				case 3:
					// OPTION 3
					return true;
				case 4:
					// OPTION 4
					return true;
				case 5:
					return false;
				default:
					return ownerMenu();
			}// END switch()
		}
		// HANDLE INVALID MENU CHOICES
		catch (Exception e) {

			return ownerMenu();
		}
	}// END ownerMenu()

	private static boolean generalManagerMenu() {

		try {
			System.out.println("============================");
			System.out.println("|   GENERAL MANAGER MENU   |");
			System.out.println("============================");
			System.out.println("[1] GeneralManager1");
			System.out.println("[2] GeneralManager2");
			System.out.println("[3] GeneralManager3");
			System.out.println("[4] GeneralManager4");
			System.out.println("[5] Exit");

			// CREATE NEW INPUT STREAM OBJECT
			Scanner input = new Scanner(System.in);

			// TAKE INPUT FROM USER
			int choice = input.nextInt();

			switch (choice) {
				case 1:
					// OPTION 1
					return true;
				case 2:
					// OPTION 2
					return true;
				case 3:
					// OPTION 3
					return true;
				case 4:
					// OPTION 4
					return true;
				case 5:
					return false;
				default:
					return generalManagerMenu();
			}// END switch()
		}
		// HANDLE INVALID MENU CHOICES
		catch (Exception e) {

			return generalManagerMenu();
		}
	}// END generalManagerMenu()

	private static boolean upperManagementMenu() {

		try {
			System.out.println("============================");
			System.out.println("|   UPPER MANAGEMENT MENU  |");
			System.out.println("============================");
			System.out.println("[1] UpperManagement1");
			System.out.println("[2] UpperManagement2");
			System.out.println("[3] UpperManagement3");
			System.out.println("[4] UpperManagement");
			System.out.println("[5] Exit");

			// CREATE NEW INPUT STREAM OBJECT
			Scanner input = new Scanner(System.in);

			// TAKE INPUT FROM USER
			int choice = input.nextInt();

			switch (choice) {
				case 1:
					// OPTION 1
					return true;
				case 2:
					// OPTION 2
					return true;
				case 3:
					// OPTION 3
					return true;
				case 4:
					// OPTION 4
					return true;
				case 5:
					return false;
				default:
					return upperManagementMenu();
			}// END switch()
		}
		// HANDLE INVALID MENU CHOICES
		catch (Exception e) {

			return upperManagementMenu();
		}
	}// END upperManagementMenu()

	private static boolean middleManagementMenu() {

		try {
			System.out.println("============================");
			System.out.println("|  MIDDLE MANAGEMENT MENU  |");
			System.out.println("============================");
			System.out.println("[1] MiddleManagement1");
			System.out.println("[2] MiddleManagement2");
			System.out.println("[3] MiddleManagement3");
			System.out.println("[4] MiddleManagement");
			System.out.println("[5] Exit");

			// CREATE NEW INPUT STREAM OBJECT
			Scanner input = new Scanner(System.in);

			// TAKE INPUT FROM USER
			int choice = input.nextInt();

			switch (choice) {
				case 1:
					// OPTION 1
					return true;
				case 2:
					// OPTION 2
					return true;
				case 3:
					// OPTION 3
					return true;
				case 4:
					// OPTION 4
					return true;
				case 5:
					return false;
				default:
					return middleManagementMenu();
			}// END switch()
		}
		// HANDLE INVALID MENU CHOICES
		catch (Exception e) {

			return middleManagementMenu();
		}
	}// END middleManagementMenu()

	private static boolean employeeMenu() {

		try {
			System.out.println("============================");
			System.out.println("|      EMPLOYEE MENU       |");
			System.out.println("============================");
			System.out.println("[1] Employee1");
			System.out.println("[2] Employee2");
			System.out.println("[3] Employee3");
			System.out.println("[4] Employee4");
			System.out.println("[5] Exit");

			// CREATE NEW INPUT STREAM OBJECT
			Scanner input = new Scanner(System.in);

			// TAKE INPUT FROM USER
			int choice = input.nextInt();

			switch (choice) {
				case 1:
					// OPTION 1
					return true;
				case 2:
					// OPTION 2
					return true;
				case 3:
					// OPTION 3
					return true;
				case 4:
					// OPTION 4
					return true;
				case 5:
					return false;
				default:
					return employeeMenu();
			}// END switch()
		}
		// HANDLE INVALID MENU CHOICES
		catch (Exception e) {

			return employeeMenu();
		}
	}// END employeeMenu()

	private static boolean contractorMenu() {

		try {
			System.out.println("============================");
			System.out.println("|     CONTRACTOR MENU      |");
			System.out.println("============================");
			System.out.println("[1] Contractor1");
			System.out.println("[2] Contractor2");
			System.out.println("[3] Contractor3");
			System.out.println("[4] Contractor4");
			System.out.println("[5] Exit");

			// CREATE NEW INPUT STREAM OBJECT
			Scanner input = new Scanner(System.in);

			// TAKE INPUT FROM USER
			int choice = input.nextInt();

			switch (choice) {
				case 1:
					// OPTION 1
					return true;
				case 2:
					// OPTION 2
					return true;
				case 3:
					// OPTION 3
					return true;
				case 4:
					// OPTION 4
					return true;
				case 5:
					return false;
				default:
					return contractorMenu();
			}// END switch()
		}
		// HANDLE INVALID MENU CHOICES
		catch (Exception e) {

			return contractorMenu();
		}
	}// END contractorMenu()

	private static boolean vendorMenu() {

		try {
			System.out.println("============================");
			System.out.println("|       VENDOR MENU        |");
			System.out.println("============================");
			System.out.println("[1] Vendor1");
			System.out.println("[2] Vendor2");
			System.out.println("[3] Vendor3");
			System.out.println("[4] Vendor4");
			System.out.println("[5] Exit");

			// CREATE NEW INPUT STREAM OBJECT
			Scanner input = new Scanner(System.in);

			// TAKE INPUT FROM USER
			int choice = input.nextInt();

			switch (choice) {
				case 1:
					// OPTION 1
					return true;
				case 2:
					// OPTION 2
					return true;
				case 3:
					// OPTION 3
					return true;
				case 4:
					// OPTION 4
					return true;
				case 5:
					return false;
				default:
					return vendorMenu();
			}// END switch()
		}
		// HANDLE INVALID MENU CHOICES
		catch (Exception e) {

			return vendorMenu();
		}
	}// END vendorMenu()

	private static void displayAllUsers()
	{
		// ITERATE OVER EACH ArrayList<Staff> REFERENCE CONTAINED WITHIN THE LIST
		for (Staff human : staff)
		{
			System.out.println();
			System.out.print("human.userId: ");
			System.out.println(human.userId);
			System.out.print("human.pin: ");
			System.out.println(human.pin);
			System.out.println();
		}
	}// END displayAllUsers()

	private static void displayAllAnimals()
	{
		// ITERATE OVER EACH ArrayList<Animal> REFERENCE CONTAINED WITHIN THE LIST
		for (Animal animal : animals)
		{
			System.out.println();
			System.out.print("animal.idNumber: ");
			System.out.println(animal.idNumber);
			System.out.print("animal.name: ");
			System.out.println(animal.name);
			System.out.println();
		}
	}// END displayAllAnimals()

	private static boolean validateUser(String userId, String pin)
	{
		boolean validationStatus = false;

		// ITERATE OVER EACH ArrayList<Staff> REFERENCE CONTAINED WITHIN THE LIST
		for (Staff human : staff)
		{
			// IF BOTH THE userId AND pin FROM THE LIST MATCH WHAT WAS ENTERED
			// BY PERSON USING SYSTEM THIS SESSION, VALIDATION SUCCESSFUL
			if (human.userId.equals(userId) && human.pin.equals(pin))
			{
				validationStatus = true;
				break;
			}
			else
				validationStatus = false;
		}

		return validationStatus;
	}// END validateUser()

	private static int getAccessLevel(String userId)
	{
		int accessLevel = 0;

		for (Staff staff : staff)
		{
			if (staff.userId.equals(userId))
				accessLevel = staff.accessLevel;
		}

		return accessLevel;
	}// END getAccessLevel()

	private static void punchClock()
	{
		// CREATE INPUT OBJECT REFERENCE
		Scanner userInput = new Scanner(System.in);

		// TAKE USER INPUT
		System.out.println("ENTER USER ID");
		String userId = userInput.next();

		System.out.println("ENTER PIN");
		String pin = userInput.next();

		// VALIDATE USER
		if (validateUser(userId, pin))
		{
			// CHECK IF USER ID LAST PUNCHED IN OR OUT
			// BY VERIFYING DAY AND TIME OF LAST PUNCH
			//...
			// BASED ON THAT INFORMATION, STORE CURRENT PUNCH TIME
			// AS CLOCKING "IN" OR CLOCKING "OUT"

			// LOG USER ID & PUNCH TIME TO DATABASE FOR STAFF MEMBER
			System.out.println("Punch accepted.");

			// DETERMINE ACCESS LEVEL OF STAFF MEMBER USING SYSTEM
			int accessLevel = getAccessLevel(userId);

			// PRESENT APPROPRIATE SYSTEM OPERATIONS MENU
			switch (accessLevel) {
				case 0: break;
				case 1:	vendorMenu();
				case 2:	contractorMenu();
				case 3: employeeMenu();
				case 4:	middleManagementMenu();
				case 5:	upperManagementMenu();
				case 6:	generalManagerMenu();
				case 7:	ownerMenu();
				case 8:	adminMenu();
				case 9:	masterMenu();
				default: break;
			}
		}
		else
		{
			System.out.println("ERROR: Unable to validate user!");
		}
	}// END punchClock()

	private static void viewTimeCard()
	{
		// CREATE INPUT OBJECT REFERENCE
		Scanner userInput = new Scanner(System.in);

		// TAKE USER INPUT
		System.out.println("ENTER USER ID");
		String userId = userInput.next();

		System.out.println("ENTER PIN");
		String pin = userInput.next();

		// VALIDATE USER
		if (validateUser(userId, pin))
		{
			// USE LOOP TO RETRIEVE ALL OF USER ID's TIME CLOCK PUNCHES
			// FOR THE WEEK STARTING AT 12:01 AM ON SUNDAY AND ENDING
			// AT 11:59 PM ON SATURDAY

			// DISPLAY ALL OF USER'S TIME PUNCHES FOR THE WEEK
			System.out.println("Your hours for this week are: ");
			System.out.println("SUNDAY: ");
			System.out.println("MONDAY: ");
			System.out.println("TUESDAY: ");
			System.out.println("WEDNESDAY: ");
			System.out.println("THURSDAY: ");
			System.out.println("FRIDAY: ");
			System.out.println("SATURDAY: ");
			System.out.println("TOTAL: ");
		}
		else
		{
			System.out.println("ERROR: Unable to validate user!");
		}
	}// END viewTimeCard()

	private static void adminConsole()
	{
		// CREATE INPUT OBJECT REFERENCE
		Scanner userInput = new Scanner(System.in);

		// TAKE USER INPUT
		System.out.println("ENTER USER ID");
		String userId = userInput.next();

		System.out.println("ENTER PIN");
		String pin = userInput.next();

		// VALIDATE USER
		if (validateUser(userId, pin))
		{
			// GET ACCESS LEVEL OF CURRENT USER ID
			// PRESENT ADMIN OPTIONS BASED ON ACCESS LEVEL
		}
		else
		{
			System.out.println("ERROR: Unable to validate user!");
		}
	}// END adminConsole()

	private static void applicationSettings()
	{
		// CREATE INPUT OBJECT REFERENCE
		Scanner userInput = new Scanner(System.in);

		// TAKE USER INPUT
		System.out.println("ENTER USER ID");
		String userId = userInput.next();

		System.out.println("ENTER PIN");
		String pin = userInput.next();

		// VALIDATE USER
		if (validateUser(userId, pin))
		{
			// GET ACCESS LEVEL OF CURRENT USER ID
			// PRESENT APPLICATION	OPTIONS BASED ON ACCESS LEVEL
		}
		else
		{
			System.out.println("ERROR: Unable to validate user!");
		}
	}// END applicationSettings()
}// END Main{}