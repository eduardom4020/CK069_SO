package Q1_Monitor;
import java.util.concurrent.Semaphore;

public class Dropbox 
{
	private int number;
	public boolean evenNumber = false;
	public boolean lock = false;
	
	public Dropbox() throws InterruptedException{}
	
	public synchronized int take(final boolean even) throws InterruptedException 
	{
		while(!lock)
		{
			wait();
		}
		
		if(evenNumber)
		{
			System.out.println("Consumidor PAR recebe "+number);
		}
		else
		{
			System.out.println("Consumidor IMPAR recebe "+number);
		}
		
		lock = false;
		notifyAll();
		
		return number;
	}
	
	public synchronized void put(int number) throws InterruptedException 
	{	
		while(lock)
		{
			wait();
		}
		
		this.number = number;
		evenNumber = number % 2 == 0;
		lock = true;
		System.out.format("PRODUTOR gera %d.%n", number);
		notifyAll();
	}
}
