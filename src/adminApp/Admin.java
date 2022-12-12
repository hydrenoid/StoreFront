package adminApp;

import java.io.File;

import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import adminApp.FileService;
import adminApp.Salable;

public class Admin
{
	private AdminService adminService;
	
	/**
	 * Create an admin server and connect it to localhost:6666 and begin asking admin user what they want to do
	 * 
	 * @param args
	 * @throws IOException When issue with reading or writing to buffer
	 * @throws InterruptedException When issue with sleeping thread
	 */
	public static void main(String[] args)
	{
		Admin admin = new Admin();
		
		admin.adminService = new AdminService();
		
		try {
			admin.adminService.start("127.0.0.1", 6666);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String response;
		String message;
		
		// Open a reader for user input
		Scanner read = new Scanner(System.in);
		
		// initialize the file service
		FileService<Salable> fileService = new FileService<Salable>();	
		
		while(true)
		{	
			// ask admin what they want to do (either send data to update inventory or get the inventory and read it)
			System.out.println("Enter 1 to send inventory, enter 2 to read inventory, enter 3 to end");
			int num1 = read.nextInt();
			
			// if input is 1 then send json inventory of salable to replace main apps inventory
			if(num1 == 1)
			{	
				try {
					String json = fileService.readInventoryJson();
					response = admin.adminService.sendInventory(json);
					System.out.println("Server response was " + response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			// if input is 2 then send a call to receive the inventory as json then convert to ArrayList of Salable
			else if(num1 == 2)
			{
				try
				{
					response = admin.adminService.readInventory();
					
					System.out.println("Response Json: " + response);
					
					Salable[] inv = null;
					
					// turn response json into array of salables
					ObjectMapper objectMapper = new ObjectMapper();
					try
					{
						inv = objectMapper.readValue(response, Salable[].class);
						
						System.out.println("----------STOCK----------");
						for(int i = 0; i < inv.length; i++)
						{
							System.out.println(inv[i].toString());
							System.out.println("-----------------------------------------------------");
						}
						
						// save to json file
						fileService.saveToFile("admin_inventory.json", inv);
					} catch(JsonProcessingException e)
					{
						e.printStackTrace();
					}
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			// if they entered 3 then shut down the admin application
			else if(num1 == 3)
			{
				break;
			}
			else
			{
				System.out.println("Please enter 1-3");
			}
		}
		try
		{
			admin.adminService.cleanup();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}


