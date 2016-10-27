package DeadlockAlgorithms;

import java.util.ArrayList;

import ResourcesManaging.ResourcesManager;

public class BankerAlgorithm 
{
	private ResourcesManager rm = null;
	
	public BankerAlgorithm(String file_name)
	{
		rm = new ResourcesManager(file_name);
	}
	
	public boolean safety()
	{
		int process_qtd = rm.n;
		int resources_types = rm.m;
		
		for(int i=0; i<process_qtd; i++)
		{
			for(int j=0; j<resources_types; j++)
			{
				//para fins de debug
				
				System.out.println("avaliable at "+j+": "+rm.Avaliable.get(j));				
				System.out.println("need["+i+","+j+"]: "+rm.Need.get(i).get(j));
				
			//se o processo i precisa de mais instancias do recurso j que as disponiveis entao temos um deadlock (nao estah seguro)
				if(rm.Need.get(i).get(j) > rm.Avaliable.get(j))	
				{
					return false;
				}
			}
		}
		
		return true;
	}

//um processo solicita mais recursos. Se ao alocar esses recursos solicitados, ele ainda estiver em um estado seguro, a operacao eh aceita
//se nao, a operacao eh desfeita.
	public boolean avoid(String request)
	{
		rm.allocate(request);
		
		if(safety())
		{
			return true;
		}
		else
		{
			rm.block(request);
			
			return false;
		}
	}
	
//confere se estah em estado seguro. Faz a requisicao do need de p0. Se for possivel, repetimos o processo para p1.
//Se nao for possivel, bloqueamos p0 e tentamos em p1. Quando o need de um processo for zero, este eh terminado e adicionado a 
//lista de finish.
	public ArrayList<String> detection()
	{		
		for(int j=0; j<rm.n; j++)
		{
			String request = String.valueOf(j);
			
			for(int i=0; i<rm.m; i++)
			{
				request += " " + rm.Need.get(j).get(i);
			}
			
			
			if(avoid(request))
			{
				rm.finish(j);
			}
		}
		
		while(rm.RequestBlocked.size() > 0)
		{
			String request_blocked = rm.RequestBlocked.remove(0);
			
			if(avoid(request_blocked))
			{
				String[] tokens = request_blocked.split(" ");
				
				rm.finish(Integer.parseInt(tokens[0]));
			}
		}
		
		return rm.Finished;
	}
}
