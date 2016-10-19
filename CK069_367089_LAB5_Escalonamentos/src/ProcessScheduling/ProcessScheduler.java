package ProcessScheduling;

import java.util.ArrayList;
import FileManaging.*;

public abstract class ProcessScheduler 
{
	protected ArrayList<CSVFileConverter.Process> process_list;
	
	protected void getProcessFromFile(String file_name)
	{
		CSVFileConverter file_converter = new CSVFileConverter(file_name);
		
		process_list = file_converter.getListOfProcess();
	}
	
	protected abstract String getAlgorithmName();
	
	protected double getTotalProcessingTime()
	{
		double sum=0;
		
		for(int i=0; i<process_list.size(); i++)
		{
			sum += process_list.get(i).processing_time;
		}
		
		return sum;
	}
	
//	protected double getCPUPercentual()
//	{
//		return 0; //comofaz?
//	}
//	
//	protected abstract double getTroughputAvg();
//	
//	protected double getTurnaroundAvg()	//media da criacao ateh o termino
//	{
//		double sum=0;
//		
//		for(int i=0; i<process_list.size(); i++)
//		{
//			sum += process_list.get(i).burst_time;
//		}
//		
//		return (double)sum/process_list.size();
//	}
	
//	protected double getWaitingTimeAvg()
//	{
//		double sum=0;
//		
//		for(int i=0; i<process_list.size(); i++)
//		{
//			sum += process_list.get(i).waiting_time;
//		}
//		
//		return (double)sum/process_list.size();
//	}
	
//	protected abstract double getResponseTimeAvg();
	
	//TODO: terminar estatisticas e save
	
	public void printStatistics()
	{
		
	}
	
	public void saveProcessList()
	{
		
	}
}
