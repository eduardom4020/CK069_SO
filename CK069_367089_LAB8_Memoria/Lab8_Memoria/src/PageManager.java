import java.util.ArrayList;


public abstract class PageManager 
{
	protected ArrayList<Integer> page_buffer;
	protected String buffer_history;
	protected Integer pageFault;
	protected Integer time;
	protected Integer time_change;
	protected Integer head;
	protected Integer buffer_capacity;
	
	protected final Integer MEMORY_TIME = 200; 
	protected final Integer CHANGE_TIME = 2000000;
	
	protected void moveHead()
	{
		if(head == page_buffer.size()-1)
		{
			head=0;
		}
		else
		{
			head++;
		}
	}
	
	protected void set(Integer page_id)
	{
		page_buffer.set(head, page_id);
		moveHead();
		buffer_history += page_id + " ";
	}
	
	protected void swap(Integer page_id)
	{
		set(page_id);
		time += CHANGE_TIME;
		time_change += CHANGE_TIME;
		pageFault += 1;
	}
	
	protected void swap(Integer actual_page_id, Integer new_page_id)
	{
		boolean swaped = false;
		
		for(int i=0; i<page_buffer.size(); i++)
		{
			if(page_buffer.get(i) == actual_page_id)
			{
				head = i;
				swap(new_page_id);
				swaped = true;
			}
		}
		
		if(!swaped)
			swap(new_page_id);
	}
	
	protected boolean acess(Integer page_id)
	{
		time += MEMORY_TIME;
		
		if(page_buffer.contains(page_id))
			return true;
		else	
			return false;
	}
	
	public abstract void process();
	
	public ResultSet resultSet()
	{
		return new ResultSet(buffer_history, time, time_change, pageFault, buffer_capacity);
	}
}
