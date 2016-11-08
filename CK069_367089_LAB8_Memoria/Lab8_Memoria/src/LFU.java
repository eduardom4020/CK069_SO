import java.util.ArrayList;

/*	LFU:
 * A mesma coisa do LRU, porem no lugar de "turnos" (tempo), faz a 
 * substituicao baseando-se nas paginas que foram menos referenciadas.
 * */

public class LFU extends PageManager
{
	ArrayList<Integer> last_reference = null;
	
	public LFU(String pages_reference, int buffer_capacity)
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
				Integer minor=0;
				
				for(int j=1; j<buffer_capacity; j++)
				{
					if(last_reference.get(minor) > last_reference.get(j))
					{
						minor = j;
					}
				}
				
				swap(page_buffer.get(minor), pages.get(i));
				last_reference.set(minor, 0);
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
