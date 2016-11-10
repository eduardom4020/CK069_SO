package Q2;

import java.util.ArrayList;

public abstract class Process 
{
	protected ArrayList<Resource> allocate = null;
	
	protected Integer findID(String resID)
	{
		for(int i=0; i<allocate.size(); i++)
		{
			if(allocate.get(i).id == resID)
			{
				return i;
			}
		}
		
		return -1;
	}
	
	protected boolean acess(Resource res)
	{
		if(!res.blocked)
		{
			if(allocate.size()==0)
			{
				allocate.add(res);
			}
			else
			{			
				if(findID(res.id)<0)
					allocate.add(res);
			}
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	protected void blockResource(String resID)
	{
		Integer i = findID(resID);
		
		if(i>=0)
			allocate.get(i).blocked = true;
	}
	
	protected void freeResource(String resID)
	{
		Integer i = findID(resID);
		
		if(i>=0)
			allocate.get(i).blocked = false;
	}
}
