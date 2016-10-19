package ProcessScheduling;

public class Fifo extends ProcessScheduler 
{
	@Override
	protected String getAlgorithmName() {
		// TODO Auto-generated method stub
		return "FirFirst-Come, First-Served";
	}

	public Fifo(String file_name)
	{
		getProcessFromFile(file_name);
		
		process_list.get(0).waiting_time = 0;
		
		for(int i=1; i<process_list.size(); i++)
		{
			process_list.get(i).waiting_time = process_list.get(i-1).waiting_time + process_list.get(i-1).burst_time;
		}
	}
}
