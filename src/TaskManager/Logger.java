package TaskManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


public class Logger {
	public static void log(final String data)
	{
		BufferedWriter bw=null;
		try{
			bw=new BufferedWriter(new FileWriter(Constants.LOGPATH,true));
           Date dt=new Date();			
			bw.write(dt.toString()+":"+data);
			bw.newLine();
			if(Constants.LOGGER_MODE)
			{
				//System.out.println(data);
			}
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally
		{
			if(bw!=null)
			{
				try {
					bw.close();
				} catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		}
		
		}
	private Logger()
	
	{
		//System.out.println("IN LOGGER NO-ARG CONSTRUCTER");
	}
	private static Logger obj = null;
	
	
	public static Logger getInstance()
	{
		//System.out.println("in Logger getInstance(), obj = "+obj);
		if(obj==null)
			obj=new Logger();
		return obj;
	}

}
