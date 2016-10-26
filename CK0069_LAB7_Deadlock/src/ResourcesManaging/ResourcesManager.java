package ResourcesManaging;

import java.util.ArrayList;

import FileManaging.FileManager;

public class ResourcesManager extends FileManager
{
	
	public ArrayList<Integer> Avaliable = null;
	public ArrayList<ArrayList<Integer>> Max = null;
	public ArrayList<ArrayList<Integer>> Allocation = null;
	public ArrayList<ArrayList<Integer>> Need = null;
	
	public int n;
	public int m;
	
	private ArrayList<String> lines = null;
	private String actualLine = null;
	private String[] tokens=null;
	
	public ResourcesManager(String file_name)
	{
		Avaliable = new ArrayList<>();
		Max = new ArrayList< ArrayList<Integer> >();
		Allocation = new ArrayList< ArrayList<Integer> >();
		Need = new ArrayList< ArrayList<Integer> >();
		
		lines = readFile(file_name);
		
		for(int i=0; i<lines.size(); i++)
		{
			actualLine = lines.get(i);
			
			tokens = actualLine.split(" ");
			
			if(i==0)
			{
				n = Integer.parseInt(tokens[0]);
				m = Integer.parseInt(tokens[1]);
			}
			else
			{
				for(int j=0; j<n; j++)
				{
					Allocation.add(new ArrayList<Integer>());
					Max.add(new ArrayList<Integer>());
					Need.add(new ArrayList<Integer>());
					
					for(int k=0; k<m; k++)
					{
						Allocation.get(j).add(Integer.parseInt(tokens[k]));
						Max.get(j).add(Integer.parseInt(tokens[m+k]));
						
						Need.get(j).add(Max.get(j).get(k) - Allocation.get(j).get(k));
					}
				}
			}
		}
	}
}
