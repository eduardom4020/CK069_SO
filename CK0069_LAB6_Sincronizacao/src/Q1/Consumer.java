package Q1;
import java.util.Random;

public class Consumer implements Runnable 
{
	private final Dropbox dropbox;
	private final boolean even;
	
	public Consumer(boolean even, Dropbox dropbox) 
	{
		this.even = even;
		this.dropbox = dropbox;
	}
	
	public void run() 
	{
		Random random = new Random();
		
		while (true) 
		{
			try 
			{
				if(dropbox.even.tryAcquire())
				{				
					dropbox.take(even);
				}
				else if(dropbox.odd.tryAcquire())
				{					
					dropbox.take(even);
				}	
				
				
			} catch (InterruptedException e1) {	e1.printStackTrace();}
			
			try 
			{
				Thread.sleep(random.nextInt(100));
			} catch (InterruptedException e) { }
		}
	}
}	