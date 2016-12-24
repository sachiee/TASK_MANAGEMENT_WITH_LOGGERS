package TaskManager;
import java.util.Comparator;

public class PriorityCompare  implements Comparator<TaskBean>{
	

	
		public int compare(TaskBean o1, TaskBean o2) {

			return o1.getPriority()-o2.getPriority();
		}

	}



