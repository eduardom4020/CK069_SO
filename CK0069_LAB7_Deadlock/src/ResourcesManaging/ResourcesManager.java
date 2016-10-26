package ResourcesManaging;

import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import FileManaging.FileManager;

public class ResourcesManager extends FileManager
{
//os dois vetores abaixo representam os tipos de recursos e suas instancias. Cada posicao do vetor eh um tipo de recurso diferente
//e seu valor eh a quantidade de instancias
	public ArrayList<Integer> Avaliable = null;				//vetor que indica o quantas instâncias de recurso estao disponiveis
	public ArrayList<Integer> Instances = null;				//vetor que indica a quantidade de instancias por tipo de recurso
	
//as matrizes abaixo representam a relacao entre os processos e os recursos. Cada linha da matriz eh um processo e cada coluna eh
//um tipo de recurso
	public ArrayList<ArrayList<Integer>> Max = null;		//a capacidade maxima de instancias de tipos de recursos que um processo aguenta
	public ArrayList<ArrayList<Integer>> Allocation = null;	//a quantidade de instancias de recursos que estao sendo fornecidas para os processos
	public ArrayList<ArrayList<Integer>> Need = null;		//Need = Max - Allocation. O quanto ainda falta para liberar o processo.
	
	public ArrayList<String> RequestBlocked = null;
	
	public ArrayList<String> Finished = null;
	
	public int n;	//numero de processos
	public int m;	//numero de tipos de recursos
	
	private ArrayList<String> lines = null;
	private String actualLine = null;
	
	public ResourcesManager(String file_name)
	{
		Avaliable = new ArrayList<>();
		Max = new ArrayList<>();
		Allocation = new ArrayList<>();
		Need = new ArrayList<>();
		Instances = new ArrayList<>();
		RequestBlocked = new ArrayList<>();
		Finished = new ArrayList<>();
		
		int N=0;
		
		lines = readFile(file_name);
		
		for(int i=0; i<lines.size(); i++)
		{
			actualLine = lines.get(i);
			
		//a primeira linha do arquivo deve conter a quantidade de processos e de tipos de recursos respectivamente
			if(i==0)
			{
				String[] tokens = actualLine.split(" ");
				
				N = Integer.parseInt(tokens[0]);
				m = Integer.parseInt(tokens[1]);
			}
		//a quantidade de instancias para cada tipo de recurso. Os tipos de recurso devem ser iguais a m. Caso passe do valor 
		//estes serao ignorados, caso nao alcance ocorrerah falha de segmentacao.
			else if(i==1)
			{
				for(int j=0; j<m; j++)
				{
					String[] tokens = actualLine.split(" ");
					
					Instances.add(Integer.parseInt(tokens[j]));
				}
			}
			else
			{
				for(int j=0; j<N; j++)	//itera pela quantidade de processos
				{
					push_back(actualLine);
				}
				
				setAvaliable();
			}
		}
	}
	
//calculamos a quantidade de instancias disponiveis para um recurso
	private void setAvaliable()
	{
		for(int j=0; j<m; j++)	//itera pela quantidade de tipos de recusos
		{
			int sum=0;
			
			for(int k=0; k<n; k++)	//itera pela quantidade de processos
			{
				sum += Allocation.get(k).get(j);
			}
			
			Avaliable.add(Instances.get(j) - sum);
		}
	}
	
//adiciona um processo, com seus respectivos pedidos de recurso, ao gerente de recursos. No final da lista
	public void push_back(String new_process)
	{
		String[] tokens = new_process.split(" ");
		
		Allocation.add(new ArrayList<Integer>());
		Max.add(new ArrayList<Integer>());
		Need.add(new ArrayList<Integer>());
		
		n = Allocation.size();
		
		for(int i=0; i<m; i++)
		{
			Allocation.get(n-1).add(Integer.parseInt(tokens[i]));
			Max.get(n-1).add(Integer.parseInt(tokens[m+i]));
			
			Need.get(n-1).add(Max.get(n-1).get(i) - Allocation.get(n-1).get(i));
		}
		
		setAvaliable();
	}

//remove o ultimo processo da lista de pedidos por recurso
	public ArrayList<Integer> pop(int pid)
	{
		ArrayList<Integer> poped = new ArrayList<>();

		for(int i=0; i<m; i++)
		{
			poped.add(Allocation.get(pid).get(i));
			poped.add(Max.get(pid).get(m+i));
		}
		
		Allocation.remove(pid);
		Max.remove(pid);
		Need.remove(pid);
		
		n -= 1;
		
		setAvaliable();
		
		return poped;
	}
	
	public ArrayList<Integer> pop_back()
	{
		return pop(n-1);
	}

//um processo solicita mais recursos
	public void allocate(String request)	//string do tipo:	pid		r1	r2	r3	... (onde r sao as quantidades alocadas para os recursos em ordem)
	{
		String[] tokens = request.split(" ");
		
		int pid = Integer.parseInt(tokens[0]);
		
		for(int i=0; i<m; i++)
		{
			Allocation.get(pid).set(i, Allocation.get(pid).get(i) + Integer.parseInt(tokens[i+1]));
			Need.get(pid).set(i, Max.get(pid).get(i) - Allocation.get(pid).get(i));			
		}
	}
	
//remove a solicitacao feita
	public void deallocate(String request)
	{
		String[] tokens = request.split(" ");
		
		int pid = Integer.parseInt(tokens[0]);
		
		for(int i=0; i<m; i++)
		{
			Allocation.get(pid).set(i, Allocation.get(pid).get(i) - Integer.parseInt(tokens[i+1]));
			Need.get(pid).set(i, Max.get(pid).get(i) + Allocation.get(pid).get(i));			
		}
	}
	
//adiciona o pedido a lista de pedidos bloqueados. Consequentemente significando que o processo estah bloqueado
	public void block(String request)
	{
		deallocate(request);
		
		RequestBlocked.add(request);
	}
	
//finaliza um processo
	public void finish(int pid)
	{
		Finished.add("p"+pid);
		
		pop(pid);
	}
}
