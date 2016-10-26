import java.util.ArrayList;

import DeadlockAlgorithms.BankerAlgorithm;


public class Main 
{
	private static BankerAlgorithm bm = null;
	
	public static void main(String[] args) 
	{
		bm = new BankerAlgorithm(args[1]);
		
		switch (args[0].toUpperCase()) 
		{
		case "SAFETY":
			
			if(bm.safety())
			{
				System.out.println("Nao ocorreu deadlock para os processos no arquivo "+args[1]);
			}
			else
			{
				System.out.println("Ocorreu deadlock para os processos no arquivo "+args[1]);
			}
			
			break;
			
		case "AVOID":
			
			String request=null;
			
			for(int i=2; i<args.length; i++)
			{
				request += args[i]+" ";
			}
			
			if(bm.avoid(request))
			{
				System.out.println("Nao ocorreu deadlock para os processos no arquivo "+args[1]+" ao adicionarmos a seguinte requisicao: "+request);
			}
			else
			{
				System.out.println("Ocorreu deadlock para os processos no arquivo "+args[1]+" ao adicionarmos a seguinte requisicao: "+request);
			}
			
			break;
			
		case "DETECT":
			
			ArrayList<String> finished = bm.detection();
			
			System.out.println("Sequencia de finalizacao dos processos para que nao ocorra deadlock: ");
			
			for(int i=0; i<finished.size(); i++)
			{				
				System.out.print(finished.get(i) + " ");
			}
			
			break;

		default:
			
			System.out.println("Comandos de entrada:\n" +
					"safety <nome do arquivo>\n" +
					"avoid <nome do arquivo> p ar1 ar2 ar3 ...(p = numero do processo; a = alocacao de recurso;)\n" +
					"detect <nome do arquivo>");
			
			break;
		}
	}

}
