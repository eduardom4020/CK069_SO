
public class Main {

	public static void main(String[] args) 
	{
		PageManager algorithm = null;
		
		switch (args[0].toUpperCase()) 
		{
		case "OTIMO":
			
			algorithm = new Optimal(args[1], Integer.parseInt(args[2]));
			
			break;
			
		case "FIFO":
			
			algorithm = new FIFO(args[1], Integer.parseInt(args[2]));
			
			break;
			
		case "LRU":
			
			algorithm = new LRU(args[1], Integer.parseInt(args[2]));
			
			break;
			
		case "LFU":
			
			algorithm = new LFU(args[1], Integer.parseInt(args[2]));
			
			break;
			
		case "MFU":
			
			algorithm = new MFU(args[1], Integer.parseInt(args[2]));
			
			break;

		default:
			break;
		}
		
		algorithm.process();
		ResultSet result = algorithm.resultSet();
		
		System.out.print("Tamanho do buffer: ");
		System.out.println(result.buffer_capacity);
		System.out.print("Histrico de alocacoes: ");
		System.out.println(result.buffer_history);
		System.out.print("Razao entre os tempos: ");
		System.out.println(result.time_product);
		System.out.print("Numero de falhas: ");
		System.out.println(result.pageFault);
	}

}
