package FileManaging;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public abstract class FileManager 
{
	private static ArrayList<String> lines;
	
	public static ArrayList<String> readFile(String file_name) throws Exception		//fileNotFound e IO
	{
		lines = new ArrayList<String>();		
		BufferedReader buffered_reader = new BufferedReader(new FileReader(file_name));

		while(buffered_reader.ready())
		{
			lines.add(buffered_reader.readLine());
		}
		
		buffered_reader.close();
		
		return lines;
	}
	
	public static void writeFile(String file_name, ArrayList<String> lines_in) throws IOException
	{
		lines = lines_in;		
		BufferedWriter buffered_writer = new BufferedWriter(new FileWriter(file_name));
		
		for(int i=0; i<lines.size(); i++)
		{
			buffered_writer.write(lines.get(i));
			buffered_writer.newLine();
		}
		
		buffered_writer.close();
	}
}
