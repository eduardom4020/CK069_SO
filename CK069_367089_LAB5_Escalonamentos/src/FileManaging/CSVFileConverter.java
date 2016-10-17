package FileManaging;
import java.io.IOException;
import java.util.ArrayList;


public class CSVFileConverter extends FileManager
{
	
	public class Process
	{
		public String id;
		public int waiting_time;
		public int burst_time;
		public int priority;
		
		public double processing_time;
		
		public Process(String process_string)
		{
			String[] tokens = process_string.split(" ");
			
			waiting_time = Integer.parseInt(tokens[0]);
			id = tokens[1];
			burst_time = Integer.parseInt(tokens[2]);
			priority = Integer.parseInt(tokens[3]);
		}
	}
	
	private ArrayList<String> lines;
	
	public CSVFileConverter() {};
	
	public CSVFileConverter(String file_name)
	{
		this.open(file_name);
	}
	
	public Process getProcess(int pos)
	{
		return new Process(this.lines.get(pos));
	}
	
	public ArrayList<Process> getListOfProcess()
	{
		ArrayList<Process> process_list = new ArrayList<>();
		
		for(int i=0; i<lines.size(); i++)
		{
			process_list.add(getProcess(i));
		}
		
		return process_list;
	}
	
	public void open(String file_name)
	{
		lines = new ArrayList<>();
		
		try 
		{
			lines = readFile(file_name+".csv");
			
		} catch (Exception e) {	e.printStackTrace();}
	}
	
	public void save(ArrayList<Process> process_list, String file_name)
	{
		ArrayList<String> lines_out = new ArrayList<>();
		
		for(int i=0; i<process_list.size(); i++)
		{
			lines_out.add(process_list.get(i).id + "    " + process_list.get(i).processing_time);
		}
		
		try
		{
			writeFile(file_name, lines_out);
			
		} catch (IOException e) {e.printStackTrace();}
	}
}
