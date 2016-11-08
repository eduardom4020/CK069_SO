import java.util.ArrayList;

/*	LRU:
 * Guardamos um vetor do tamanho do buffer que armazena a quantas
 * "rodadas" cada pagina do buffer se encontra no mesmo. Quando
 * uma troca for necessaria, trocamos a pagina com maior valor
 * no vetor de ultimo uso.
 * */

public class LRU extends PageManager
{
	ArrayList<Integer> last_use = null;
	
	public LRU(String pages_reference, int buffer_capacity)
	{
		initialize(pages_reference, buffer_capacity);
		
		last_use = new ArrayList<>();
		
		for(int i=0; i<buffer_capacity; i++)
		{
			last_use.add(0);
		}
	}
	
	private void nextTurn()
	{
		for(int i=0; i<buffer_capacity; i++)
		{
			last_use.set(i, last_use.get(i)+1);
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
					if(last_use.get(major) < last_use.get(j))
					{
						major = j;
					}
				}
				
				swap(page_buffer.get(major), pages.get(i));
				last_use.set(major, 0);
			}
			
			acess(pages.get(i));
			nextTurn();
		}
	}
	
}
