package ResourcesManaging;
import java.util.ArrayList;


public class ResourcesManager 
{
	
	public ArrayList<Integer> Avaliable = null;
	public ArrayList<ArrayList<Integer>> Max = null;
	public ArrayList<ArrayList<Integer>> Allocation = null;
	public ArrayList<ArrayList<Integer>> Need = null;
	
	public ResourcesManager()
	{
		Avaliable = new ArrayList<>();
		Max = new ArrayList< ArrayList<Integer> >();
		Allocation = new ArrayList< ArrayList<Integer> >();
		Need = new ArrayList< ArrayList<Integer> >();
	}
	
	
}
