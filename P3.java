package ColorStyleClassification;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class P3 {
	public static final int numSharePerDimension = 4;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
/*
		List<Color> colors = img.readIMG("/Users/shuangsu/Desktop/2.jpg");
		KMeans kMeans = new KMeans(colors, 5);
		List<Cluster> pointsClusters = kMeans.getPointsClusters();
		for (int i = 0; i < kMeans.k; i++)
			System.out.println("Cluster " + i + ": " + pointsClusters.get(i).getCentroid() + " weight: " + (double) pointsClusters.get(i).getPoints().size() / colors.size());
*/
		//read files in the image directory
		File f = new File("/Users/jessica/Desktop/test");
		File[] paths;
		paths = f.listFiles();
		//write output
		FileOutputStream fos = new FileOutputStream(new File("out.csv"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		String header = "imageID";
		for ( int i = 0; i < Math.pow(numSharePerDimension, 3); i++ )
		{
			header = header + "," + i;
		}
		bw.write(header);
		bw.newLine();
		for (File path:paths)
        {
			// get rid of max file system meta file
			if (!path.toString().endsWith("DS_Store")) 
			{
				String id = path.toString().replaceAll("[^0-9]", "");
				List<Color> pixels = readIMG(path.toString());
				KMeans kMeans = new KMeans(pixels, 5);
				List<Cluster> pointsClusters = kMeans.getPointsClusters();
				String aLine = writeAline(pointsClusters, pixels.size(), id);
				bw.write(aLine);
				bw.newLine();
			}
        }
		bw.close();
	}		
	
	public static String writeAline(List<Cluster> pointsClusters, int size, String id)
	{
		Map imgRange = new HashMap();
		StringBuilder sb = new StringBuilder(); 
		sb.append(id);
		double grayscale = 0;
		for (int i = 0; i < 5; i++){
			int range = new ColorRange( pointsClusters.get(i).getCentroid().r, pointsClusters.get(i).getCentroid().g, pointsClusters.get(i).getCentroid().b, numSharePerDimension).numBucket;
			
			int clusterSize = pointsClusters.get(i).getPoints().size();
			double weight = clusterSize/(double) size;
			imgRange.put(range, weight);
		}
		for(int j = 0; j < Math.pow(numSharePerDimension, 3); j++)
		{
			if (imgRange.containsKey(j)){
				sb.append(","+imgRange.get(j));
			}
			else{
				sb.append(",0");
			}
		}
		//compute grayscale
		
		return sb.toString();
	}
	
	public static List<Color> readIMG(String path) throws IOException
	{
		BufferedImage bi;
		bi = ImageIO.read(new File(path));
		int[] pixel;
		List<Color> aIMG = new ArrayList<Color>();
		for (int y = 0; y < bi.getHeight(); y++) {
		    for (int x = 0; x < bi.getWidth(); x++) {
		        pixel = bi.getRaster().getPixel(x, y, new int[3]);
		        Color aColor = new Color(pixel[0],pixel[1],pixel[2]);
		        aIMG.add(aColor);
		    }
		}
		return aIMG;
	}

}
