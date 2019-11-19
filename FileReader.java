import java.io*;
import java.util.*;
public class FileReader
{
  //create or overwrite a file, given its name, for storing todoItems
  public static void storeData(ArrayList<todoItem> list, String filename) throws IOException
  {
    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
    Iterator<todoItem> iter = list.iterator();
    todoItem temp;
    while(iter.hasNext())
    {
      temp = iter.next();
      writer.write(temp.getTitle());
      writer.newLine();
      writer.write(temp.getDescription());
      writer.newLine();
      writer.write(temp.isComplete());
      writer.newLine();
      writer.newLine();
    }
    writer.close();
  }
  
  //return a list of todoItems given a file providing information
  public static ArrayList<todoItem> readData(String filename) throws IOException
  {
    ArrayList<todoItem> list = new ArrayList<>();
    Scanner scan = new Scanner(new File(filename));
    todoItem temp;
    while(scan.hasNextLine())
    {
      temp = new todoItem();
      temp.setTitle(scan.nextLine());
      temp.setDescription(scan.nextLine());
      temp.setCompleted(Boolean.parseBoolean(scan.nextLine()));
      list.add(temp);
      scan.nextLine();  //advance to the next line, tasks are seperated by empty lines
    }
    return list;
  }
}
