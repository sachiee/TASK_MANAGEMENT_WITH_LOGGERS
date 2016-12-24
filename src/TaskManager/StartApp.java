package TaskManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class StartApp {
	
static	Scanner s1=new Scanner(System.in);
static	Scanner s2=new Scanner(System.in);
static	int ch1=0;
static	int ch2=0;
static	int ch3=0;
static int ch4=0;
static	String catName;
static	String  taskName;
static	String desc,tags;
static	String sstdt,senddt;
static	int priority;

/*------ This Whole Method Can be used for ( Inner Task loop ) -------------------*/
	public static void reuse(String catName)
	{   
		TaskModel model=new TaskModel();
		ch2=0;
		
        while(ch2!=6)
        {
        System.out.println("press 1 to add task");
        System.out.println("press 2 to list tasks");
        System.out.println("press 3 to edit task");
        System.out.println("press 4 to delete a task");
        System.out.println("press 5 to search a task ");
        System.out.println("press 6 to go back...");
        System.out.println("");
        
        while(!s1.hasNextInt())
        {
     	   System.out.println("enter the valid input between 1 to 6");
     	   s1.next();
     	   
        }
       ch2= s1.nextInt();
        
        switch(ch2)
        {
        case 1: System.out.println("task adding");
        	    System.out.println("Enter the name of task");
        	    taskName=s2.nextLine();
        	
        	    System.out.println("Enter the description of task");
        	    desc=s2.nextLine();
        	    System.out.println("Enter the start date in format(dd/mm/yyyy)");
        	    sstdt=s2.nextLine();
        	    System.out.println("Enter the end date in format(dd/mm/yyyy)");
        	    senddt=s2.nextLine();
        	    System.out.println("Enter priority(1..10)");
        	    while(!s1.hasNextInt())
        	    {
        	    	System.out.println("enter the integer value");
        	    	s1.next();
        	    }
				priority = s1.nextInt();
        	    
        	    System.out.println("enter the tags of task in comma seperated");
        	    tags=s2.nextLine();
        	    TaskBean tb=new TaskBean(taskName,desc,sstdt,senddt,tags,priority);
        	 String  msg=model.addTask(tb,catName);
        	 if(msg.equals(Constants.SUCCESS))
        		 System.out.println("Task name"+taskName+" added successfully in category "+catName);
        	 else
        		 System.out.println("task did not get added successfully  msg  "+msg);
        	    
        	    break;
        	    
        case 2:List<TaskBean> tasks = model.getTasks(catName);
		if(tasks==null)
			System.out
					.println("Listing is not working...check log for failure details!!");
		else
		{
			PriorityCompare pc=new PriorityCompare();
		 Collections.sort(tasks,pc);
		 for(TaskBean bean:tasks)
		 {
			
			 System.out.println("Name : "+bean.getName()+" Desc : "+bean.getDesc()+" Start Date : "+bean.getStartDate()+" End Date : "+bean.getEndDate()+" Status : "+bean.getStatus()+" Priority : " +bean.getPriority()+" Tags : "+bean.getTags() );
			 
		 }
		
		}
        	   
        	break;
        	
        	
        case 3:
               System.out.println("enter the name of task");
              taskName= s2.nextLine();
              if(!(model.checkForTaskExist(taskName, catName)))
              {
            	  System.out.println("the task is not existing");
            	  break;
              }
              else
              {
            	while((ch3<1)||(ch3>6))
            	{
              System.out.println("enter 1 to edit task name");
              System.out.println("enter 2 edit desc");
              System.out.println("enter 3 to edit endDate");
              System.out.println("enter 4 to edit priority");
              System.out.println("enter 5 to edit status");
              System.out.println("enter 6 to edit tags");
             
               while(!s1.hasNextInt())
               {
            	   System.out.println("enter the valid input between 1 to 6");
            	   s1.next();
               }
               ch3=s1.nextInt();
            	}
            	   
              switch (ch3) 
				 {
				case 1:System.out.println("What do yu want to change this task name as??");
				String newname=s2.nextLine();
				String info= model.editTask(taskName, catName, 0, newname);
				if(info.equals(Constants.SUCCESS))
				System.out.println(" task name successfully edited");
				
				break;
				case 2:System.out.println("What do yu want to change this descrption  as??");
			    String newdescr=s2.nextLine();
			   info=model.editTask(taskName, catName, 1,newdescr );
			    if(info.equals(Constants.SUCCESS))
					System.out.println(" task description successfully edited");
			   
			    break;
				case 3:System.out.println("What do yu want to change this endDate as??");
			    String endDate=s2.nextLine();
			   info= model.editTask(taskName, catName, 3,endDate );
			   if(info.equals(Constants.SUCCESS))
					System.out.println(" task endDate successfully edited"); 
			   
			   
                 break;
				case 4 :System.out.println("What do yu want to change this prio as??");
			    String newprio=s2.nextLine();
			   info= model.editTask(taskName, catName, 5,newprio );
			   if(info.equals(Constants.SUCCESS))
					System.out.println(" task priority successfully edited");
			   
                   break;
				case 5:System.out.println("What do yu want to change this status as??");
			    String newstatus=s2.nextLine();
			    info=model.editTask(taskName, catName, 4,newstatus );
			    if(info.equals(Constants.SUCCESS))
					System.out.println(" task status successfully edited");
			   
                   break;
				
                   
				case 6:System.out.println("What do yu want to change this tag name as??");
			    String newtag=s2.nextLine();
			    info=model.editTask(taskName, catName, 6,newtag );
			    if(info.equals(Constants.SUCCESS))
					System.out.println(" task tag successfully edited");
			    break;
			    default:System.out.println("provide input between 1 to 6");
			            break;
			            
				 }
              }
				
				
        
        	break;
        	
        case 4:System.out.println("enter the name of task");
               taskName= s2.nextLine();
               if(!(model.checkForTaskExist(taskName, catName)))
	              {
	            	  System.out.println("the task is not existing");
	            	  break;
	              }
               else
            	   msg=model.deleteTask(taskName, catName);
               if(msg.equals(Constants.SUCCESS))
	            	  System.out.println("task has been successfully deleted");
	              else
	            	  System.out.println(msg);
       
           
        	break;
        case 5:System.out.println("enter the key to search");
                
                String key= s2.nextLine();
            List<String>search=model.SearchTask(key, catName);
               if(search==null)
               {
            	   System.out.println("oops something went wrong check logger");
            	   break;
               }
               else
            	   if(search.size()<=1)
            	   {
                	   System.out.println("match not found");
                	   break;
                   }
            	   else
            		   for(String s:search)
            		   {
            			   System.out.println(s);
            		   }

            		   
        	break;
        case 6:System.out.println("go back...");
        	break;
         default:System.out.println("option not yet supported");
        	 break;
        }
      	
        }
	
	}
	// Code Reusing Ends here...............
	
	
	// main methd.......................
	
	public static void main(String[] args) {
		
		try
		{  TaskModel model1=new TaskModel();
			
			while(ch4!=7)//to display 1st screen
			{
			
				System.out.println("plese select from  the following menu ");
				System.out.println("select 1 to create category ");
				System.out.println("select 2 to load category ");
				System.out.println("select 3 to search category ");
				System.out.println("select 4 to delete category");
				System.out.println("select 5 to  list all category ");
				System.out.println("select 6 to report");
				System.out.println("select 7 to exit");
				
				
				 System.out.println("");
				 ch4=0;
				while(!s1.hasNextInt())
				{
					System.out.println("please enter the correct Integer between 1 to 7");
					s1.next();
				}
				ch4=s1.nextInt();
				
			    Logger.getInstance().log("ch4"+ch1);
				switch(ch4)
				{
				case 1: //to display second screen
				        System.out.println("please enter the category name");
				        catName= s2.nextLine();
				        Logger.getInstance().log(catName);
				        while(!TaskUtil.validateName(catName))
						{
							System.out.println("Enter valid name buddy!");
							catName = s2.nextLine();
							
						}
				    	System.out.println("valid category name is "+catName);
				    	//checking if category name allready exist
				    	if(model1.checkIfCategoryExist(catName))
				    		System.out.println("Category name is a duplicate, you duplicate! Enter a unique category name!");
				    	else
				    	{
				    		if(!model1.createCategory(catName))
				    		{
				    			System.out.println("category not created check the log");
				    			break;
				    		}
				    		System.out.println("category "+catName+" has been created");
				    		reuse(catName);
				    	
				    	
				    	}
				    	break;
				        	
				        	
				       
					
				case 2:  System.out.println("please enter the category name");
		                 catName= s2.nextLine();
		                 Logger.getInstance().log(catName);
		                 while(!TaskUtil.validateName(catName))
							{
								System.out.println("Enter valid name buddy!");
								catName = s2.nextLine();
								
							}
					    	System.out.println("valid category name is "+catName);
		               if(!model1.checkIfCategoryExist(catName))
		               {
		            	  System.out.println("category doesnot exists"); 
		            	  break;
		     
		               }
		               
		               
		                 reuse(catName);
		                 
		                 System.out.println("last line of cat load");
		                break;
				case 3: System.out.println("please enter the category name");
						catName= s2.nextLine();
						 while(!TaskUtil.validateName(catName))
							{
								System.out.println("Enter valid name buddy!");
								catName = s2.nextLine();
								
							}
					    	System.out.println("valid category name is "+catName);
		               if(!model1.checkIfCategoryExist(catName))
		               {
		            	  System.out.println("category doesnot exists"); 
		                    break;
		               }
		               
						System.out.println("category "+catName+" exists");
						break;
				case 4: System.out.println("please enter the category name");
                        catName= s2.nextLine();
                        while(!TaskUtil.validateName(catName))
						{
							System.out.println("Enter valid name buddy!");
							catName = s2.nextLine();
							
						}
				    	System.out.println("valid category name is "+catName);
	               if(!model1.checkIfCategoryExist(catName))
	               {
	            	  System.out.println("category doesnot exists"); 
	                      break;
	               }
	                  if(model1.deleteCategory(catName))
	                  {
	                	  System.out.println("category successfully deleted");
	                	  break;
	                  }
	                   System.out.println("category not deleted check log");
	                  
						break;
				case 5: System.out.println("Listing all categories");
				          List<String>l1= model1.listCategory();
				          if(l1.size()==0)
				          {
				        	  System.out.println("no category found");
				          break;
				          }
				          else
				          Collections.sort(l1);
				          for(String s:l1)
				          {
				        	  System.out.println(s);
				          }
              
						break;
				case 6:System.out.println("reports....");
				List<String>lis= model1.listCategory();
		          if(lis.size()==0)
		          {
		        	  System.out.println("no category found");
		          break;
		          }
		          else
		          {
		         
		        	  List<String>ll= model1.getReports(lis);
		        	  for(String s:ll)
		        	  {
		        		  System.out.println(s);
		        	  }
		          
		          }
				       break;
				case 7: System.out.println("tata bye bye");
						break;
				default:System.out.println("option not supported");
				        break;
					
				
				}
			}
				
		}
		catch(Throwable t)
		{
			t.printStackTrace();
		}
		finally
		{
			System.out.println("main end");
		}

}
}



