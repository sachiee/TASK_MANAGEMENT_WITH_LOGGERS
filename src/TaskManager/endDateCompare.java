package TaskManager;


	import java.text.SimpleDateFormat;
	import java.util.Comparator;
	import java.util.Date;

	public class endDateCompare implements Comparator<TaskBean> {

		@Override
		public int compare(TaskBean o1, TaskBean o2) {
			
			String d1=o1.getEndDate();
			String d2=o2.getEndDate();
			Date dt1=null;
			Date dt2=null;
			//convert string to java.util.Date!!!
			try{
				
				SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
				dt1=sdf.parse(d1);
				dt2=sdf.parse(d2);
				return dt1.compareTo(dt2);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return dt1.compareTo(dt2);
			
		}

	}



