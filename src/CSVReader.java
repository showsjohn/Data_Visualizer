import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader implements FileDataReader
{
    ArrayList<String[]> data;
    String line;
    CSVReader(String filePath) throws IOException {

        data = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        line = br.readLine();

        while(line != null)
       {
           data.add(line.split(","));
           line = br.readLine();
       }

    };

    public ArrayList<String[]> getData()
    {
        return data;
    }

}
