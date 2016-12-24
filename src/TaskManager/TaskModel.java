package TaskManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.ListenerNotFoundException;

public class TaskModel {
	public TaskModel()
	{
		Logger.getInstance().log("in TaskModel no arg constructer");
	}
	
	// Listing Of Categories....
	public List<String> listCategory()
	{ 
		
		List<String> l1=new ArrayList<String>();
		File f=new File(Constants.CATPATH);
		  File[]ff=f.listFiles();
		  for(File f3:ff)
		  {
			  if(f3.getName().endsWith(".tasks"))
				 l1.add(f3.getName().substring(0,f3.getName().length()-6)) ;
		  }
		  return l1;
		
	}
	
	
	// Check for CategeoryExistance..
	
	public boolean checkIfCategoryExist(String catName)
	{
		File f=new File(Constants.CATPATH+catName+".tasks");
		return f.exists();
	}
	
	// Getting of Tasks............
	
	public List<TaskBean> getTasks(String catName)
	{
		if(catName == null || catName.trim().equals(""))
			throw new IllegalArgumentException("category name is empty!!");
		else
		{
			BufferedReader br=null;
			
			try{
				br = new BufferedReader(new FileReader(Constants.CATPATH+catName+".tasks"));
				String line;
				TaskBean bean=null;
				List<TaskBean>tasks=new ArrayList<TaskBean>();
				
				while((line=br.readLine())!=null)
				{
					bean=new TaskBean();
				String[]s1=line.split(":");
			    bean.setName(s1[0]);
				bean.setDesc(s1[1]);
				bean.setStartDate(s1[2]);
				bean.setEndDate(s1[3]);
				bean.setStatus(s1[4]);
				bean.setPriority(Integer.parseInt(s1[5]));
				bean.setTags(s1[6]);
				tasks.add(bean);
				
				}
				return tasks;
				
			}
			catch(IOException e)
			{
				e.printStackTrace();
				return null;
				
			}
			finally
			{
				if(br!=null)
				{
					try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			}
		}
	
	// Seraching of Tasks.............
	
	public List<String> SearchTask(String search,String catName)
	{
		Logger.getInstance().log("In SearchTask searching for "+search+"in :"+catName);
		BufferedReader br=null;
		TaskBean bean=null;
		try{
			br=new BufferedReader(new FileReader(Constants.CATPATH+catName+".tasks"));
			String line;
			int count=0;
			ArrayList<String>matches=new ArrayList<String>();
			
			ArrayList<String> tasknames=new ArrayList<>();
			tasknames.add("the match found in taskNames for "+search);
			ArrayList<String> descriptions=new ArrayList<>();
			descriptions.add("the match found in description for "+search);
			ArrayList<String> startDate=new ArrayList<>();
			startDate.add("the match found in startDate for "+search);
			ArrayList<String> endDate=new ArrayList<>();
			endDate.add("the match found in endDate for "+search);
			ArrayList<String> status=new ArrayList<>();
			status.add("the match found in status for "+search);
			ArrayList<String> priority=new ArrayList<>();
			priority.add("the match found in priority for "+search);
			ArrayList<String> tags=new ArrayList<>();
			tags.add("the match found in tags for "+search);
			
			
			while((line=br.readLine())!=null)
			{
				
				String[] arr=line.split(":");
				
				
				if(search.equals(line.split(":")[0]))
				{count++;
					tasknames.add(line);
				}
				if(search.equals(line.split(":")[1]))
				{count++;
					descriptions.add(line);
				}
				if(search.equals(line.split(":")[2]))
				{ count++;
					startDate.add(line);
				}
				if(search.equals(line.split(":")[3]))
				{ count++;
					endDate.add(line);
				}
				if(search.equals(line.split(":")[4]))
				{  count++;
					status.add(line);
				}
				if(search.equals(line.split(":")[5]))
				{  count++;
					priority.add(line);
				}
				if((line.split(":")[6]).contains(search))
				{count++;
					tags.add(line);
				}
			}
			   matches.add("Total number of matches found "+Integer.toString(count));
			if(tasknames.size()>1)
				matches.addAll(tasknames);
			if(descriptions.size()>1)
				matches.addAll(descriptions);
			if(startDate.size()>1)
				matches.addAll(startDate);
			if(endDate.size()>1)
				matches.addAll(endDate);
			if(status.size()>1)
				matches.addAll(status);
			if(priority.size()>1)
				matches.addAll(priority);
			if(tags.size()>1)
				matches.addAll(tags);
			
		
			return matches;
			
		 }
		catch(IOException e)
		{
			e.printStackTrace();
			return null;
			
		}
		finally{
			if(br!=null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
		
		
	}
	
		
	// Deleting category..........
	
	
	public boolean deleteCategory(String catName)
	{ Logger.getInstance().log("in deleteCtegory"+catName);
		File f=new File(Constants.CATPATH+catName+".tasks");
		return f.delete();
	}
	
	
		
		
	//Check For Task Existance...........
	public boolean checkForTaskExist(String taskName, String catName)
	{
		Logger.getInstance().log("In checkForTaskExist "+taskName+":"+catName);
		BufferedReader br=null;
		try{
			br=new BufferedReader(new FileReader(Constants.CATPATH+catName+".tasks"));
			String line;
			while((line=br.readLine())!=null)
			{
				if(taskName.equals(line.split(":")[0]))
				{
					return true;
				}
				
			}
			
			
		 }
		catch(IOException e)
		{
			e.printStackTrace();
			
		}
		finally{
			if(br!=null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			
		}
		return false;
		
	}
	
	
	// Getting report about Task Status.........
	public List<String> getReports(List<String>l2)
	{   
		Logger.getInstance().log("in getReports method");
		BufferedReader br=null;
	        String line;
	        
		try{ List<String>ll=new ArrayList<>();
			for(String s:l2)
			{   
				br=new BufferedReader(new FileReader(s+".tasks"));
				while((line=br.readLine())!=null)
				{
				      String[]ss=line.split(":");
				      if(!ss[4].equals("completed"))
				    	  ll.add(line);
				    	  
				}
				
			}
			return ll;
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return null;
		}
		finally{
			if(br!=null)
			{
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	
	
	// Editing of  Tasks....
	
	public String editTask(String taskName,String catName,int i,String edt)
	{  
		Logger.getInstance().log("in editTask  "+taskName+catName+i+edt);
		BufferedWriter bw=null;
		BufferedReader br=null;
		try{
			String line;
			List<String>task=new ArrayList<String>();
			br=new BufferedReader(new FileReader(Constants.CATPATH+catName+".tasks"));
			while((line=br.readLine())!=null)
			{
				if(taskName.equals(line.split(":")[0]))
				{
					String[]s=line.split(":");
					s[i]=edt;
					String set="";
					for(int j=0;j<s.length;j++)
					{
						set+=s[j]+":";
						
					}
					set=set.substring(0, set.length()-1);
					task.add(set);
					
				}
				else
					task.add(line);
			}
			br.close();
			bw=new BufferedWriter(new FileWriter(Constants.CATPATH+catName+".tasks"));
			for(String s3:task)	
			{
				bw.write(s3);
				bw.newLine();
			}
			
		
			
		return Constants.SUCCESS;
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
			return  "oops something went wrong msg"+e.getMessage();
			
		}
		finally
		{
			if(br!=null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(bw!=null)
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
				
		
	}
	
	
	// Deletion Of Tasks.........
	
	
	
public String deleteTask(String taskName, String catName) {
		
		/*
		 * open the file
		 * read one line at a time to a list except the line
		 * that represents/contains taskname
		 * loop the list, overwrite the contents of the file!
		 */
		BufferedReader br = null;
		BufferedWriter bw = null;
		try
		{
			br = new BufferedReader(new FileReader(Constants.CATPATH+catName+".tasks"));
			String line;
			List<String> data = new ArrayList<String>();
			while((line = br.readLine())!=null)
			{
				if(!taskName.equals(line.split(":")[0]))
					data.add(line);
			}
			br.close();
			bw = new BufferedWriter(new FileWriter(Constants.CATPATH+catName+".tasks"));
			
			for(String str : data)
			{
				bw.write(str);
				bw.newLine();
			}
			bw.close();
			
			return Constants.SUCCESS;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return "Oops something bad happened msg = "+e.getMessage();
		}
		
	}


// creation Of Categeory...


public boolean createCategory(String catName)
{ boolean b=false;
	Logger.getInstance().log("in createCategory"+catName);
	File f=new File(Constants.CATPATH+catName+".tasks");
	try {
		b= f.createNewFile();
		return b;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return b;
}
	

// adding of Task.....
public String addTask(TaskBean bean,String category) {
	
	Logger.getInstance().log("TaskModel->addTask() bean = "+bean);
	
	String msg = bean.validate();
	if(!msg.equals(Constants.SUCCESS))
		return msg;
	else
	{
		
		BufferedWriter bw = null;
		try
		{
			bw = new BufferedWriter(new FileWriter(Constants.CATPATH+category+".tasks",true));
			bw.write(bean.getName()+":"+bean.getDesc()+":"+bean.getStartDate()+":"+bean.getEndDate()+":"+bean.getStatus()+":"+bean.getPriority()+":"+bean.getTags()+":"+new Date());
			bw.newLine();
			
			return Constants.SUCCESS;
		}
		catch(IOException e)
		{
			
			e.printStackTrace();
			return "Oops something bad happened! msg = "+e.getMessage();
		}
		finally
		{
			if(bw!=null)
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	
}
	
	
	
	
	
	
	
	
	
				
			
		
	}







	

	

