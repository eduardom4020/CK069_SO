
public class ResultSet
{
	public String buffer_history;
	public Double time_product;
	public Integer pageFault;
	public Integer buffer_capacity;
	
	public ResultSet(String buffer_history, Integer time, Integer time_change, Integer pageFault, Integer buffer_capacity)
	{
		this.buffer_history = buffer_history;
		this.time_product = time_change/time.doubleValue();
		this.pageFault = pageFault;
		this.buffer_capacity = buffer_capacity;
	}
}