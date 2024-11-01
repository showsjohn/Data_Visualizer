import java.util.ArrayList;
import java.util.Arrays;

public class DataController
{
    FileDataReader reader;
    ArrayList<String[]> data;

    DataController(FileDataReader reader)
    {
        this.reader = reader;
        this.data = reader.getData();
    }

    String[] getHeader()
    {
        return data.get(0);
    }

    String[][] getData()
    {
        String[][] dataToReturn = new String[data.size()-1][];

        for (int i = 1; i < data.size(); i++) {
            dataToReturn[i-1] = data.get(i);
        }

        return dataToReturn;
    }



}
