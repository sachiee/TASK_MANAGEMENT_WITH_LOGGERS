package TaskManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskBean implements Comparable{

	private String name, desc, startDate, endDate, tags;
	private int priority;
	private String status="new";
	private Date actualEndDate;
	
	public TaskBean() {
		Logger.getInstance().log("in TaskBean no-arg constr");
	}
	
	public TaskBean(String name, String desc, String startDate, String endDate,
			String tags, int priority) {
		super();
		this.name = name;
		this.desc = desc;
		this.startDate = startDate;
		this.endDate = endDate;
		this.tags = tags;
		this.priority = priority;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getActualEndDate() {
		return actualEndDate;
	}
	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((actualEndDate == null) ? 0 : actualEndDate.hashCode());
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + priority;
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskBean other = (TaskBean) obj;
		if (actualEndDate == null) {
			if (other.actualEndDate != null)
				return false;
		} else if (!actualEndDate.equals(other.actualEndDate))
			return false;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (priority != other.priority)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TaskBean [name=" + name + ", desc=" + desc + ", startDate="
				+ startDate + ", endDate=" + endDate + ", tags=" + tags
				+ ", priority=" + priority + ", status=" + status
				+ ", actualEndDate=" + actualEndDate + "]";
	}
	
	public String validate()
	{
		//perform input validations!
		
		String msg = "";
		if(name==null || name.trim().equals(""))
			msg = msg + " Enter task name. It is mandatory!!";
		
		if(name.split(" ").length>1)
		{
			msg="multiple words are not allowed";
		}
		else
		{
		 for(int i=0;i<name.length();i++)
		 {
			 char c=name.charAt(i);
				if(!(Character.isDigit(c)||Character.isLetter(c)))
				{
					msg="special characters are not allowed";
					break;
				}
		 }
		}
		if(desc==null || desc.trim().equals(""))
			msg = msg + " Enter description!. It is mandatory!!";
		if(priority < 0 || priority > 10)
			msg = msg + "prority range is 1 to 10 oly pa!";
		 // for date   { imp here}
		 if(startDate!=null)
		 {
		 SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		 sdf.setLenient(false);   // very imp here..
		 try {
			Date d=sdf.parse(startDate);
		} catch (ParseException e) {
			
		//	msg="date should be correct foramat";
			msg=e.getMessage();
		}
		 }
		 if(endDate!=null)
		 {
		 SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		 sdf.setLenient(false);   // very imp here..
		 try {
			Date d=sdf.parse(endDate);
		} catch (ParseException e) {
			
		//	msg="date should be correct foramat";
			msg=e.getMessage();
		}
		 }
		 

		 // for tag...
		 
		 if(tags==null||tags.equals(""))
			{
				msg="Task tags not be blank or null";
			}
		
		if(msg.equals(""))
			return Constants.SUCCESS;
		else
			return msg;
					
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof TaskBean)
		{
			TaskBean bean = (TaskBean) o;
			return this.name.compareTo(bean.getName());
		}
		else
			throw new IllegalArgumentException("only task beans can be compared");
	}
	
}








