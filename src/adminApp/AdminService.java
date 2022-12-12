package adminApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AdminService
{
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	/**
	 * Creates the PrintWriter from the client socket and a buffered reader to read from and write to server
	 * 
	 * @param ip The ip to create the socket on (127.0.0.1) localhost
	 * @param port The port number to run the socket on (6666)
	 * @throws IOException If there is an issue with input or output
	 */
	public void start(String ip, int port) throws IOException
	{
		clientSocket = new Socket(ip, port);
		
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}
	
	/**
	 * Send a message to the server.
	 * 
	 * @param msg Message to send.
	 * @return Response from the server.
	 * @throws IOException Thrown if anything bad happens from the networking classes.
	 */
	public String sendMessage(String msg) throws IOException
	{
		out.println(msg);
		
		return in.readLine();
	}
	
	/**
	 * Takes in a json string and adds the U command and sends it to the server to update inventory.
	 * 
	 * @param json Inventory json to send to server.
	 * @return The response from the server.
	 * @throws IOException If there is anything wrong with networking classes.
	 */
	public String sendInventory(String json) throws IOException
	{
		// add "U|" to front of json then send
		out.println("U-" + json);
		
		return in.readLine();
	}
	
	/**
	 * Sends command 'R' to server to get the current inventory and return it.
	 * 
	 * @return The response from the server.
	 * @throws IOException If there is anything wrong with networking classes.
	 */
	public String readInventory() throws IOException
	{
		// send "R" and return the response
		out.println("R");
		return in.readLine();
	}
	
	/**
	 * Closes all of the readers and sockets
	 * 
	 * @throws IOException If there is a problem closing any of the readers
	 */
	public void cleanup() throws IOException
	{
		in.close();
		out.close();
		clientSocket.close();
	}
}
