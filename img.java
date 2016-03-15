package imgsim;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
public class img 
{
	public static void main(String args[]) throws IOException
	{
		BufferedImage bi;
		bi = ImageIO.read(new File("/Users/jessica/Desktop/housingIMG/1-apartment-plants-air-purification.jpg"));
		List<color> aimg = readIMG(bi);
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