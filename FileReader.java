import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
public class FileReader
{
  private static SimpleDateFormat df = new SimpleDateFormat();
  //create or overwrite a file, given its name, for storing todoItems
  public static void storeData(TodoList list, String filename) throws IOException
  {
    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
    Iterator<TodoItem> iter = list.iterator();
    TodoItem temp;
    while(iter.hasNext())
    {
      temp = iter.next();
      writer.write(temp.getTitle());
      writer.newLine();
      writer.write(temp.getDescription());
      writer.newLine();
      writer.write(temp.getCompleted()+"");
      writer.newLine();
      if(temp.getCalendar() != null){
        writer.write(df.format(temp.getCalendar().getTime()));
      }
      writer.newLine();
      writer.newLine();
    }
    writer.close();
  }

  //return a list of todoItems given a file providing information
  public static TodoList readData(String filename) throws IOException
  {
    TodoList list = new TodoList();
    Scanner scan = new Scanner(new File(filename));
    TodoItem temp;
    while(scan.hasNextLine())
    {
      temp = new TodoItem();
      temp.setTitle(scan.nextLine());
      temp.setDescription(scan.nextLine());
      temp.setCompleted(Boolean.parseBoolean(scan.nextLine()));
      Calendar cal = Calendar.getInstance();
      String stringDate = "";
      if(scan.hasNextLine())
        stringDate = scan.nextLine();
      try{
        if(stringDate.equals("")){
          cal = null;
        }else{
          cal.setTime(FileReader.df.parse(stringDate));
        }
      }catch(ParseException e){
        e.printStackTrace();
      }
      temp.setCalendar(cal);
      list.add(temp);
      if(scan.hasNextLine())
        scan.nextLine();  //advance to the next line, tasks are seperated by empty lines
    }
    return list;
  }
}
