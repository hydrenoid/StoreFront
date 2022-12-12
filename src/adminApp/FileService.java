package adminApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;

public class FileService<E extends Salable>
{
	/**
	 * Read the json from the inventory file and return the string of it.
	 * 
	 * @return Return the json string from file.
	 * @throws IOException Thrown when trouble reading json file
	 */
	public String readInventoryJson() throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader("admin_inventory.json"));
		
		String json = reader.readLine();
		
		reader.close();
		
		return json;
	}
	
	/**
	 * Take a filename and an array of Salables and turn into a json file.
	 * 
	 * @param filename The name of the file to place the json into.
	 * @param inventory The array of Salables to place into the Json.
	 * @throws IOException Thrown when trouble reading json file
	 */
	public void saveToFile(String filename, E[] inventory) throws IOException
	{
		PrintWriter pw;
	
		// Create a file File to write
		File file = new File(filename);
		FileWriter fw = new FileWriter(file);
		pw = new PrintWriter(fw);
		
		// Convert Cars array to JSON (could have used ArrayList as well)
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(inventory);

		// Write Car as JSON
		pw.println(json);
		
		// Cleanup
		pw.close();
		
	}
}
