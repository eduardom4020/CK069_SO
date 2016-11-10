package Q1_Semaphore;
import java.util.concurrent.Semaphore;


public class Dropbox 
{
	private int number;
	public boolean evenNumber = false;
	
	public Semaphore producer=null;
	public Semaphore even=null;
	public Semaphore odd=null;
	
	public Dropbox() throws InterruptedException
	{
		producer = new Semaphore(1);	
		even = new Semaphore(0);
		odd = new Semaphore(0);
	}
	
	public int take(final boolean even) throws InterruptedException 
	{
		if(evenNumber)
		{
			System.out.println("Consumidor PAR recebe "+number);
		}
		else
		{
			System.out.println("Consumidor IMPAR recebe "+number);
		}
		
		producer.release();
		
		return number;
	}
	
	public void put(int number) throws InterruptedException 
	{				
	
		this.number = number;
		evenNumber = number % 2 == 0;
		System.out.format("PRODUTOR gera %d.%n", number);
		
		if(evenNumber) 
		{
			even.release();
		}
		else
		{
			odd.release();
		}
	}
}
