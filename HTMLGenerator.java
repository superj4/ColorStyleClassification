package ColorStyleClassification;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HTMLGenerator {
	public static Map<String, ArrayList<Integer>> csvToMap(String csvFile) throws IOException {
		BufferedReader br = null;
		String line = null;
		final String cvsSplitBy = ",";
		Map<String, ArrayList<Integer>> maps = new HashMap<String, ArrayList<Integer>>();
		br = new BufferedReader(new FileReader(csvFile));
		// ignore the first line
		while ((line = br.readLine()) != null) {
			break;
		}
		while ((line = br.readLine()) != null) {
			// use comma as separator
			String[] entry = line.trim().split(cvsSplitBy);
			String img = entry[0].trim();
			String cat = entry[1].trim();
			if (maps.containsKey(cat)) {
				ArrayList<Integer> list = maps.get(cat);
				list.add(Integer.parseInt(img));
			} else {
				ArrayList<Integer> newList = new ArrayList<Integer>();
				newList.add(Integer.parseInt(img));
				maps.put(cat, newList);
			}
		}
		if (br != null) {
			br.close();
		}
		return maps;
	}

	public static void process(Map<String, ArrayList<Integer>> cateMaps,
			Map<String, ArrayList<Integer>> idMap,
			ArrayList<Integer> otherFormat) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter("/Users/shuangsu/Documents/workspace/DM/src/ColorStyleClassification/imageShowSample.html"));

		out.write("<html>");
		out.newLine();
		out.write("  <head>");
		out.newLine();
		out.write("    <title>Image Demo </title>");
		out.newLine();
		out.write("    <meta charset=\"utf-8\">");
		out.newLine();
		out.write("     <link rel='stylesheet' href=\"image.css\">");
		out.newLine();
		out.write("  </head>");
		out.newLine();

		out.write("  <body>");
		out.newLine();
		for (Map.Entry<String, ArrayList<Integer>> entry : cateMaps.entrySet()) {
			String key = entry.getKey();
			ArrayList<Integer> list = entry.getValue();
			out.write("    <h2> This is the category: ");
			out.write(key);
			out.write("</h2>");
			out.newLine();
			int length = list.size();
			int iterator = (int) Math.ceil(length / 4.0);
			int count = 0;
			for (int j = 0; j < iterator; j++) {
				out.write("    <div class=\"row\">");
				out.newLine();
				while (count < length) {
					out.write("      <div class=\"col-3\">");
					out.newLine();
					out.write("	       <img src=\"/Users/shuangsu/Downloads/housingIMG/");
					int id = list.get(count);
					out.write(String.valueOf(id));
					String type = "";
					if (otherFormat.contains(id)) {
						ArrayList<Integer> listPNG = idMap.get("png");
						if (listPNG.contains(id)) {
							type = "png";
						} else {
							type = "jpeg";
						}
					} else {
						type = "jpg";
					}
					out.write(".");
					out.write(type);
					out.write("\" alt=\"image\">");
					out.newLine();
					// out.write("	       <img src=\"images/8.jpg\" alt=\"image\">");
					out.write("      </div>");
					out.newLine();
					count++;
				}
				out.write("    </div>");
				out.newLine(); // end of the 4 images group
			}

		}

		out.write("    <div class=\"footer\">");
		out.newLine();
		out.write("      Image Classification Project<br>COEN281 Data Mining<br>Santa Clara University<br>Santa, CA");
		out.newLine();
		out.write("    </div>");
		out.newLine();
		out.write("  </body>");
		out.newLine();
		out.write("</html>");
		out.newLine();
		if (out!=null) out.close();
	}

	public static void main(String[] args) {
		Map<String, ArrayList<Integer>> idMap = new HashMap<String, ArrayList<Integer>>();
		ArrayList<Integer> listPNG = new ArrayList<Integer>(Arrays.asList(2,
				66, 184, 185, 191, 234, 239));
		idMap.put("png", listPNG);
		ArrayList<Integer> listJPEG = new ArrayList<Integer>(Arrays.asList(5,
				11, 44, 51, 115, 139, 204, 218, 246));
		idMap.put("jpeg", listJPEG);
		ArrayList<Integer> otherFormat = new ArrayList<Integer>(Arrays.asList(
				2, 66, 184, 185, 191, 234, 239, 5, 11, 44, 51, 115, 139, 204,
				218, 246));
		try {
			Map<String, ArrayList<Integer>> cateMaps = csvToMap("/Users/shuangsu/Documents/workspace/DM/src/ColorStyleClassification/testFiles/firstforest.csv");
			process(cateMaps, idMap, otherFormat);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
