/*	A substitui��o de p�gina �tima:
 * Quando precisamos acessar uma p�gina que n�o est� no buffer, precisamos
 * realizar a troca. Sua estrategia se baseia em conferir qual das paginas 
 * no buffer vai demorar mais a aparecer novamente, baseada em sua posicao
 * na string de paginas chamadas, e assim substituimos a pagina pela pagina
 * atualmente requisitada.
 * */

public class Optimal extends PageManager
{
	public Optimal(String pages_reference, int buffer_capacity)
	{
		initialize(pages_reference, buffer_capacity);
	}
	
//define onde ocorre o primeiro caractere na lista de paginas a partir de uma posicao, quantos caracteres
//foram percorridos
	private Integer firstOf(int id, int pos)
	{
		Integer output=0;
		
		for(int i=pos; i<pages.size(); i++)
		{
			output++;
			
			if(pages.get(i) == id)
				break;
		}
		
		return output;
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
				Integer mostDistant = 0;
				
				for(int j=1; j<buffer_capacity; j++)
				{
					if(firstOf(page_buffer.get(mostDistant), i) < firstOf(page_buffer.get(j), i))
					{
						mostDistant = j;
					}
				}
				
				swap(page_buffer.get(mostDistant), pages.get(i));
			}
			
			acess(pages.get(i));
		}
	}
}
