import java.util.Calendar;
import java.util.Date;

public class todoItem// extends FileReader
{
	private Calendar timeAndDate;
	private String title;
	private String description;
	private boolean completed;
	
	Date date = new Date();
	Calendar temp = Calendar.getInstance();
	
	public todoItem()
	{
		this("", "", false);
	}
	
	public todoItem(String title, String description, boolean completed)
	{
		this.timeAndDate.setTime(date);
		this.title = title;
		this.description = description;
		this.completed = completed;
	}
	
	public void setCalendar(Calendar t)
	{
		this.timeAndDate = t;
	}
	
	public Calendar getCalendar()
	{
		return this.timeAndDate;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public void setCompleted(boolean completed)
	{
		this.completed = completed;
	}
	
	public boolean getCompleted()
	{
		return this.completed;
	}
}
