import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Task implements Runnable
{
	private Socket client;
	
	public Task(Socket client)
	{
		this.client = client;
	}
	
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		try
		{
			System.out.println("client = " + client);
	
			// we have a connection
			PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
			// write the Date to the socket
			pout.println(new java.util.Date().toString());
	
			pout.close();
			client.close();
		}
		catch (IOException e) 
		{
			System.err.println(e);
		}
	}

}
