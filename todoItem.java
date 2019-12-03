import java.util.Calendar;
import java.util.Date;

public class TodoItem// extends FileReader
{
	private Calendar timeAndDate;
	private String title;
	private String description;
	private boolean completed;

	public TodoItem()
	{
		this("", "", false, null);
	}

	public TodoItem(String title, String description, boolean completed, Calendar calendar)
	{
		this.timeAndDate = calendar;
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
