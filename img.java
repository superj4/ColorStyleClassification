package ColorStyleClassification;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

public class img {
	public static final int numSharePerDimension = 4;
	/*
	public static void main(String args[]) throws IOException {
		/*BufferedImage bi;
		bi = ImageIO.read(new File("/Users/jessica/Desktop/housingIMG/1.jpg"));
		List<Color> aimg = readIMG(bi);
		Color color1 = new Color(33, 44, 55);
		color1.setWeight(0.1);
		Color color2 = new Color(13, 84, 105);
		color2.setWeight(0.3);
		Color color3 = new Color(108, 24, 205);
		color3.setWeight(0.1);
		Color color4 = new Color(73, 214, 85);
		color4.setWeight(0.2);
		Color color5 = new Color(43, 174, 115);
		color5.setWeight(0.3);
		Color[] abstractImg = new Color[] { color1, color2, color3, color4,
				color5 };
		Map imgRange = new HashMap();
		for (int i = 0; i < 5; i++) {
			int range = new colorRange(abstractImg[i].r, abstractImg[i].g,
					abstractImg[i].b, numSharePerDimension).numBucket;
			imgRange.put(range, abstractImg[i].weight);
		}
		for (int j = 0; j < 64; j++) {
			if (imgRange.containsKey(j)) {
				System.out.print(imgRange.get(j) + ",");
			} else {
				System.out.print("0,");
			}
		}

	}
	*/
	public static List<Color> readIMG(String filePath) {
		BufferedImage bi;
		List<Color> aIMG = new ArrayList<Color>();
		try {
			bi = ImageIO.read(new File(filePath));
			for (int y = 0; y < bi.getHeight(); y++) {
				for (int x = 0; x < bi.getWidth(); x++) {
					int[] pixel = bi.getRaster().getPixel(x, y, new int[3]);
					Color aColor = new Color(pixel[0], pixel[1], pixel[2]);
					aIMG.add(aColor);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aIMG;
	}

}