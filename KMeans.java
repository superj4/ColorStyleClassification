package ColorStyleClassification;
import java.util.*;

public class KMeans {
	private static final Random random = new Random();
	public final List<Color> allPoints;
	public final int k;
	private Clusters pointClusters; // the k Clusters

	/**
	 * @param allColors
	 *            : a list of colors
	 * @param k
	 *            : number of clusters
	 */
	public KMeans(List<Color> allColors, int k) {
		if (k < 2)
			new Exception("The value of k should be 2 or more.")
					.printStackTrace();
		this.k = k;
		this.allPoints = allColors;
	}

	/**
	 * step 1: get random seeds as initial centroids of the k clusters
	 */
	private void getInitialKRandomSeeds() {
		pointClusters = new Clusters(allPoints);
		List<Color> kRandomPoints = getKRandomPoints();
		for (int i = 0; i < k; i++) {
			kRandomPoints.get(i).setIndex(i);
			pointClusters.add(new Cluster(kRandomPoints.get(i)));
		}
	}

	private List<Color> getKRandomPoints() {
		List<Color> kRandomPoints = new ArrayList<Color>();
		boolean[] alreadyChosen = new boolean[allPoints.size()];
		int size = allPoints.size();
		for (int i = 0; i < k; i++) {
			int index = -1, r = random.nextInt(size--) + 1;
			for (int j = 0; j < r; j++) {
				index++;
				while (alreadyChosen[index])
					index++;
			}
			kRandomPoints.add(allPoints.get(index));
			alreadyChosen[index] = true;
		}
		return kRandomPoints;
	}

	/**
	 * step 2: assign points to initial Clusters
	 */
	private void getInitialClusters() {
		pointClusters.assignPointsToClusters();
	}

	/**
	 * step 3: update the k Clusters until no changes in their members occur
	 */
	private void updateClustersUntilNoChange() {
		boolean isChanged = pointClusters.updateClusters();
		while (isChanged)
			isChanged = pointClusters.updateClusters();
	}

	/**
	 * do K-means clustering with this method
	 */
	public List<Cluster> getPointsClusters() {
		if (pointClusters == null) {
			getInitialKRandomSeeds();
			getInitialClusters();
			updateClustersUntilNoChange();
		}
		return pointClusters;
	}

	
}
