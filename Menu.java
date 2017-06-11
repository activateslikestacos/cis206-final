import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Used for working with the user and manages
 * input and output
 * @author chris
 *
 */
public class Menu {

	private Scanner in;
	private RecordHandler records;
	
	public Menu() {
		this.in = new Scanner(System.in);
		this.records = new RecordHandler();
	}
	
	// Used for printing out the menu to the user to show them
	// their options
	private void printMenu() {
		
		System.out.println("Please choose an option:");
		System.out.println("1. List all records");
		System.out.println("2. View record via ID");
		System.out.println("3. Add a record");
		System.out.println("4. Remove a record via ID");
		System.out.println("5. Save records to a file");
		System.out.println("6. Load records from a file");
		System.out.println("7. Exit the program");
		
	}
	
	// Starts a loop that keeps the user in a menu
	public void startMenu() {
		
		// Keep looping until the program is ended
		while (true) {
			
			// Print out the menu
			printMenu();
			
			// handle user input
			handleInput();
			
		}
		
	}
	
	private void handleInput() {
		
		// Get the user's selected number
        	int selection = 0;
        	try {
            		selection = in.nextInt();
        	} catch (InputMismatchException e) {
            		in.nextLine();
            		System.out.println("\n\nInvalid Input\n\n");
            		return;
       	 	}
		
		// Get rid of the left behind \n character
		in.nextLine();
		
		switch (selection) {
		
		case 1:
			// List all records
			this.listRecords();
			break;
		case 2:
			// View a record
			this.viewSpecificRecord();
			break;
		case 3:
			// Add a record
			this.addRecord();
			break;
		case 4:
			// Remove a record
			this.removeRecord();
			break;
		case 5:
			// Save to file
			this.saveToFile();
			break;
		case 6:
			// Load from a file
			this.loadFromFile();
			break;
		case 7:
			// Exit the program
			System.exit(0);
			break;
		default:
			System.out.println("Invalid input! :(");
			break;
		}
		
	}
	
	private void listRecords() {
		records.listRecords();
	}
	
	private void viewSpecificRecord() {
		
		// Prompt the user for the record ID
		System.out.print("Please enter the record ID: ");
		// Get the user's selected number
        	int id = 0;
        	try {
            		id = in.nextInt();
        	} catch (InputMismatchException e) {
            		in.nextLine();
            		System.out.println("\n\nPlease enter an ID only\n\n");
            		return;
       	 	}
		
		// Get rid of the left behind \n character
		in.nextLine();
		
		if (!records.exists(id)) {
			System.out.println("\nThe specified record ID doesn't exist!\n");
			return;
		}
		
		Record temp = records.viewRecord(id);
		
		System.out.println(temp.getName() + "'s Record");
		System.out.println("-------------------------------------");
		System.out.println("Birthday: " + temp.getBirthday());
		System.out.println("Website: " + temp.getWebsite());
		System.out.println("Email: " + temp.getEmail());
		System.out.println("Address: " + temp.getAddress());
		System.out.println("-------------------------------------\n");
		
	}
	
	private void addRecord() {
		
		String name, address, website, birthday, email;
		
		// Prompting all data from the user for the record
		System.out.println("Adding a record:");
		System.out.println("-------------------------");
		
		System.out.print("Name: ");
		name = in.nextLine();
		
		System.out.print("Address: ");
		address = in.nextLine();
		
		System.out.print("Website: ");
		website = in.nextLine();
		
		System.out.print("Birthday: ");
		birthday = in.nextLine();
		
		System.out.print("Email: ");
		email = in.nextLine();
	
		// Add the record to the file
		records.add(new Record(name, address, website, email, birthday));
		
	}
	
	private void removeRecord() {
		
		// Prompt the user for the record ID
		System.out.print("Please enter the record ID: ");
		// Get the user's selected number
        	int id = 0;
        	try {
            		id = in.nextInt();
        	} catch (InputMismatchException e) {
            		in.nextLine();
            		System.out.println("\n\nPlease enter an ID only\n\n");
            		return;
       	 	}
		
		// Get rid of the left behind \n character
		in.nextLine();
		
		// Make sure the record exists
		if (!records.exists(id)) {
			System.out.println("The specified record doesn't exist!");
			return;
		}
		
		// Delete the record by ID
		records.remove(id);
		
		System.out.println("The record at ID: " + id + "has been deleted");
		
	}
	
	private void saveToFile() {
		
		// Prompt user for file location
		System.out.println("Please enter the save path:");
		String filePath = in.nextLine();
		
		try {
			
			// Create the file object
			File file = new File(filePath);
			
			// Check to see if the file exists
			if (!file.exists())
				file.createNewFile();
			
			// Get the object to write to the file
			Record[] data = records.getRecords();
			
			// Building the objects to write to the file
			FileOutputStream fout = new FileOutputStream(file);
			ObjectOutputStream objectOut = new ObjectOutputStream(fout);
			
			// Write the object to the file
			objectOut.writeObject(data);
			objectOut.flush();
			
			// Close up
			objectOut.close();
			
		} catch (IOException e) {
			System.out.println("There was an error writing to the file: " + e.getMessage());
		}
		
	}
	
	private void loadFromFile() {
		
		// Prompt user for file location
		System.out.println("Please enter the save path:");
		String filePath = in.nextLine();
		try {

			// Create the file object
			File file = new File(filePath);

			// Check to see if the file exists
			if (!file.exists())
				file.createNewFile();

			// Building the objects to write to the file
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream objectIn = new ObjectInputStream(fin);

			// Get the object from the file
			Record[] data = (Record[]) objectIn.readObject();

			// Loop through and add the data to the records
			for (Record r : data) {
				records.add(r);
			}
			
			System.out.println("All the records from the file have been loaded!\n");
			
			// Close up
			objectIn.close();

		} catch (IOException | ClassNotFoundException e) {
			System.out.println("There was an error reading the file: " + e.getMessage());
		}

	}

}
