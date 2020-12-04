import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileProcessing {
    public List<String> readFileAsStringList(String fileName) {
        List<String> inputList = new ArrayList<String>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("\\Users\\klaudia.bzdyk\\Desktop\\AdventOfCode\\" + fileName));
            String line = reader.readLine();
            while (line != null) {
                inputList.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputList;
    }

    public List<Integer> readFileAsIntList(String filePath) {
        List<Integer> inputList = new ArrayList<Integer>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while (line != null) {
                inputList.add(Integer.valueOf(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputList;
    }
}
