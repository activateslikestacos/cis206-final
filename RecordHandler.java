import java.util.ArrayList;

/**
 * Used for storing all the records from the user
 * and files etc.
 * @author chris
 */
public class RecordHandler {

	private ArrayList<Record> records;
	
	public RecordHandler() {
		records = new ArrayList<Record>();
	}
	
	// Used for listing the record names and their IDs
	public void listRecords() {
		
		System.out.println("List of all records loaded");
		System.out.println("--------------------------------------");
		
		Record temp;
		
		for (int i = 0; i < records.size(); i++) {
			
			temp = records.get(i);
			System.out.println("[" + (i + 1) + "] " + temp.getName());
			
		}
		
	}

	// Used to tell if something exists
	public boolean exists(int i) {
		
		return (i <= records.size() && i > 0);
		
	}
	
	// Used for getting a specific record by ID
	public Record viewRecord(int i) {
		
		return records.get(i - 1);
		
	}
	
	// Fetches the amount of records
	public int amount() {
		return records.size();
	}
	
	// Used to add an entry to the records
	public void add(Record r) {
		records.add(r);
	}
	
	// Used to remove an entry from the records
	public void remove(int i) {
		records.remove(i);
	}
	
	// Retrieves an array of records for easy file writing
	public Record[] getRecords() {
		return records.toArray(new Record[records.size()]);
	}
	
}
