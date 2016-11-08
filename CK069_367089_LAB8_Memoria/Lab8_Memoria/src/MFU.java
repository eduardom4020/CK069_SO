import java.util.ArrayList;

/*	MFU:
 * Analogo ao LFU, porem para o valor maior
 * */

public class MFU extends PageManager
{
	ArrayList<Integer> last_reference = null;
	
	public MFU(String pages_reference, int buffer_capacity)
	{
		initialize(pages_reference, buffer_capacity);
		
		last_reference = new ArrayList<>();
		
		for(int i=0; i<buffer_capacity; i++)
		{
			last_reference.add(0);
		}
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
				Integer major=0;
				
				for(int j=1; j<buffer_capacity; j++)
				{
					if(last_reference.get(major) < last_reference.get(j))
					{
						major = j;
					}
				}
				
				swap(page_buffer.get(major), pages.get(i));
				last_reference.set(major, 0);
			}
			else
			{
				for(int j=0; j<buffer_capacity; j++)
				{
					if(page_buffer.get(j) == pages.get(i))
					{
						last_reference.set(j, last_reference.get(j)+1);
					}
				}
			}
			
			acess(pages.get(i));
		}
	}
	
}
