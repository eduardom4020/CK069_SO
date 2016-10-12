/**
 * Time-of-day server listening to port 6013.
 *
 */
 
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

public class DateServer
{
	private static Socket client;
	
	public DateServer(Socket client) 
	{
		// TODO Auto-generated constructor stub
		DateServer.setClient(client);
	}	
	
	public static void main(String[] args) throws IOException 
	{
		ServerSocket sock = null;
		ExecutorService pool = null;
		int numOfTasks=1;

		if(args[0].compareTo("-single") == 0)
		{
			pool = Executors.newSingleThreadExecutor();
		}
		else if (args[0].compareTo("-fixed") == 0)
		{
			numOfTasks = Integer.parseInt(args[1]);
			pool = Executors.newFixedThreadPool(numOfTasks);
		}
		else if (args[0].compareTo("-cached") == 0)
		{
			numOfTasks = Integer.parseInt(args[1]);
			pool = Executors.newCachedThreadPool();
		}
		
		
		try 
		{
			sock = new ServerSocket(6013);
			// now listen for connections
			while (true) 
			{
				DateServer server = new DateServer(sock.accept());	
				
				for(int i=0; i<numOfTasks; i++)
				{
					pool.execute(new Task(getClient()));
				}
				pool.shutdown();
				
				System.out.println("server = " + sock);
				
			}
		}
		catch (IOException ioe) {
				System.err.println(ioe);
		}
		finally {
			if (sock != null)
				sock.close();
		}
	}

	public static Socket getClient() {
		return client;
	}

	public static void setClient(Socket client) {
		DateServer.client = client;
	}
}
