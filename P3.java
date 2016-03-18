package ColorStyleClassification;
import java.util.List;


public class P3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Color> colors = img.readIMG("/Users/shuangsu/Desktop/2.jpg");
		KMeans kMeans = new KMeans(colors, 5);
		List<Cluster> pointsClusters = kMeans.getPointsClusters();
		for (int i = 0; i < kMeans.k; i++)
			System.out.println("Cluster " + i + ": " + pointsClusters.get(i).getCentroid());
	}

}
