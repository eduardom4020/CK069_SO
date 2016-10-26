package FileManaging;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public abstract class FileManager 
{
	private static BufferedReader br;

	public static ArrayList<String> readFile(String file_name)
	{
		try 
		{
			br = new BufferedReader(new FileReader(file_name+".txt"));
			
			ArrayList<String> output = new ArrayList<>();
			
			while(br.ready())
			{
				output.add(br.readLine());
			}
			
			br.close();
			return output;
			
		} catch (Exception e) {e.printStackTrace();}
		
		return null;
	}
}
