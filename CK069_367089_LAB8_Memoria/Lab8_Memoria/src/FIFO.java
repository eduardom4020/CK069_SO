
public class FIFO extends PageManager 
{
	public FIFO(String pages_reference, int buffer_capacity)
	{
		initialize(pages_reference, buffer_capacity);
	}

	@Override
	public void process() 
	{
		for(int i=0; i<pages.size(); i++)
		{
			if(i<buffer_capacity)
			{
				set(pages.get(i));
			}
			else if(!acess(pages.get(i)))
			{
				swap(pages.get(i));
			}
			
			acess(pages.get(i));
		}
	}
	
}
