package app;

import java.io.File;
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
	 * Read the json from the inventory file and return an arrayList of Salables from it.
	 * 
	 * @param filename The name of the file containing the inventory json.
	 * @return Return an ArrayList of salables containing the inventory.
	 * @throws IOException Thrown when trouble reading json file
	 */
	public ArrayList<E> readInventoryJson(String filename) throws IOException
	{
		Salable[] inventory = null;
		
		// Read a string of JSON from a file and convert to an array of Cars (could have used ArrayList as well)
		ObjectMapper objectMapper = new ObjectMapper();
		inventory = objectMapper.readValue(new File(filename), Salable[].class);
		
		ArrayList<E> stock = new ArrayList<E>();
		for(int i = 0; i < inventory.length; i++)
		{
			stock.add((E) inventory[i]);
		}
		
		return stock;
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
