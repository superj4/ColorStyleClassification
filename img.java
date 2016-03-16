package imgsim;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
public class img 
{
	public static final int numSharePerDimension = 4;
	public static void main(String args[]) throws IOException
	{
		BufferedImage bi;
		bi = ImageIO.read(new File("/Users/jessica/Desktop/housingIMG/1.jpg"));
		List<color> aimg = readIMG(bi);
		color color1 = new color(33,44,55);
		color1.setWeight(0.1);
		color color2 = new color(13,84,105);
		color2.setWeight(0.3);
		color color3 = new color(108,24,205);
		color3.setWeight(0.1);
		color color4 = new color(73,214,85);
		color4.setWeight(0.2);
		color color5 = new color(43,174,115);
		color5.setWeight(0.3);
		color[] abstractImg = new color[]{color1, color2, color3, color4, color5};
		Map imgRange = new HashMap();
		for (int i = 0; i < 5; i++){
			int range = new colorRange( abstractImg[i].r, abstractImg[i].g, abstractImg[i].b, numSharePerDimension).numBucket;
			imgRange.put(range, abstractImg[i].weight);
		}
		for(int j = 0; j < 64; j++)
		{
			if (imgRange.containsKey(j)){
				System.out.print(imgRange.get(j)+",");
			}
			else{
				System.out.print("0,");
			}
		}
		
	}
	
	public static List<color> readIMG(BufferedImage image)
	{
		int[] pixel;
		List<color> aIMG = new ArrayList<color>();
		for (int y = 0; y < image.getHeight(); y++) {
		    for (int x = 0; x < image.getWidth(); x++) {
		        pixel = image.getRaster().getPixel(x, y, new int[3]);
		        color aColor = new color(pixel[0],pixel[1],pixel[2]);
		        aIMG.add(aColor);
		    }
		}
		return aIMG;
	}
	
}