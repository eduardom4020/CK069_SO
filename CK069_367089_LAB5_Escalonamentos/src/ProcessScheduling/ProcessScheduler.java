package ProcessScheduling;

import java.util.ArrayList;
import FileManaging.*;

public abstract class ProcessScheduler 
{
	private static ArrayList<CSVFileConverter.Process> process_list;
	
	private void getProcessFromFile(String file_name)
	{
		CSVFileConverter file_converter = new CSVFileConverter(file_name);
		
		process_list = file_converter.getListOfProcess();
	}
	
	public void printStatistics()
	{
		
	}
	
	public void saveProcessList()
	{
		
	}
}
