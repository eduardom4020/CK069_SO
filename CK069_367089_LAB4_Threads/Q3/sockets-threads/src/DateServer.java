/**
 * Time-of-day server listening to port 6013.
 *
 */
 
import java.net.*;
import java.io.*;

public class DateServer implements Runnable
{
	Socket client;
	
	public DateServer(Socket client) 
	{
		// TODO Auto-generated constructor stub
		this.client = client;
	}	
	
	public static void main(String[] args) throws IOException {
		ServerSocket sock = null;

		try 
		{
			sock = new ServerSocket(6013);
			// now listen for connections
			while (true) 
			{
				DateServer server = new DateServer(sock.accept());				
				Thread n_thread = new Thread(server);
				n_thread.start();
				
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

	@Override
	public void run() 
	{
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
