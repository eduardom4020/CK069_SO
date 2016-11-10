package Q2_Semaphore;

public class Resource 
{
	public String id;
	public boolean blocked;
	
	public Resource(String id, boolean blocked) 
	{
		this.id = id;
		this.blocked = blocked;
	}
}
