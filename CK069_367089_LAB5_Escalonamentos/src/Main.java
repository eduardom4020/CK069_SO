import ProcessScheduling.Fifo;
import ProcessScheduling.ProcessScheduler;
import ProcessScheduling.SJF;

public class Main {

	public static void main(String[] args) 
	{
		ProcessScheduler ps=null;
		
		switch (args[1]) 
		{
		case "fifo":
			
			ps = new Fifo(args[0]);
			
			break;
			
		case "sjf":
			
			ps = new SJF(args[0]);
			
			break;

		default:
			break;
		}
		
		ps.printStatistics();

	}

}
