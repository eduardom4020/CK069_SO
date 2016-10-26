package ProcessScheduling;
import java.util.Collections;
import java.util.Comparator;

import FileManaging.CSVFileConverter.Process;

public class SJF extends ProcessScheduler
{
	@Override
	protected String getAlgorithmName() {
		// TODO Auto-generated method stub
		return "Shortest Job First";
	}

	public SJF(String file_name)
	{
		getProcessFromFile(file_name);
		
		Collections.sort(process_list, new Comparator<Process>() {

			@Override
			public int compare(Process o1, Process o2) 
			{
				if(o1.arrival_time == o2.arrival_time)
				{
					return o1.burst_time - o2.burst_time;
				}
				else
				{
					return o1.arrival_time - o2.arrival_time;
				}
			}
		});
		
		int process_qtd = process_list.size();
		
		process_list.get(0).processing_time = process_list.get(0).arrival_time + process_list.get(0).burst_time;	
		process_list.get(0).response_time = process_list.get(0).processing_time - process_list.get(0).burst_time;
		process_list.get(0).waiting_time = process_list.get(0).response_time - process_list.get(0).arrival_time;
		process_list.get(0).turnaround = process_list.get(0).waiting_time + process_list.get(0).burst_time;
		
		for(int i=1; i<process_qtd; i++)
		{
			process_list.get(i).processing_time = process_list.get(i-1).processing_time + process_list.get(i).burst_time;
			process_list.get(i).response_time = process_list.get(i).processing_time - process_list.get(i).burst_time;
			process_list.get(0).waiting_time = process_list.get(i).response_time - process_list.get(i).arrival_time;
			process_list.get(i).turnaround = process_list.get(i).waiting_time + process_list.get(i).burst_time;	
		}
	}
}
