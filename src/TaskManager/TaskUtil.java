package TaskManager;

public class TaskUtil {

	public static boolean validateName(String name)
	{
		// name should not be null or blank and it 
		// should not contain spl chars or space and
		// must start with letter! length <= 100
		
		if(name==null || name.trim().equals(""))
			return false;
		else
		{
			if(name.split(" ").length>1)
				return false;
			
			if(name.length() > 100)
				return false;
			else
			{
				if(!Character.isLetter(name.charAt(0)))
					return false;
				else
				{
					for(int i = 1 ; i < name.length() ; i++)
					{
						char c = name.charAt(i);
						if(!(Character.isDigit(c) || Character.isLetter(c)))
							return false;
					}
					//means name is valid!
					return true;
				}
			}
		}
	}
}




