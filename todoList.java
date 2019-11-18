import java.util.ArrayList;
import java.util.Calendar;

public class todoList extends todoItem
{
	private ArrayList<todoItem> itemArray;
	
	public todoList()
	{
		itemArray = new ArrayList<todoItem>();
		
		todoItem temp = new todoItem("", "", false);
		
		itemArray.add(temp);
	}
	
	public todoList(String title, String description, boolean completed)
	{
		itemArray = new ArrayList<todoItem>();
		
		todoItem temp = new todoItem(title, description, completed);
		
		itemArray.add(temp);
	}
	
	public ArrayList<todoItem> getSpecificDay(int day, int month, int year)
	{
		ArrayList<todoItem> temp = new ArrayList<todoItem>();
		
		for(int i = 0; i < this.itemArray.size(); i++)
		{
			itemArray.get(i).getCalendar();
			if(Calendar.DAY_OF_MONTH == day && Calendar.MONTH == month - 1 && Calendar.YEAR == year)
			{
				temp.add(itemArray.get(i));
			}
		}
		return temp;
	}
}
