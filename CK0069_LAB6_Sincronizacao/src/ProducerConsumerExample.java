
public class ProducerConsumerExample 
{
	private static boolean Even = true;
	private static boolean Odd = false;
	
	public static void main(String[] args) 
	{
		Dropbox dropbox=null;
		try {
			dropbox = new Dropbox();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		(new Thread(new Consumer(Even, dropbox))).start();
		(new Thread(new Consumer(Odd, dropbox))).start();
		(new Thread(new Producer(dropbox))).start();
	}
}
