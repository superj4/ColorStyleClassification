package ColorStyleClassification;
import java.util.ArrayList;
import java.util.List;

public class Cluster {
	private final List<Color> points;
	private Color centroid;
	private int id;

	public Cluster(Color firstPoint) {
		points = new ArrayList<Color>();
		addPoint(firstPoint);
		centroid = firstPoint;
	}

	public Color getCentroid() {
		return centroid;
	}
	
	public void updateCentroid() {
		int newr = 0, newg = 0, newb = 0;
		for (Color point : points) {
			newr += point.r;
			newg += point.g;
			newb += point.b;
		}
		centroid = new Color(newr / points.size(), newg / points.size(), newb
				/ points.size());
	}

	public List<Color> getPoints() {
		return points;
	}
	
	public void addPoint(Color c){
		points.add(c);
	}
	
	public int getId(){
		return id;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder(
				"This cluster contains the following points:\n");
		for (Color point : points)
			builder.append(point.toString() + ",\n");
		return builder.deleteCharAt(builder.length() - 2).toString();
	}
}
