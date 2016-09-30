class Fib
{
	private int fib[];
	
	public int[] get() 
	{
		return fib;
	}
	
	public void calculate(int fib_end) 
	{
		fib = new int[fib_end];
		fib[0] = 0;
		fib[1] = 1;
	
		for(int i=2; i<fib_end; i++)
		{
			fib[i] = fib[i-1] + fib[i-2];
		}
	}
}

class Fibonacci implements Runnable
{
	private int fib_end;
	private Fib fib;
	
	public Fibonacci(int fib_end, Fib fib) 
	{
		if (fib_end < 0)
		throw new IllegalArgumentException();
		
		//se nao ocorre a exception seguimos a execucao normal
		this.fib_end = fib_end;
		this.fib = fib;
	}
	
	public void run() 
	{
		fib.calculate(fib_end);
	}
}

class Driver
{
	public static void main(String[] args) 
	{
		if (args.length != 1) 
		{
			System.err.println("Usage Driver <integer>");
			System.exit(0);
		}
	
		Fib fib = new Fib();
		int fib_end = Integer.parseInt(args[0]);
		Thread worker = new Thread(new Fibonacci(fib_end, fib));
		worker.start();
	
		try
		{
			worker.join();
		} catch (InterruptedException ie) { }
		
		int out[] = fib.get();
		
		for(int i=0; i<fib_end; i++)
		{
			System.out.println(out[i]);
		}
	}
}
