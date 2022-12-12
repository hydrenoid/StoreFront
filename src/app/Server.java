package app;
import java.net.*;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;


public class Server {

	private InventoryManager inventoryManager;
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	/**
	 * Instantiates the manager, same manager as the main app.
	 * 
	 * @param manager The manager from main application.
	 */
	Server(InventoryManager<Salable> manager)
	{
		this.inventoryManager = manager;
	}
	
	/**
	 * Waits for a client connection then prints all incoming messages until receives "." to shut down.
	 * 
	 * @param port Is the port to run the Server connection on.
	 * @throws IOException Is thrown if there is an input or output error.
	 */
	public void start(int port) throws IOException
	{
		// wait for client connection
		System.out.println("Waiting for a Client connection.....");
		serverSocket = new ServerSocket(port);
		clientSocket = serverSocket.accept();
		
		// create writer and buffer for I/O
		System.out.println("Received a CLient connection on port " + clientSocket.getLocalPort());
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		String inputLine;
		while((inputLine = in.readLine()) != null)
		{
			// parse from '-' delimiter
			System.out.println("received message was: " + inputLine);

			String[] parsedStrings = inputLine.split("-");
			
			if(".".equals(parsedStrings[0]))
			{
				System.out.println("Got a message to shut the Server down");
				out.println("QUIT");
				break;
			}
			else if("U".equals(parsedStrings[0]))
			{
				// update store inventory from json string
				String jsonString = parsedStrings[1];
				
				Salable[] inventory = null;
				try
				{
					// Read a string of JSON from a file and convert to an array of Cars (could have used ArrayList as well)
					ObjectMapper objectMapper = new ObjectMapper();
					inventory = objectMapper.readValue(jsonString, Salable[].class);
				}
				catch (IOException e)
				{
					// Print exception
					e.printStackTrace();
					
				}
				
				ArrayList<Salable> stock = new ArrayList<Salable>();
				for(int i = 0; i < inventory.length; i++)
				{
					stock.add((Salable) inventory[i]);
				}
				
				// update inventory with new inventory
				this.inventoryManager.setInventory(stock);
				
				out.println("Updated Successfully");
			}
			else if("R".equals(parsedStrings[0]))
			{
				// turn current inventory into json and send the json as the response
				Salable[] inv = inventoryManager.getInventory();
				
				ObjectMapper objectMapper = new ObjectMapper();
				String json = objectMapper.writeValueAsString(inv);
				
				out.println(json);
			}
		}
		System.out.println("Server is shutting down");
	}
	
	/**
	 * Closes all of the readers and sockets.
	 * 
	 * @throws IOException If there is a problem closing any of the readers.
	 */
	public void cleanUp() throws IOException
	{
		in.close();
		out.close();
		clientSocket.close();
		serverSocket.close();
	}
}
