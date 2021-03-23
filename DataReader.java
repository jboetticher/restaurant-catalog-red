import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.DataFormatException;

// --== CS400 File Header Information ==--
// Name: <Kenneth Diao>
// Email: <kdiao2@wisc.edu>
// Team: <Red>
// Group: <KF>
// TA: <Keren Chen>
// Lecturer: <Gary Dahl>
// Notes to Grader: none

public class DataReader implements DataReaderInterface {
	
	// this is the header of the csv file, and represent the fields that will go into each Restaurant object
	private String header[] = new String[] {"Rank", "Restaurant", "Sales", "Average Check", "City", "State", "Meals Served"};
	
	/**
	 * This method reads the data from the csv file into strings and organizes them in such a way that they can be used to create RestaurantInterface objects
	 * @param inputFileReader is a Reader object that reads from the csv
	 * @return a list of RestaurantInterface objects based on the csv file inputted
	 * @throws IOException if there is a problem reading from the file or to a string
	 * @throws DataFormatException if data is not formatted properly (i.e. in this case usually if the data being read in don't match properly with the fields specified in header)
	 */
	@Override
	public List<RestaurantInterface> readDataSet(Reader inputFileReader) throws IOException, DataFormatException {
		return createRestaurantList(processData(inputFileReader));
	}
	
	/**
	 * This method is a helper for the readDataSet method. It reads the data from the file into a list of strings
	 * @param inputFileReader is the file reader passed from the readDataSet method
	 * @return a list of strings based on the data from the csv file
	 * @throws IOException if there is a problem reading from the file or to a string
	 */
	private List<String> readData(Reader inputFileReader) throws IOException{
		LinkedList<String> rawData = new LinkedList<String>();
		char nextChar;
		// test the reader
		try {
			nextChar = (char)inputFileReader.read();
		} catch (Exception e) {
			throw new IOException("Invalid input: " + e.getMessage());
		}
		String nextStr = "";
		boolean quotationLock = false;
		// skip past the header
		while(!isEndCharacter(nextChar)) {
			nextChar = (char) inputFileReader.read();
		}
		nextChar = (char) NextParsableCharacter(inputFileReader);
		while((int)nextChar != 65535 && (int)(nextChar) != -1) { // while nextChar is not null
			while(!isEndCharacter(nextChar)) {
				if (nextChar == '"') { // make sure commas within quotations aren't noted as delimiters
					quotationLock = !quotationLock;
				}
				else if(nextChar == ',' && !quotationLock) { // delimit by strings
					rawData.add(nextStr);
					nextStr = "";
				} else { // otherwise, continue building the string
					nextStr = nextStr + nextChar;
				}
				// read the next character
				nextChar = (char)inputFileReader.read();
			}
			// add the string, clear nextStr, and move past the end character (to the next line)
			rawData.add(nextStr);
			nextStr = "";
			nextChar = (char)NextParsableCharacter(inputFileReader);
		}
		// save resources once inputFileReader isn't needed
		inputFileReader.close();
		return rawData;
	}
	
	/**
	 * this method is a helper for the readDataSet method. It takes the list strings from the readData method and organizes the strings into lists of lists of strings
	 * @param inputFileReader is the reader from readDataSet
	 * @return a list of list of strings, where each sublist contains the list of strings under a specific header
	 * @throws IOException if there is a problem with reading from the csv or to a string
	 * @throws DataFormatException if data is not formatted properly (i.e. in this case usually if the data being read in don't match properly with the fields specified in header)
	 */
	private LinkedList<LinkedList<String>> processData(Reader inputFileReader) throws IOException, DataFormatException{
		LinkedList<LinkedList<String>> processedData = new LinkedList<LinkedList<String>>();
		// create a list of LinkedLists to organize the strings under different headers
		for (int i = 0; i < header.length; i++) {
			processedData.add(new LinkedList<String>());
		}
		List<String> rawData = readData(inputFileReader);
		// sort the strings into their relevant headers
		for(int i = 0; i < rawData.size(); i++) {
			try {
				processedData.get(i % header.length).add(rawData.get(i));
			} catch (Exception e) {
				throw new DataFormatException("Improper data format:" + e.getMessage());
			}
		}
		return processedData;
	}
	
	/**
	 * helper method to determine whether a character is an end character
	 * @param character is the current character
	 * @return true if character is an end character and false otherwise
	 * @throws IOException if there is a problem reading from the file
	 */
	private boolean isEndCharacter(char character) throws IOException {
		if(character == '\n' || character == '\r') { // if it's a newline or carriage return character
			return true;
		}
		return false;
	}
	
	/**
	 * helper method gets the next character from the reader that is not an end character
	 * @param reader is the same as readDataSet's reader
	 * @return the next non-end character in int format
	 * @throws IOException if there is a problem reading from the file
	 */
	private int NextParsableCharacter(Reader reader) throws IOException {
		int next = reader.read();
		
		// while it's not BS, NL, CR
		while(next == (int)'\r' || next == (int)'\n' || next == 10) {
			next = reader.read();
		}
		
		return next;
	}
	
	/**
	 * helper method to take the list of lists of strings 
	 * (which are organized so each sub-list is a list of the strings grouped by the header they're under)
	 *  and creates RestaurantInterface objects
	 * @param processedData is a list of lists of strings
	 * @return a list of RestaurantInterface objects
	 */
	private List<RestaurantInterface> createRestaurantList(LinkedList<LinkedList<String>> processedData) {
		LinkedList<RestaurantInterface> RestaurantList = new LinkedList<RestaurantInterface>();
		// create RestaurantInterfaces by taking strings from the processed data, one from each sublist
		for (int i = 0; i < processedData.get(0).size(); i++) {
			RestaurantList.add(new Restaurant(processedData.get(0).get(i), processedData.get(1).get(i), processedData.get(2).get(i), processedData.get(3).get(i), processedData.get(4).get(i), processedData.get(5).get(i), processedData.get(6).get(i)));
		}
		return RestaurantList;
	}

}
