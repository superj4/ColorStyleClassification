package ColorStyleClassification;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CSVReader {
	
	public static Map<String, ArrayList<Integer>> csvToMap(){
		String csvFile = "/Users/Xiaoyu/Documents/workspace/DataMining/src/ColorStyleClassification/firstforest.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		Map<String, ArrayList<Integer>> maps = new HashMap<String, ArrayList<Integer>>();
		try {
			br = new BufferedReader(new FileReader(csvFile));
			
			//ignore the first line
			while ((line = br.readLine()) != null) {
				break;
			}
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] Image = line.split(cvsSplitBy);
				if(maps.containsKey(Image[1].trim())){
					ArrayList<Integer> list = maps.get(Image[1].trim());
					list.add(Integer.parseInt(Image[0]));
				} else {
					ArrayList<Integer> newList = new ArrayList<Integer>();
					newList.add(Integer.parseInt(Image[0].trim()));
					maps.put(Image[1].trim(), newList);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		
		return maps;
	  }

	  public static void main(String[] args){
		  Map<String, ArrayList<Integer>> maps = csvToMap();	  
		  
		//loop map
		for (Map.Entry<String, ArrayList<Integer>> entry : maps.entrySet()) {
			String key = entry.getKey();
			ArrayList<Integer> list = entry.getValue();
			StringBuilder integerList = new StringBuilder();
			for (Integer num : list){
				integerList.append(num + ", ");
			}
			System.out.println("The key " + key + " contains the following images: " + integerList);

	    }
		
	  }
	
  }

