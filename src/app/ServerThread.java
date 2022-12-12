package app;

import java.io.IOException;

public class ServerThread extends Thread
{
	private Server server;
	private InventoryManager manager;
	
	/**
	 * Constructor to set inventory manager, same as main app.
	 * 
	 * @param inventoryManager Is inventory manager from main app.
	 */
	ServerThread(InventoryManager<Salable> inventoryManager)
	{
		this.manager = inventoryManager;
	}
	
	/**
	 * Creates a server instance and starts it, once stopped it cleans itself up.
	 * 
	 * @Override run from Thread.
	 */
	public void run()
	{
		Server server = new Server(this.manager);
		try
		{
			server.start(6666);
			server.cleanUp();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
}
