package Q2;

import java.util.ArrayList;

public abstract class Process 
{
	public ArrayList<Resource> allocate;
	
	public void blockResource(String resID)
	{
		int i=0;
		boolean found=false;
		
		while((i<allocate.size()) && (!found))
		{
			if(allocate.get(i).id == resID)
			{
				allocate.get(i).blocked = true;
				found=true;
			}
			
			i++;
		}
	}
	
	
}
