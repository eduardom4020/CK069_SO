
public class Main {

	public static void main(String[] args) 
	{
		PageManager algorithm = null;
		
		switch (args[0].toUpperCase()) 
		{
		case "OTIMO":
			
			algorithm = new Optimal(args[1], Integer.parseInt(args[2]));
			
			break;

		default:
			break;
		}
		
		ResultSet result = algorithm.resultSet();
		
		System.out.print("Tamanho do buffer: ");
		System.out.println(result.buffer_capacity);
		System.out.println("Histórico de alocações: ");
		System.out.println(result.buffer_history);
		System.out.print("Razao entre os tempos: ");
		System.out.println(result.time_product);
		System.out.println("Número de falhas: ");
		System.out.println(result.pageFault);
	}

}
